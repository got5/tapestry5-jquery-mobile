(function($) {
        $.extend($.ui.tapestryZone.prototype, {
        	update: function(specs) {
	        
			var that = this;
			
	        var ajaxRequest = {
	            url: specs.url,
	            success: function(data) {
	                
					if(data.redirectToJQMPage)
					{
						$.mobile.changePage ($('#'+data.redirectToJQMPage.pageName))
					}
					else if (data.content) {

		                that.applyContentUpdate(data.content);

					} else if (data.zones) {

	                    // perform multi zone update
						$.each(data.zones, function(zoneId){

							$('#' + zoneId).tapestryZone("applyContentUpdate", data.zones[zoneId]);
						});
						
					}

	                $.tapestry.utils.loadScriptsInReply(data);
	            }
	        };
	        
	        if (specs.params) {
	            ajaxRequest = $.extend(ajaxRequest, {
	                type: 'post',
	                data: specs.params
	            });
	        }
	        
	        $.ajax(ajaxRequest);
	    },
	    /**
		 * Updates the element's content and triggers the appropriate effect on the
		 * zone.
		 * 
		 * @param {Object} content the new content for this zone's body
		 */
		applyContentUpdate: function(content) {

			if (!content) {

				console.log("WARN: content is undefined. Aborting update for zone: " + this.element.attr("id"));
				return;
			}

			var el = this.element;
		
			var effect = el.is(":visible") ? this.options.update : this.options.show;
			//force Jquery mobile to enhance new content
			el.html(content).page().effect(effect);
	        el.trigger(Tapestry.ZONE_UPDATED_EVENT); 
		}
        });
})(jQuery);
