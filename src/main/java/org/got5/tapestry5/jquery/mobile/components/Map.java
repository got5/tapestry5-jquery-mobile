package org.got5.tapestry5.jquery.mobile.components;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.BeginRender;
import org.apache.tapestry5.annotations.Environmental;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.services.AssetSource;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;

public class Map {

	@Parameter
	private JSONObject config;
	
    @Environmental
    private JavaScriptSupport jsSupport;
    
    @Inject
    private ComponentResources _resources;
    
    private String id;
    
    @Inject
    private AssetSource assetSource;
    
    @BeginRender
    void beginRender (MarkupWriter writer){
    	writer.element("div",
                "id", getClientId());

		 	 
		writer.end();
		
    	writer.element("script",
                "type", "text/javascript",
                "src", "http://maps.google.com/maps/api/js?sensor=true");

		 	 
		writer.end();
		
		//can't use @Import(library={ ... }) because jquery.ui.map calls "google" which is not defined (because loaded from http://maps.google.com/maps/api/js?sensor=true)
    	writer.element("script",
                "type", "text/javascript",
                "src", assetSource.getClasspathAsset("org/got5/tapestry5/jquery/mobile/components/jquery.ui.map.js", null).toClientURL());

		 	 
		writer.end();
		 
    	writer.element("script",
                "type", "text/javascript",
                "src", assetSource.getClasspathAsset("org/got5/tapestry5/jquery/mobile/components/gmap-init.js", null).toClientURL());

		 	 
		writer.end();
    }
    
    @SetupRender
	void setupRender(MarkupWriter writer)
    {
		 
		id = jsSupport.allocateClientId(_resources.getId());
        
        _resources.renderInformalParameters(writer);

        if(!_resources.isBound("config"))
        	config = new JSONObject();
        config.put("clientId", id);
        jsSupport.addInitializerCall("GMap", config);
    }
	
	public String getClientId(){
		return id;
	}
	
}
