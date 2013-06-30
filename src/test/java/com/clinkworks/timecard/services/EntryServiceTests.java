package com.clinkworks.timecard.services;

import org.joda.time.DateTime;
import org.jukito.JukitoRunner;
import org.jukito.UseModules;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.clinkworks.timecard.config.TestCaseConfigBase;
import com.clinkworks.timecard.datatypes.Entry;
import com.clinkworks.timecard.service.EntryService;
import com.clinkworks.timecard.service.TestSystemTimeService;

@RunWith(JukitoRunner.class)
@UseModules({ TestCaseConfigBase.class })
public class EntryServiceTests{
	
	@Test
	public void testServiceCanCreateEntry(EntryService entryService){
		
		Entry entry = entryService.createEntry();
		
		Assert.assertNotNull(entry);
		
		DateTime now = new DateTime();
		
		Entry manualEntry = entryService.createEntry(now);
		
		Assert.assertNotNull(manualEntry);
		
		Assert.assertEquals(manualEntry.getTimeStamp(), now);
		
	}
	
	@Test
	public void testEntryContainsSystemTimestamp(EntryService entryService, TestSystemTimeService timeService){
		
		DateTime initalTime = timeService.useTestTime().getSystemTime();
		
		Entry entry = entryService.createEntry();
		
		Assert.assertEquals(initalTime, entry.getTimeStamp());
		
		DateTime timeStampTomorrow = timeService.addDay();
		
		Entry entryTomorrow = entryService.createEntry();
		
		Assert.assertEquals(timeStampTomorrow, entryTomorrow.getTimeStamp());

	}

}
