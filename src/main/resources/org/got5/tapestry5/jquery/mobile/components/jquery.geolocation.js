(function($){
	$.mobile.geolocation=function(options){
		$.mobile.geolocation.position={};
		var defaults={
			url:null,
			buttonId:"",
			positionParameters:{},
			handleNoGeolocation:function(){
				alert("Geolocation failed");
			}, 
			geoError:function(){ }
		};
		var opts = $.extend(defaults, options);
		var geoSuccess=function(position){
			$(document).trigger("positionFound", position);
			if(opts.url){
				position.coords.timestamp=position.timestamp;
				$.ajax({
					url:opts.url,
					data:position.coords,
					dataType:"json",
					success:function(data){
						$(document).trigger("positionFoundAfterAjax", data);
					}
				});
			}
			$.mobile.geolocation.position=position;
		};
		if ( navigator.geolocation ) {
			var watch;
			if(opts.buttonId)
				$("#"+opts.buttonId).click(function(){
					watch = navigator.geolocation.getCurrentPosition(geoSuccess);
				});
			else 
				watch = navigator.geolocation.watchPosition(geoSuccess, opts.geoError, opts.positionParameters);
			
			$.mobile.geolocation.stop = function(){
				navigator.geolocation.clearWatch(watch);
			};
		}
		else{
			opts.handleNoGeolocation();
		}
	};
	
	$.extend(Tapestry.Initializer, { Geolocation : function(params)
										{
											$.mobile.geolocation(params);
										}
	});
	
})(jQuery)