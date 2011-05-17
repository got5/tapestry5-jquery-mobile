package org.got5.tapestry5.jquery.mobile.components;

import org.apache.tapestry5.ComponentEventCallback;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.ContentType;
import org.apache.tapestry5.Link;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.Environmental;
import org.apache.tapestry5.annotations.Events;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.ResponseRenderer;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;
import org.apache.tapestry5.util.TextStreamResponse;
import org.got5.tapestry5.jquery.mobile.data.GeoPosition;
import org.got5.tapestry5.jquery.mobile.services.javascript.JQueryMobileJavaScriptStack;
import org.got5.tapestry5.jquery.utils.JQueryUtils;

@Import(library="geolocation.js", stack=JQueryMobileJavaScriptStack.STACK_ID)
@Events(Geolocation.GEOLOCATION_EVENT)
public class Geolocation {

	/*
	 * If given, an onclick event will be added to the button holding the given ID.
	 * Then, on the user interface, clicking this button will call the getPosition method, applied to the geoSuccess method.
	 * If an ID is given, user position won't be automatically refreshed.
	 */
	@Parameter(defaultPrefix="literal")
	private String buttonId;
	
	/*
	 * If true, then an AJAX request is sent to the server each time the device changes location.
	 * 
	 * The container is responsible for providing an event handler for event "geolocation" (Geolocation.GEOLOCATION_EVENT).  
	 * The context will be the current position sent from the client. 
	 * Return a JSONObject containing the data you want to have on the client.
	 * On Ajax success, the content of the response is triggered in a "positionFoundAfterAjax" event.
	 * 
	 * I.e.
	 * <pre>
	 * Object onGeolocationFromMyComponent(GeoPosition position)
	 * {
	 *   return new JSONObject(...);
	 * }
	 * </pre>
	 */
	@Parameter
	private boolean ajax; 
	
	/*
	 * A JSONObject you can use to override defaults parameters.
	 * 
	 */
	@Parameter
	private JSONObject params;
	
    @Environmental
    private JavaScriptSupport jsSupport;
    
    @Inject
    private ComponentResources _resources;
    
    @Inject
    private Request request;
    
    @Inject
    private ResponseRenderer responseRenderer;
    
    public static final String GEOLOCATION_EVENT = "geolocation";
    
    public static final String PROVIDE_GEOLOCATION="provideGeolocation";
    
    @SetupRender
	void setupRender(MarkupWriter writer)
    {
		JSONObject o = new JSONObject();
		
		if(_resources.isBound("ajax"))
			if(ajax){
				Link link = _resources.createEventLink(GEOLOCATION_EVENT);
				o.put("url", link.toAbsoluteURI());
			}

		/* writer.element("script",
	                "type", "text/javascript",
	                "src", "http://code.google.com/apis/gears/gears_init.js");

			 writer.end();*/
		
		
		if(_resources.isBound("buttonId")){
			o.put("buttonId", buttonId);
		}
	
		JQueryUtils.merge(o, params);
		
        _resources.renderInformalParameters(writer);
        
        jsSupport.addInitializerCall("Geolocation", o);
    }

	private JSONObject r;
	
	@OnEvent(GEOLOCATION_EVENT)
	Object onGeolocation()
    {
        GeoPosition position = new GeoPosition(request.getParameter("heading"), 
        								request.getParameter("altitude"), 
        								request.getParameter("latitude"), 
        								request.getParameter("accuracy"), 
        								request.getParameter("longitude"), 
        								request.getParameter("speed"), 
        								request.getParameter("altitudeAccuracy"), 
        								request.getParameter("timestamp"));
        
        ComponentEventCallback<JSONObject> callback = new ComponentEventCallback<JSONObject>()
        {
            public boolean handleResult(JSONObject result)
            {
            	r = result;
                return true;
            }
        };

        _resources.triggerEvent(PROVIDE_GEOLOCATION, new Object[] { position }, callback);
        ContentType contentType = responseRenderer.findContentType(this);
		return new TextStreamResponse(contentType.toString(), r.toString());
    }
	
}
