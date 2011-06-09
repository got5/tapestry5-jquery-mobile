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

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

import org.apache.commons.lang.ArrayUtils;
import org.apache.tapestry5.ContentType;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.internal.services.DocumentLinker;
import org.apache.tapestry5.internal.services.PageContentTypeAnalyzer;
import org.apache.tapestry5.internal.services.PageMarkupRenderer;
import org.apache.tapestry5.internal.services.PageResponseRenderer;
import org.apache.tapestry5.internal.services.ajax.JavaScriptSupportImpl;
import org.apache.tapestry5.internal.services.javascript.JavaScriptStackPathConstructor;
import org.apache.tapestry5.internal.structure.Page;
import org.apache.tapestry5.ioc.Configuration;
import org.apache.tapestry5.ioc.Invocation;
import org.apache.tapestry5.ioc.MappedConfiguration;
import org.apache.tapestry5.ioc.MethodAdvice;
import org.apache.tapestry5.ioc.MethodAdviceReceiver;
import org.apache.tapestry5.ioc.OrderedConfiguration;
import org.apache.tapestry5.ioc.ServiceBinder;
import org.apache.tapestry5.ioc.annotations.Advise;
import org.apache.tapestry5.ioc.annotations.Symbol;
import org.apache.tapestry5.ioc.util.IdAllocator;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.services.ComponentClassTransformWorker;
import org.apache.tapestry5.services.Environment;
import org.apache.tapestry5.services.LibraryMapping;
import org.apache.tapestry5.services.MarkupRenderer;
import org.apache.tapestry5.services.MarkupRendererFilter;
import org.apache.tapestry5.services.MarkupWriterFactory;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.RequestGlobals;
import org.apache.tapestry5.services.Response;
import org.apache.tapestry5.services.javascript.JavaScriptStack;
import org.apache.tapestry5.services.javascript.JavaScriptStackSource;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;
import org.got5.tapestry5.jquery.mobile.JQueryMobileSymbolConstants;
import org.got5.tapestry5.jquery.mobile.services.javascript.JQueryMobileDateFieldStack;
import org.got5.tapestry5.jquery.mobile.services.javascript.JQueryMobileJavaScriptStack;
import org.slf4j.Logger;



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
       
        configuration.add(JQueryMobileSymbolConstants.JQUERY_MOBILE_CORE_PATH, "classpath:org/got5/tapestry5/jquery/mobile/jquery.mobile_core");
        configuration.add(JQueryMobileSymbolConstants.JQUERY_MOBILE_VERSION, "1.0a4.1");
        configuration.add(JQueryMobileSymbolConstants.JQUERY_MOBILE_PAGES_SUBPACKAGE, "m");
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
    
    public static void bind(ServiceBinder binder) {
    	binder.bind(JQMobilePageIdentifier.class, JQMobilePageIdentifierImpl.class);
    }
    
    
    /*
     * Encapsulate the response in a JSON object in order to work with tapestry Zones
     * Because we assume that an AJAX request asking for a page included in the "jquerymobilepages" subpackage should be handled by the zonemanager
     */
    @Advise(serviceInterface = PageResponseRenderer.class)
    public static void advisePageRenderer(MethodAdviceReceiver receiver, 
    		final RequestGlobals requestGlobals, 
    		final MarkupWriterFactory markupWriterFactory,
    		final PageMarkupRenderer markupRenderer, 
    		final PageContentTypeAnalyzer pageContentTypeAnalyzer, 
    		final Response response,
    		final Logger logger, 
    		final JQMobilePageIdentifier jQMobilePageIdentifier) 
    {
      MethodAdvice advice = new MethodAdvice()
      {
    	  
        public void advise(Invocation invocation)
        {
        	if(jQMobilePageIdentifier.isJQMobilePage()){
		        	Page page = null;
			        for(int i=0; i<invocation.getParameterCount(); i++){
			        	if(invocation.getParameterType(i).equals(Page.class))
			        		page=(Page)invocation.getParameter(i);
			        }
		          
		        	  assert page != null;
		
		              requestGlobals.storeActivePageName(page.getName());
		
		              ContentType contentType = pageContentTypeAnalyzer.findContentType(page);
		              contentType=new ContentType("application/json");
		
		              MarkupWriter writer = markupWriterFactory.newMarkupWriter(contentType);
		
		              markupRenderer.renderPageMarkup(page, writer);
		
		              PrintWriter pw;
					try {
						pw = response.getPrintWriter(contentType.toString());
						long startNanos = System.nanoTime();
		
			              long endNanos = System.nanoTime();
		
			              if (logger.isDebugEnabled())
			              {
			                  long elapsedNanos = endNanos - startNanos;
			                  double elapsedSeconds = ((float) elapsedNanos) / 1000000000F;
		
			                  logger.debug(String.format("Response DOM streamed to markup in %.3f seconds", elapsedSeconds));
			              }
		
			              
			              JSONObject o = new JSONObject();
			              o.put("content", writer.toString());
			              pw.write(o.toCompactString());
			              pw.close();
			              
					} catch (IOException e) {
						e.printStackTrace();
					}
        		}
        		else 
            		invocation.proceed();
        }
      };

      
      for (Method m : receiver.getInterface().getMethods())
      {
        if (ArrayUtils.contains(m.getParameterTypes(), Page.class))
          receiver.adviseMethod(m, advice);
      }
    };
    
    /*
     * Don't include javascript core stack
     * Because we assume that a browser making an AJAX request asking for a page belonging to the "jquerymobilepages" subpackage package 
     * already has the core js stack
     */
    public void contributeMarkupRenderer(OrderedConfiguration<MarkupRendererFilter> configuration,

    	    @Symbol(SymbolConstants.OMIT_GENERATOR_META)
    	    final boolean omitGeneratorMeta,

    	    @Symbol(SymbolConstants.TAPESTRY_VERSION)
    	    final String tapestryVersion,

    	    @Symbol(SymbolConstants.COMPACT_JSON)
    	    final boolean compactJSON,
    	    
    	    final Environment environment, 
    	    
    		final Request request, 
    		
    		final JavaScriptStackSource javascriptStackSource,

    	    final JavaScriptStackPathConstructor javascriptStackPathConstructor, 
    	    
    	    final JQMobilePageIdentifier jQMobilePageIdentifier
    	    )
    	    {

		    	MarkupRendererFilter javaScriptSupport = new MarkupRendererFilter()
		        {
		            public void renderMarkup(MarkupWriter writer, MarkupRenderer renderer)
		            {
		                DocumentLinker linker = environment.peekRequired(DocumentLinker.class);
		                JavaScriptSupportImpl support;
		                
		                if(jQMobilePageIdentifier.isJQMobilePage()){
		                	String uid = Long.toHexString(System.currentTimeMillis());
		                	String namespace = "_" + uid;
		                    IdAllocator idAllocator = new IdAllocator(namespace);
		                	support = new JavaScriptSupportImpl(linker, javascriptStackSource,
		                        javascriptStackPathConstructor, idAllocator, true);
		                }
		                else support = new JavaScriptSupportImpl(linker, javascriptStackSource,
		                        javascriptStackPathConstructor);
		
		                environment.push(JavaScriptSupport.class, support);
		
		                renderer.renderMarkup(writer);
		
		                environment.pop(JavaScriptSupport.class);
		
		                support.commit();
		            }
		        };
    	        configuration.override("JavaScriptSupport", javaScriptSupport, "after:DocumentLinker");
    	    }
    

}
