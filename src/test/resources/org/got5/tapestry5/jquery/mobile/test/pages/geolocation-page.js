(function(){
	$(document).bind("map_beforeAddingMarker", function(e, poi, marker, map){
		//let's change the icon :)
		marker.icon=poi.icon;
		
		//let's display an info window when the user click the marker
		google.maps.event.addListener(marker, 'click', function() {
			var infowindow = new google.maps.InfoWindow({
			    content: poi.markerContent
			});
			infowindow.open(map,marker);
		});
	});
})(jQuery)