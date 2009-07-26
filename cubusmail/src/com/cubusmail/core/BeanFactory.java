/* BeanFactory.java

   Copyright (c) 2009 J�rgen Schlierf, All Rights Reserved
   
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

package com.cubusmail.core;

import org.springframework.context.ApplicationContext;

/**
 * Create spring beans.
 * 
 * @author J�rgen Schlierf
 */
public abstract class BeanFactory {

	private static ApplicationContext context;

	/**
	 * @param context
	 *            The context to set.
	 */
	public static void setContext( ApplicationContext context ) {

		BeanFactory.context = context;
	}

	public static Object getBean( String beanId ) {

		return context.getBean( beanId );
	}
}
