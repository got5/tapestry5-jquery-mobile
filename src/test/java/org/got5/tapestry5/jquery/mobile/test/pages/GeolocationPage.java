package org.got5.tapestry5.jquery.mobile.test.pages;

import java.util.ArrayList;
import java.util.List;

import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.json.JSONLiteral;
import org.apache.tapestry5.json.JSONObject;
import org.got5.tapestry5.jquery.mobile.components.GeolocationToMap;
import org.got5.tapestry5.jquery.mobile.data.GeoPosition;

@Import(library="geolocation-page.js")
public class GeolocationPage {

	@OnEvent(GeolocationToMap.PROVIDE_POI)
	public List<JSONObject> hop(GeoPosition p){
		
		ArrayList<JSONObject> result = new ArrayList<JSONObject>();
		
		JSONObject o = new JSONObject();
		o.put("latitude", 50.553751);
		o.put("longitude", 3.030454);
		o.put("icon", "http://chart.apis.google.com/chart?chst=d_map_pin_icon&chld=restaurant|FFFF00");
		o.put("markerContent", "Chez Josette<br/>Yum yum");
		result.add(o);
		
		JSONObject o2 = new JSONObject();
		o2.put("latitude", 50.535947);
		o2.put("longitude", 3.054452);
		o2.put("icon", "http://chart.apis.google.com/chart?chst=d_map_pin_icon&chld=bar|FFFF00");
		o2.put("markerContent", "El troquet du coin<br/>Cheers !");
		result.add(o2);
		
		return result;
	}
	
	@Property
	private JSONObject config;
	
	@SetupRender
	void setupRender(){
		config = new JSONObject();
		config.put("center", new JSONLiteral("new google.maps.LatLng(50.550851, 3.030454)"));
		config.put("zoom", 12);
	}
}
