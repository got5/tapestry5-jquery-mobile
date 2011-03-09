//
// Copyright 2010 GOT5 (GO Tapestry 5)
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
//
package org.got5.tapestry5.jquery.mobile.components;

import java.util.ArrayList;
import java.util.List;


import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONObject;

import org.apache.tapestry5.services.AssetSource;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;
import org.got5.tapestry5.jquery.mobile.data.NavBarItem;
import org.got5.tapestry5.jquery.utils.JQueryTabData;
import org.slf4j.Logger;

public class NavBar 
{
	@Inject
	private ComponentResources resources;

    @Inject
    private AssetSource source;

	@Property
	@Parameter(required=true)
	private ArrayList<NavBarItem> navBarItems;

	@Property
	private NavBarItem currentElement;

	@Persist
	private String currentPageName;
	
	@Parameter
	private int ActiveBarIndex;

	@Property
	private int currentBarIndex;

	@Inject
	private Logger logger;

	private Messages i18Labels;
	
	
	@SetupRender
	public void readParameters() {

		i18Labels = resources.getContainerMessages();
		if (resources.isBound("activeBarIndex")) currentPageName = navBarItems.get(ActiveBarIndex).getPageName();	
		else currentPageName = resources.getPageName();		
	}


	public boolean getBarOn() {
			return currentElement.getPageName().equalsIgnoreCase(currentPageName);
	}

	public boolean getLink() {
		return !currentElement.getPageName().startsWith("#");
	}
		
	public String getPageLinkWithHash()
	{
		return resources.getPageName()+currentElement.getPageName();
	}

}
