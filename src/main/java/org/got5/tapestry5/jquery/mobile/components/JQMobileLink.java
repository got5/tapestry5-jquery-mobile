package org.got5.tapestry5.jquery.mobile.components;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.Link;
import org.apache.tapestry5.annotations.Events;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.corelib.base.AbstractComponentEventLink;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.annotations.Symbol;
import org.apache.tapestry5.services.PageRenderLinkSource;
import org.got5.tapestry5.jquery.mobile.JQueryMobileSymbolConstants;


@Events(EventConstants.ACTION)
public class JQMobileLink extends AbstractComponentEventLink
{
    @Inject
    private ComponentResources resources;

    @Parameter(required=true)
    private String jqmobPage;
    
    @Inject 
    private PageRenderLinkSource pageRenderLinkSource;
    
    @Inject
	@Symbol(JQueryMobileSymbolConstants.JQUERY_MOBILE_PAGES_SUBPACKAGE)
	private String jQMobPagesSubPackage;
	
    @Override
    protected Link createLink(Object[] contextArray)
    {
    	
    	String page = jQMobPagesSubPackage!="" ? jQMobPagesSubPackage+"/"+jqmobPage : jqmobPage;
    	if (contextArray!=null)
    		return pageRenderLinkSource.createPageRenderLinkWithContext(page, contextArray);
    	return pageRenderLinkSource.createPageRenderLinkWithContext(page);
    }
}
