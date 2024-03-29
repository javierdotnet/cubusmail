/* MailCanvas.java

   Copyright (c) 2010 Juergen Schlierf, All Rights Reserved
   
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
package com.cubusmail.client.canvases.mail;

import com.cubusmail.client.canvases.CanvasRegistry;
import com.cubusmail.client.canvases.IWorkbenchCanvas;
import com.cubusmail.client.toolbars.ToolbarRegistry;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * TODO: documentation
 * 
 * @author Juergen Schlierf
 */
public class MailCanvas extends VLayout implements IWorkbenchCanvas {

	public MailCanvas() {

		super();
		setBackgroundImage( "[SKIN]/shared/background.gif" );
		setWidth100();
		setHeight100();
		setBorder( "0px" );
		addMember( ToolbarRegistry.MAIL.get() );

		HLayout contentCanvas = new HLayout();
		contentCanvas.setWidth100();
		contentCanvas.setHeight100();

		CanvasRegistry.MAIL_FOLDER_CANVAS.get().setWidth( 200 );
		contentCanvas.addMember( CanvasRegistry.MAIL_FOLDER_CANVAS.get() );
		contentCanvas.addMember( CanvasRegistry.MESSAGE_LIST_CANVAS.get() );

		addMember( contentCanvas );
	}
}
