package com.clinkworks.timecard.api;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;

import com.clinkworks.timecard.domain.Entry;
import com.clinkworks.timecard.util.TestCaseBase;


public class EntryServiceTests extends TestCaseBase{
	
	@Test
	public void testServiceCanCreateEntry(){
		Entry entry = getEntryService().createEntry();
		
		Assert.assertNotNull(entry);
	}
	
	@Test
	public void testEntryContainsSystemTimestamp(){
		
		DateTime initalTime = getTestingClockService().useTestTime().getSystemTime();
		
		Entry entry = getEntryService().createEntry();
		
		Assert.assertEquals(initalTime, entry.getTimeStamp());
		
		DateTime timeStampTomorrow = getTestingClockService().addDay();
		
		Entry entryTomorrow = getEntryService().createEntry();
		
		Assert.assertEquals(timeStampTomorrow, entryTomorrow.getTimeStamp());

	}

}
