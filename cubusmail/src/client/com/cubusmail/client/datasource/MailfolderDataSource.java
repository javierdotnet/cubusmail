/* MailfolderDataSource.java

   Copyright (c) 2009 Juergen Schlierf, All Rights Reserved
   
   This file is part of Cubusmail (http://code.google.com/p/cubusmail/).
	
   This library is free software; you can redistribute it and/or
   modify it under the terms of the GNU Lesser General Public
   License as published by the Free Software Foundation; either
   version 3 of the License, or (at your option) any later version.
	
   This library is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
   Lesser General Public License for more details.
	
   You should have received a copy of the GNU Lesser General Public
   License along with Cubusmail. If not, see <http://www.gnu.org/licenses/>.
 */
package com.cubusmail.client.datasource;

import com.cubusmail.client.events.EventBroker;
import com.cubusmail.client.exceptions.GWTExceptionHandler;
import com.cubusmail.client.util.GWTSessionManager;
import com.cubusmail.client.util.ServiceProvider;
import com.cubusmail.client.util.TextProvider;
import com.cubusmail.client.util.UIFactory;
import com.cubusmail.common.exceptions.folder.GWTMailFolderException;
import com.cubusmail.common.exceptions.folder.GWTMailFolderExistException;
import com.cubusmail.common.model.GWTConstants;
import com.cubusmail.common.model.GWTMailFolder;
import com.cubusmail.common.model.GWTMailbox;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.rpc.RPCResponse;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.tree.TreeNode;

/**
 * Data source for mail folders.
 * 
 * @author Juergen Schlierf
 */
public class MailfolderDataSource extends GwtRpcDataSource {

	public MailfolderDataSource() {

		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cubusmail.client.datasource.GwtRpcDataSource#executeAdd(java.lang
	 * .String, com.smartgwt.client.data.DSRequest,
	 * com.smartgwt.client.data.DSResponse)
	 */
	@Override
	protected void executeAdd( final String requestId, final DSRequest request, final DSResponse response ) {

		JavaScriptObject jsObject = request.getAttributeAsJavaScriptObject( GWTConstants.PARAM_PARENT_FOLDER );
		TreeNode parentNode = TreeNode.getOrCreateRef( jsObject );
		String parentId = parentNode.getAttributeAsString( "id" );
		TreeNode newFolderNode = TreeNode.getOrCreateRef( request.getData() );
		String folderName = newFolderNode.getName();

		ServiceProvider.getMailboxService().createFolder( parentId, folderName, new AsyncCallback<GWTMailFolder>() {

			public void onSuccess( GWTMailFolder result ) {

				TreeNode newNode = UIFactory.createTreeNode( result );
				response.setData( new TreeNode[] { newNode } );
				processResponse( requestId, response );
			}

			public void onFailure( Throwable caught ) {

				GWTExceptionHandler.handleException( caught );
				GWTMailFolderException e = (GWTMailFolderException) caught;
				if ( caught instanceof GWTMailFolderExistException ) {
					SC.showPrompt( TextProvider.get().common_error(), TextProvider.get()
							.exception_folder_already_exist( e.getFolderName() ) );
				}
				else {
					SC.showPrompt( TextProvider.get().common_error(), TextProvider.get().exception_folder_create(
							e.getFolderName() ) );
				}
				EventBroker.get().fireFoldersReload();
			}
		} );
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cubusmail.client.datasource.GwtRpcDataSource#executeFetch(java.lang
	 * .String, com.smartgwt.client.data.DSRequest,
	 * com.smartgwt.client.data.DSResponse)
	 */
	@Override
	protected void executeFetch( final String requestId, final DSRequest request, final DSResponse response ) {

		ServiceProvider.getMailboxService().retrieveFolderTree( new AsyncCallback<GWTMailFolder[]>() {

			public void onSuccess( GWTMailFolder[] result ) {

				mapResponse( response, result );
				processResponse( requestId, response );
			}

			public void onFailure( Throwable caught ) {

				GWTExceptionHandler.handleException( caught );
				response.setStatus( RPCResponse.STATUS_FAILURE );
				processResponse( requestId, response );
			}
		} );
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cubusmail.client.datasource.GwtRpcDataSource#executeRemove(java.lang
	 * .String, com.smartgwt.client.data.DSRequest,
	 * com.smartgwt.client.data.DSResponse)
	 */
	@Override
	protected void executeRemove( String requestId, DSRequest request, DSResponse response ) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cubusmail.client.datasource.GwtRpcDataSource#executeUpdate(java.lang
	 * .String, com.smartgwt.client.data.DSRequest,
	 * com.smartgwt.client.data.DSResponse)
	 */
	@Override
	protected void executeUpdate( String requestId, DSRequest request, DSResponse response ) {

	}

	/**
	 * @param response
	 * @param folderTree
	 */
	private void mapResponse( DSResponse response, GWTMailFolder[] folderTree ) {

		GWTMailbox mailbox = GWTSessionManager.get().getMailbox();
		mailbox.setMailFolders( folderTree );
		TreeNode root = UIFactory.createTreeNode( mailbox );
		response.setData( new TreeNode[] { root } );
	}
}
