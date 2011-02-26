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


import org.apache.tapestry5.internal.InternalConstants;
import org.apache.tapestry5.ioc.Configuration;
import org.apache.tapestry5.ioc.MappedConfiguration;
import org.apache.tapestry5.ioc.OrderedConfiguration;
import org.apache.tapestry5.services.ComponentClassTransformWorker;
import org.apache.tapestry5.services.LibraryMapping;
import org.apache.tapestry5.services.javascript.JavaScriptStack;
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

  
    
    public static void contributeFactoryDefaults(MappedConfiguration<String, String> configuration)
    {
       
        configuration.add(JQUERY_MOBILE_CORE_PATH, "classpath:org/got5/tapestry5/jquery/mobile/jquery.mobile_core");
        configuration.add(JQUERY_MOBILE_VERSION, "1.0a3");
    }
    
    public static void contributeClasspathAssetAliasManager(MappedConfiguration<String, String> configuration)
    {
        configuration.add("tap-jquery-mobile", "org/got5/tapestry5");
    }
    
   

}
