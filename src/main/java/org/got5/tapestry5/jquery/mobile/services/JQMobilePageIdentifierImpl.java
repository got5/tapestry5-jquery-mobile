package org.got5.tapestry5.jquery.mobile.services;

import java.util.Collection;

import org.apache.tapestry5.internal.InternalConstants;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.annotations.Symbol;
import org.apache.tapestry5.ioc.services.ClassNameLocator;
import org.apache.tapestry5.services.ComponentEventLinkEncoder;
import org.apache.tapestry5.services.PageRenderRequestParameters;
import org.apache.tapestry5.services.Request;
import org.got5.tapestry5.jquery.mobile.JQueryMobileSymbolConstants;

public class JQMobilePageIdentifierImpl implements JQMobilePageIdentifier{

	private Request request;
	private String appRootPackage;
	private ComponentEventLinkEncoder componentEventLinkEncoder;
	private ClassNameLocator classNameLocator;
	private String jQMobPagesSubPackage;
	
	public JQMobilePageIdentifierImpl(
			Request request, 
    		@Inject
    	    @Symbol(InternalConstants.TAPESTRY_APP_PACKAGE_PARAM)
    		String appRootPackage,
    		@Inject
    		@Symbol(JQueryMobileSymbolConstants.JQUERY_MOBILE_PAGES_SUBPACKAGE)
    		String jQMobPagesSubPackage,
    		ComponentEventLinkEncoder componentEventLinkEncoder,
    		ClassNameLocator classNameLocator
	){
		this.request = request;
		this.appRootPackage = appRootPackage;
		this.componentEventLinkEncoder = componentEventLinkEncoder;
		this.classNameLocator = classNameLocator;
		this.jQMobPagesSubPackage = jQMobPagesSubPackage;
	}
	
	public boolean isJQMobilePage() {
		//first test if its an XHR request
		if(request.isXHR()) {
    		PageRenderRequestParameters parameters = componentEventLinkEncoder.decodePageRenderRequest(request);
    		String requestedPageName = parameters.getLogicalPageName();
    		if(requestedPageName.contains("/"))
    			requestedPageName=requestedPageName.substring(requestedPageName.lastIndexOf("/"));
    		String pck = jQMobPagesSubPackage=="" ? 
    				appRootPackage+"."+InternalConstants.PAGES_SUBPACKAGE : 
    				appRootPackage+"."+InternalConstants.PAGES_SUBPACKAGE+"."+jQMobPagesSubPackage; 
    		
    		Collection<String> listOfPages = classNameLocator.locateClassNames(pck);
    		if(requestedPageName.startsWith("/"))
    			requestedPageName = requestedPageName.substring(1);
    		String test = pck+"."+requestedPageName.replace("/", ".");
    		//then test if the requested page is in the subpackage pages.jquerymobilepages
    		if(listOfPages.contains(test)){
    			return true;
    		}
		}
		return false;
	}
}
