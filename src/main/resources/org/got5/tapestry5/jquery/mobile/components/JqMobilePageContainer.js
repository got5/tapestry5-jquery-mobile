(function($){
    $.extend(Tapestry.Initializer, {
        jqlink: function(specs) {
    		$(document).bind(Tapestry.ZONE_UPDATED_EVENT, function(a){
    			var page = $(a.target).children();
    			$("body").append(page);
    			page.each(function(){
    				var $this=$(this);
    				if($this.attr("data-role")=="page"){
    					var id = $this.attr("id");
    					$( $.mobile.initializePage );
    					$.mobile.changePage(id);
    				}
    			});
    		});
        }
    });
})(jQuery)