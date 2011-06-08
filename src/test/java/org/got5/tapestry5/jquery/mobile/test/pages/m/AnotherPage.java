package org.got5.tapestry5.jquery.mobile.test.pages.m;

import java.util.ArrayList;
import java.util.List;

import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.corelib.components.Zone;


public class AnotherPage {

	@Property
	private String test;


	
	@Property
	private String ctx;
	
	@OnEvent(EventConstants.ACTIVATE)
	public void onActivate(String test){
		ctx=test;
	}

	@OnEvent(EventConstants.PROVIDE_COMPLETIONS)
	public List<String> provi(String input){
		ArrayList<String> result = new ArrayList<String>();
		result.add("aa");
		result.add("bb");
		result.add("cc");
		return result;
	}
	
}
