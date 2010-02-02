/* MessageGridRecord.java

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
package com.cubusmail.client.datasource;

import com.cubusmail.common.model.GWTMessageRecord;
import com.cubusmail.common.model.MessageListFields;
import com.smartgwt.client.widgets.grid.ListGridRecord;

/**
 * List Grid Record implementation f�r message records with some convenience
 * methods.
 * 
 * @author Juergen Schlierf
 */
public class MessageGridRecord extends ListGridRecord {

	public MessageGridRecord( GWTMessageRecord source ) {
		super();
		setGWTMessageRecord( source );
	}

	public void setGWTMessageRecord( GWTMessageRecord source ) {

		setAttribute( MessageListFields.ID.name(), source.getId() );
	}
}
