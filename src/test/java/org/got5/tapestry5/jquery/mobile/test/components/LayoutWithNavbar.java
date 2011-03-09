//
// Copyright 2010 GOT5 (GO Tapestry 5)
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// 	http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
//

package org.got5.tapestry5.jquery.mobile.test.components;
//
//Copyright 2010 GOT5 (GO Tapestry 5)
//
//Licensed under the Apache License, Version 2.0 (the "License");
//you may not use this file except in compliance with the License.
//You may obtain a copy of the License at
//
//	http://www.apache.org/licenses/LICENSE-2.0
//
//Unless required by applicable law or agreed to in writing, software
//distributed under the License is distributed on an "AS IS" BASIS,
//WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//See the License for the specific language governing permissions and
//limitations under the License.
//

import java.util.ArrayList;
import java.util.List;

import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.got5.tapestry5.jquery.mobile.components.Layout;
import org.got5.tapestry5.jquery.mobile.data.NavBarItem;
import org.got5.tapestry5.jquery.mobile.services.javascript.JQueryMobileJavaScriptStack;
import org.got5.tapestry5.jquery.utils.JQueryTabData;

@Import(stack = JQueryMobileJavaScriptStack.STACK_ID, stylesheet={"context:/css/jqm-docs.css"})
public class LayoutWithNavbar 
{
	@SuppressWarnings("unused")
	@Property
    private String pageName;

    @SuppressWarnings("unused")
    @Property
    @Parameter(required = true, defaultPrefix = BindingConstants.LITERAL)
    private String pageTitle;

    @Inject
    private ComponentResources resources;
    
	@Property
	private List<NavBarItem> navBarItems;

	@SetupRender
    void init(final MarkupWriter writer)
	{
		writer.getDocument().raw("<!DOCTYPE html>");
        this.pageName = resources.getPageName();
        navBarItems = new ArrayList<NavBarItem>();
        navBarItems.add(new NavBarItem("NavBar/AjaxForm"));
        navBarItems.add(new NavBarItem("NavBar/ValidateForm"));
        navBarItems.add(new NavBarItem("NavBar/FormFragment"));
        navBarItems.add(new NavBarItem("NavBar/Calendar"));
        
	}
    

}
