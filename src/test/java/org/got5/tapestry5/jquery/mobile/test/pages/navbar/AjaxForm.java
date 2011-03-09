//
// Copyright 2010 GOT5 (GO Tapestry 5)
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
//

package org.got5.tapestry5.jquery.mobile.test.pages.navbar;


import org.apache.tapestry5.Block;
import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.ajax.MultiZoneUpdate;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.corelib.components.TextField;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Request;


public class AjaxForm
{
	 @Property
	    @Persist
	    private int count;

	    @Inject
	    private Request request;

	    @Inject
	    private Block myBlockActionLink;

	    
	    @Inject
	    private Block defaultBlock, multiUpdateBlock;

	    @InjectComponent
	    private org.apache.tapestry5.corelib.components.Zone multiZone1, multiZone2;
	    
	    @Property
	    @Persist
	    private String dummy;
	    
	    @Persist
	    private boolean afterFormSubmit;
	    
	    @Property
	    private int blockId;
	    
	    @Component(id="myForm")
	    private Form myForm;
	    
	    @Component(id="textFieldDummy", parameters ={"validate=required"})
		private TextField tf;
	    
	    @Component(id="formZone")
	    private Zone formZone;
	    
	    @Component(id="formZoneResult")
	    private Zone formZoneResult;

	    public Block getTheBlockActionLink()
	    {
		return myBlockActionLink;
	    }

	    
	    @OnEvent(value = "action", component = "myActionLink")
	    Object updateCount()
	    {
		if (!request.isXHR())
		{
		    return this;
		}
		count++;
		return myBlockActionLink;
	    }
	    
	    void onValidateForm() {
			
			if (dummy.trim().equals("dummy")) {
				myForm.recordError(tf, "dummy is not allowed");
			}
		}

		
		Object onFailure() {
			MultiZoneUpdate zoneUpdate =  new MultiZoneUpdate(formZoneResult).add(formZone);
			return zoneUpdate;
			
		}

	    @OnEvent(value = EventConstants.SUCCESS, component = "myForm")
	    Object updateZoneContentFromForm()
	    {
			if (!request.isXHR())
			{
			    return this;
			}

			MultiZoneUpdate zoneUpdate =  new MultiZoneUpdate(formZoneResult).add(formZone);
			return zoneUpdate;
	    }
	    
	    @OnEvent(value = EventConstants.SUCCESS, component = "myMultiZoneUpdateForm")
	    Object performMultiZoneUpdate() 
	    {
	        afterFormSubmit = true;

	        return new MultiZoneUpdate("multiZone1", multiZone1.getBody()).add("multiZone2", multiZone2.getBody()); 
	    }
	    
	    public Block getMultiUpdateBlock1() {

	        blockId = 1;
	        
	        return afterFormSubmit ? multiUpdateBlock : defaultBlock;
	    }
	    
	    public Block getMultiUpdateBlock2() {

	        blockId = 2;
	        
	        return afterFormSubmit ? multiUpdateBlock : defaultBlock;
	    }
	 
}
