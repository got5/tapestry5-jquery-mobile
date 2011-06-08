package org.got5.tapestry5.jquery.mobile.components;

import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.Link;
import org.apache.tapestry5.annotations.AfterRender;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;

@Import(library="JqMobilePageContainer.js")
public class JqMobilePageContainer {
	
	  
	@Inject  
	private JavaScriptSupport support;  
	  
	@Inject  
	private ComponentResources resources;  
	    
	
	
	@AfterRender
	public void addJSInit(){
		JSONObject params = new JSONObject();
		support.addInitializerCall("jqlink", params);
	}
}
