package com.clinkworks.timecard.api;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import com.clinkworks.timecard.datatypes.Entry;
import com.clinkworks.timecard.util.TestCaseBase;


public class EntryServiceTests extends TestCaseBase{
	
	@Test
	public void testServiceCanCreateEntry(){
		Entry entry = getEntryService().createEntry();
		
		Assert.assertNotNull(entry);
	}
	
	@Test
	public void testEntryContainsSystemTimestamp(){
		this.toggleTimeMockOn();
		try{
			Date timeStamp = getTimeService().getSystemTime();
			
			Entry entry = getEntryService().createEntry();
			
			Assert.assertEquals(timeStamp, entry.getTimeStamp());
		}finally{
			this.toggleTimeMockOff();
		}		
	}
	
}
