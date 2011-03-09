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

package org.got5.tapestry5.jquery.mobile.services;

import static org.got5.tapestry5.jquery.mobile.JQueryMobileSymbolConstants.JQUERY_MOBILE_CORE_PATH;
import static org.got5.tapestry5.jquery.mobile.JQueryMobileSymbolConstants.JQUERY_MOBILE_VERSION;


import org.apache.tapestry5.Link;
import org.apache.tapestry5.StreamResponse;
import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.ajax.MultiZoneUpdate;
import org.apache.tapestry5.internal.InternalConstants;
import org.apache.tapestry5.internal.services.AjaxComponentInstanceEventResultProcessor;
import org.apache.tapestry5.internal.services.AjaxLinkComponentEventResultProcessor;
import org.apache.tapestry5.internal.services.AjaxPageClassComponentEventResultProcessor;
import org.apache.tapestry5.internal.services.AjaxPageNameComponentEventResultProcessor;
import org.apache.tapestry5.internal.services.ComponentInstanceProcessor;
import org.apache.tapestry5.internal.services.JSONArrayEventResultProcessor;
import org.apache.tapestry5.internal.services.JSONObjectEventResultProcessor;
import org.apache.tapestry5.internal.services.RenderCommandComponentEventResultProcessor;
import org.apache.tapestry5.internal.services.StreamResponseResultProcessor;
import org.apache.tapestry5.ioc.Configuration;
import org.apache.tapestry5.ioc.MappedConfiguration;
import org.apache.tapestry5.ioc.OrderedConfiguration;
import org.apache.tapestry5.ioc.annotations.Contribute;
import org.apache.tapestry5.json.JSONArray;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.runtime.Component;
import org.apache.tapestry5.runtime.RenderCommand;
import org.apache.tapestry5.services.Ajax;
import org.apache.tapestry5.services.ComponentClassTransformWorker;
import org.apache.tapestry5.services.ComponentEventResultProcessor;
import org.apache.tapestry5.services.LibraryMapping;
import org.apache.tapestry5.services.Traditional;
import org.apache.tapestry5.services.ajax.MultiZoneUpdateEventResultProcessor;
import org.apache.tapestry5.services.javascript.JavaScriptStack;
import org.got5.tapestry5.jquery.mobile.components.JQMPage;
import org.got5.tapestry5.jquery.mobile.services.javascript.JQueryMobileDateFieldStack;
import org.got5.tapestry5.jquery.mobile.services.javascript.JQueryMobileJavaScriptStack;



public class JQueryMobileModule
{
    public static void contributeJavaScriptStackSource(MappedConfiguration<String, JavaScriptStack> configuration)
    {
    	configuration.addInstance(JQueryMobileDateFieldStack.STACK_ID, JQueryMobileDateFieldStack.class);
    	configuration.addInstance(JQueryMobileJavaScriptStack.STACK_ID, JQueryMobileJavaScriptStack.class); 
        
        
    }

    
    public static void contributeComponentClassTransformWorker(OrderedConfiguration<ComponentClassTransformWorker> configuration)
    {
    	
    }

    public static void contributeComponentClassResolver(Configuration<LibraryMapping> configuration)
    {
        configuration.add(new LibraryMapping("jquery-mobile", "org.got5.tapestry5.jquery.mobile"));
    }

    public static void contributeApplicationDefaults(MappedConfiguration<String,String> configuration)
    {
      //to allow Native jQueryNavigation
      configuration.add(SymbolConstants.SUPPRESS_REDIRECT_FROM_ACTION_REQUESTS, "false");
    }

  
    
    public static void contributeFactoryDefaults(MappedConfiguration<String, String> configuration)
    {
       
        configuration.add(JQUERY_MOBILE_CORE_PATH, "classpath:org/got5/tapestry5/jquery/mobile/jquery.mobile_core");
        configuration.add(JQUERY_MOBILE_VERSION, "1.0a3");
      
    }
    
    public static void contributeClasspathAssetAliasManager(MappedConfiguration<String, String> configuration)
    {
        configuration.add("tap-jquery-mobile", "org/got5/tapestry5");
    }
    
    /*@Contribute(ComponentEventResultProcessor.class)
    @Ajax
    public static void ContributeComponentEventResultProcessors(
            MappedConfiguration<Class, ComponentEventResultProcessor> configuration)
    {
        configuration.addInstance(JQMPage.class, jQueryMobilePageComponentEventResultProcessor.class);
    }

    public void contributeComponentEventResultProcessor(@Ajax
    	    @ComponentInstanceProcessor
    	    ComponentEventResultProcessor componentInstanceProcessor,

    	    MappedConfiguration<Class, ComponentEventResultProcessor> configuration)
    {
    	  configuration.addInstance(JQMPage.class, jQueryMobilePageComponentEventResultProcessor.class);
    	  
    }*/

}
