(function($){
	$.extend(Tapestry.Initializer, { GeolocationToMap : function(params)
	{
		$(document).bind("positionFoundAfterAjax", 
				function(event, data){
					var $map = $("#"+params.mapId);
					if(params.removeMarkers)
						$map.gmap("clearMarkers");
					if(params.displayPosition)
						$map.gmap("addMarker", new google.maps.Marker({
								'position':new google.maps.LatLng($.mobile.geolocation.position.coords.latitude, $.mobile.geolocation.position.coords.longitude)
						}), function(map, marker){
							map.panTo(marker.getPosition());
						});
					$.each(data.POIs, function(i, poi){
						var marker = new google.maps.Marker({
								'position':new google.maps.LatLng(poi.latitude, poi.longitude)
						});
						$map.trigger("map_beforeAddingMarker", [poi, marker, $map]);
						$map.gmap("addMarker", marker );
					});
				}
		);
	}
	});
})(jQuery)