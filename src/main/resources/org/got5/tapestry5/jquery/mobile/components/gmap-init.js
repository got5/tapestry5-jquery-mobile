(function($){
	$.extend(Tapestry.Initializer, { GMap: function(params)
		{
			$("#"+params.clientId).gmap(params);
		}
});
})
(jQuery)