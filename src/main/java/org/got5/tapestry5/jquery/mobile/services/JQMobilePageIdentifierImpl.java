package org.got5.tapestry5.jquery.mobile.services;

import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.annotations.Symbol;
import org.apache.tapestry5.services.ComponentEventLinkEncoder;
import org.apache.tapestry5.services.PageRenderRequestParameters;
import org.apache.tapestry5.services.Request;
import org.got5.tapestry5.jquery.mobile.JQueryMobileSymbolConstants;

public class JQMobilePageIdentifierImpl implements JQMobilePageIdentifier{

	private Request request;
	private ComponentEventLinkEncoder componentEventLinkEncoder;
	private String jQMobPagesSubPackage;
	
	public JQMobilePageIdentifierImpl(
			Request request, 
    		@Inject
    		@Symbol(JQueryMobileSymbolConstants.JQUERY_MOBILE_PAGES_SUBPACKAGE)
    		String jQMobPagesSubPackage,
    		ComponentEventLinkEncoder componentEventLinkEncoder
	){
		this.request = request;
		this.componentEventLinkEncoder = componentEventLinkEncoder;
		this.jQMobPagesSubPackage = jQMobPagesSubPackage;
	}
	
	public boolean isJQMobilePage() {
		//first test if its an XHR request
		if(request.isXHR()) {
    		PageRenderRequestParameters parameters = componentEventLinkEncoder.decodePageRenderRequest(request);
    		String requestedPageName = parameters.getLogicalPageName();

    		//requested page is not included in a specific package AND there is no jqmobpage subpackage
    		if(!requestedPageName.contains("/") && jQMobPagesSubPackage.equals(""))
    			return true;
    		//requested page belongs to a package AND this package is equals to the jqmobpage subpackage  
    		if( requestedPageName.contains("/") 
    			&& jQMobPagesSubPackage.equals(requestedPageName.substring(0, requestedPageName.lastIndexOf("/")).replace("/", ".")))
    			return true;
    	}
		return false;
	}
}
