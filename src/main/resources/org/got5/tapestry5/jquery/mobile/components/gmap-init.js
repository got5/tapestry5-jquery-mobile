(function($){
	$.extend(Tapestry.Initializer, { GMap: function(params)
		{
			var map = $("#"+params.clientId).gmap(params);
			$(document).trigger("map-ready", map);
		}
});
})
(jQuery)