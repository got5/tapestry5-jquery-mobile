(function($){
	$.extend(Tapestry.Initializer, { GeolocationToMap : function(params)
	{
		function displayOnMap(){
			var marker = new google.maps.Marker({
				'position':new google.maps.LatLng(poi.latitude, poi.longitude)
		});
		$map.trigger("map_beforeAddingMarker", [poi, marker, $map.gmap("getMap")]);
		$map.gmap("addMarker", marker );
		}
		$(document).bind("positionFoundAfterAjax", 
				function(event, data){
					var $map = $("#"+params.mapId);
					if(params.removeMarkers)
						$map.gmap("clearMarkers");
					console.log($.mobile.geolocation.position.latitude);
					if(params.displayPosition)
						$map.gmap("addMarker", new google.maps.Marker({
								'position':new google.maps.LatLng($.mobile.geolocation.position.coords.latitude, $.mobile.geolocation.position.coords.longitude)
						}));
					$.each(data.POIs, function(i, poi){
						var marker = new google.maps.Marker({
								'position':new google.maps.LatLng(poi.latitude, poi.longitude)
						});
						$map.trigger("map_beforeAddingMarker", [poi, marker, $map.gmap("getMap")]);
						$map.gmap("addMarker", marker );
					});
				}
		);
	}
	});
})(jQuery)