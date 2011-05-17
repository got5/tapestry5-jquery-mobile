package org.got5.tapestry5.jquery.mobile.test.pages;

import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.json.JSONObject;
import org.got5.tapestry5.jquery.mobile.components.Geolocation;
import org.got5.tapestry5.jquery.mobile.data.GeoPosition;

public class GeolocationPage {

	@OnEvent(Geolocation.PROVIDE_GEOLOCATION)
	public JSONObject hop(GeoPosition p){
		System.out.println(p.toString());
		JSONObject o = new JSONObject();
		o.put("a", "b");
		return o;
	}
}
