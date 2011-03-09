package org.got5.tapestry5.jquery.mobile.components;

import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.ClientElement;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.json.JSONArray;
import org.apache.tapestry5.json.JSONObject;

public class JQMPage implements ClientElement{
	
	@Parameter(value = "prop:componentResources.id", defaultPrefix = BindingConstants.LITERAL)
    private String clientId;
	
	@Parameter(defaultPrefix = BindingConstants.LITERAL)
	private String pageName;

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	public String getClientId() {
		return clientId;
	}
	
	public JSONObject redirectToMe()
	{
		JSONObject ret = new JSONObject();
		JSONObject page = new JSONObject();
		page.put("pageName",pageName);
		ret.put("redirectToJQMPage",page);
		return ret;
	}
}
