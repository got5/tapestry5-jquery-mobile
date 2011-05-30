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

package org.got5.tapestry5.jquery.mobile.test.pages;

import java.util.Date;

import org.apache.tapestry5.Block;
import org.apache.tapestry5.EventConstants;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Request;
import org.got5.tapestry5.jquery.mobile.components.DateFieldMobile;
import org.got5.tapestry5.jquery.mobile.components.JQMPage;

@Import(stylesheet={"context:/css/jqm-docs.css"})
public class Test_datefield
{
	
	 @Property
	 private Date date;
	 
	 @Component(parameters={ "pageName=detail" })
	 private JQMPage detail;
	 
	 @Inject
	 private Block blockForm;
	 
	 @Component
	 private Form testDate;
	 
	 @Component(id = "date")
	 private DateFieldMobile dfm;
	 
	 @Inject
	 private Request request;
	 
	 public Block getBlockForm()
	 {
		return blockForm;
	 }
	 
	 @OnEvent(value = EventConstants.SUCCESS, component = "testDate")
	 Object updateZoneContentFromForm()
	 {
		Date now = new Date(); 
		if(date.before(now))
		{
			testDate.recordError(dfm, "must be later than now");
			return null;
		}	
		 
		 
		if (!request.isXHR())
		{
		    return this;
		}

		
	 //return blockForm;
	 return detail.redirectToMe();
	 }
	 
}
