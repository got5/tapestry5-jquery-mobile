(function(){
	$(document).bind("map_beforeAddingMarker", function(e, poi, marker, map){
		//let's change the icon :)
		marker.icon=poi.icon;
		
		//let's display an info window when the user click the marker
		map.gmap("addInfoWindow", {
		    content: poi.markerContent
		}, function(iw){
			$(marker).click(function() {
				iw.open(map.gmap("getMap"), marker);
			});
		});
	});
})(jQuery)