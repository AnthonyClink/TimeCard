package com.clinkworks.timecard.services;

import java.util.List;

import org.joda.time.DateTime;
import org.jukito.JukitoRunner;
import org.jukito.UseModules;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.clinkworks.timecard.config.TestCaseConfigBase;
import com.clinkworks.timecard.domain.Entry;
import com.clinkworks.timecard.service.EntryService;
import com.clinkworks.timecard.service.TestSystemTimeService;
import com.google.common.collect.Lists;

@RunWith(JukitoRunner.class)
@UseModules({ TestCaseConfigBase.class })
public class TestEntryService{
	
	@Test
	public void testServiceCanCreateEntry(EntryService entryService){
		
		Entry entry = entryService.createEntry();
		
		assertNotNull(entry);
		
		DateTime now = new DateTime();
		
		Entry manualEntry = entryService.createEntry(now);
		
		assertNotNull(manualEntry);
		
		assertEquals(manualEntry.getTimeStamp(), now);
		
	}
	
	@Test
	public void testEntryContainsSystemTimestamp(EntryService entryService, TestSystemTimeService timeService){
		
		DateTime initalTime = timeService.useTestTime().getSystemTime();
		
		Entry entry = entryService.createEntry();
		
		assertEquals(initalTime, entry.getTimeStamp());
		
		DateTime timeStampTomorrow = timeService.addDay();
		
		Entry entryTomorrow = entryService.createEntry();
		
		assertEquals(timeStampTomorrow, entryTomorrow.getTimeStamp());

	}
	
	@Test
	public void entriesCanCompareThemselves(EntryService entryService, TestSystemTimeService timeService){
		
		DateTime morning = timeService.resetClockToJanuaryFirstTwoThousand().useTestTime().getSystemTime();
		
		DateTime noon = morning.plusHours(12);
		
		DateTime night = noon.plusHours(12).minusSeconds(1);
		
		Entry morningEntry = entryService.createEntry();
		Entry noonEntry = entryService.createEntry(noon);
		Entry nightEntry = entryService.createEntry(night);
		
		assertTrue(nightEntry.isAfter(noonEntry));
		assertTrue(nightEntry.isAfter(morningEntry));
		
		assertTrue(noonEntry.isAfter(morningEntry));
		assertTrue(noonEntry.isBefore(nightEntry));
		
		assertTrue(morningEntry.isBefore(noonEntry));
		assertTrue(morningEntry.isBefore(nightEntry));
		
		DateTime newMorning = timeService.resetClockToJanuaryFirstTwoThousand().getSystemTime();//gets a new time reference so object equality
		                                                  //isn't an issue.
		
		assertEquals(-1, morning.compareTo(night));
		assertEquals(0, morning.compareTo(newMorning));
		assertEquals(1, noon.compareTo(morning));
		
	}
	
	@Test
	public void entriesCanBeSorted(EntryService entryService, TestSystemTimeService timeService){
		DateTime morning = timeService.resetClockToJanuaryFirstTwoThousand().useTestTime().getSystemTime();
		
		DateTime noon = morning.plusHours(12);
		
		DateTime night = noon.plusHours(12).minusSeconds(1);
		
		Entry morningEntry = entryService.createEntry();
		Entry noonEntry = entryService.createEntry(noon);
		Entry nightEntry = entryService.createEntry(night);
		
		List<Entry> listToSort = Lists.newArrayList(nightEntry, noonEntry, morningEntry);
		
		entryService.sortEntriesAcending(listToSort);
		
		assertEquals(morningEntry, listToSort.get(0));
		assertEquals(noonEntry, listToSort.get(1));
		assertEquals(nightEntry, listToSort.get(2));
		
		entryService.sortEntriesDecending(listToSort);
		
		assertEquals(nightEntry, listToSort.get(0));
		assertEquals(noonEntry, listToSort.get(1));
		assertEquals(morningEntry, listToSort.get(2));
	}

}
