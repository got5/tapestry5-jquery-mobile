package org.got5.tapestry5.jquery.mobile.components;

import java.util.Collections;
import java.util.List;

import org.apache.tapestry5.ComponentEventCallback;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.ContentType;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.internal.util.Holder;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.services.TypeCoercer;
import org.apache.tapestry5.json.JSONArray;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.services.MarkupWriterFactory;
import org.apache.tapestry5.services.ResponseRenderer;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;
import org.got5.tapestry5.jquery.mobile.data.GeoPosition;

@Import(library="GeolocationToMap.js")
public class GeolocationToMap {

	/*
	 * Configuration passed to the map component
	 */
	@Parameter
	private JSONObject mapConfiguration;
	
	/*
	 * Configuration passed to the geolocation component
	 */
	@Parameter
	private JSONObject geolocConfiguration;
	
	/*
	 * If true (default), everytime the user changes location, all markers are removed from the map
	 */
	@Parameter(value="true")
	private boolean removeMarkers;
	
	/*
	 * If true (default), displays the user's position on the map (each time his/her position changes)
	 */
	@Parameter(value="true")
	private boolean displayPosition;
	
	@Inject
	private ComponentResources resources;
	
	@Inject
    private TypeCoercer coercer;
	
	@Inject
    private ResponseRenderer responseRenderer;
	
	@Inject
    private MarkupWriterFactory factory;
	
	@InjectComponent
	private Map map;
	
	@Inject
	private JavaScriptSupport jsSupport;
	
	@SetupRender
	public void render(){
		
		JSONObject o = new JSONObject();
		o.put("mapId", map.getClientId());
		o.put("removeMarkers", removeMarkers);
		o.put("displayPosition", displayPosition);
		
		jsSupport.addInitializerCall("GeolocationToMap", o);
		
	}
	
	public static final String PROVIDE_POI = "PROVIDE_POI";
	
	@OnEvent(Geolocation.PROVIDE_GEOLOCATION)
	public JSONObject askForPOIs(GeoPosition position){
		
		final Holder<List> matchesHolder = Holder.create();

        // Default it to an empty list.

        matchesHolder.put(Collections.emptyList());

        ComponentEventCallback callback = new ComponentEventCallback()
        {
            public boolean handleResult(Object result)
            {
                List matches = coercer.coerce(result, List.class);

                matchesHolder.put(matches);

                return true;
            }
        };

        resources.triggerEvent(PROVIDE_POI, new Object[] { position }, callback);

        ContentType contentType = responseRenderer.findContentType(this);

        MarkupWriter writer = factory.newPartialMarkupWriter(contentType);

        JSONObject result = new JSONObject();
        result.put("POIs", generateResponseJSON(matchesHolder.get()));
        return result;
	}
	
	protected JSONArray generateResponseJSON (List matches){
    	JSONArray JSONAr = new JSONArray();
    	for (Object o : matches)
        {	
    		JSONAr.put(o);
        }
    	return JSONAr;
    }
	
}
