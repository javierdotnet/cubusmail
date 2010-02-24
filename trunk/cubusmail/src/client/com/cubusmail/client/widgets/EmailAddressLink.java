/* EmailAddressLink.java

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
package com.cubusmail.client.widgets;

import com.cubusmail.common.model.GWTAddress;
import com.google.gwt.user.client.ui.Hyperlink;

/**
 * Hyperlink for email addresses.
 * 
 * @author Juergen Schlierf
 */
public class EmailAddressLink extends Hyperlink {

//	private Menu contextMenu;

	public EmailAddressLink( GWTAddress address) {

		super();
	    setStyleName("emailAddressLink");
		setText( address.getInternetAddress() );
		setTargetHistoryToken( "#" );

//		AddContactFromEmailAddressAction addContactAction = new AddContactFromEmailAddressAction();
//		addContactAction.setAddress( address );
//		NewMessageToEmailAddressAction newMessageAction = new NewMessageToEmailAddressAction();
//		newMessageAction.setAddress( address );
//
//		this.contextMenu = new Menu();
//		this.contextMenu.addItem( UIFactory.createMenuItem( addContactAction ) );
//		this.contextMenu.addItem( UIFactory.createMenuItem( newMessageAction ) );
//
//		MouseListenerAdapter listener = new MouseListenerAdapter() {
//
//			@Override
//			public void onMouseDown( Widget sender, int x, int y ) {
//
//				contextMenu.showAt( sender.getAbsoluteLeft() + x + 10, sender.getAbsoluteTop() + y );
//			}
//		};
//		link.addLeftButtonListener( listener );
//		link.addRightButtonListener( listener );

	}
}
