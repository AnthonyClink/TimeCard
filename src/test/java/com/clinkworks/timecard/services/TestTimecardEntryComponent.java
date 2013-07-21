package com.clinkworks.timecard.services;

import java.util.List;

import org.joda.time.DateTime;
import org.jukito.JukitoRunner;
import org.jukito.UseModules;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.clinkworks.timecard.component.TimecardEntryComponent;
import com.clinkworks.timecard.config.TestCaseConfigBase;
import com.clinkworks.timecard.domain.TimecardEntry;
import com.clinkworks.timecard.util.SystemTimeUtil;
import com.google.common.collect.Lists;

@RunWith(JukitoRunner.class)
@UseModules({ TestCaseConfigBase.class })
public class TestTimecardEntryComponent{
	
	@Test
	public void testServiceCanCreateEntry(TimecardEntryComponent entryComponent){
		
		TimecardEntry entry = entryComponent.createTimecardEntry();
		
		assertNotNull(entry);
		
		DateTime now = new DateTime();
		
		TimecardEntry manualEntry = entryComponent.createTimecardEntry(now);
		
		assertNotNull(manualEntry);
		
		assertEquals(manualEntry.getTimestamp(), now);
		
	}
	
	@Test
	public void testEntryContainsSystemTimestamp(TimecardEntryComponent entryComponent, SystemTimeUtil timeComponent){
		
		DateTime initalTime = timeComponent.useTestTime().getSystemTime();
		
		TimecardEntry entry = entryComponent.createTimecardEntry();
		
		assertEquals(initalTime, entry.getTimestamp());
		
		DateTime timeStampTomorrow = timeComponent.addDay().getSystemTime();
		
		TimecardEntry entryTomorrow = entryComponent.createTimecardEntry();
		
		assertEquals(timeStampTomorrow, entryTomorrow.getTimestamp());

	}
	
	@Test
	public void entriesCanCompareThemselves(TimecardEntryComponent entryComponent, SystemTimeUtil timeComponent){
		
		DateTime morning = timeComponent.resetClockToJanuaryFirstTwoThousand().useTestTime().getSystemTime();
		
		DateTime noon = morning.plusHours(12);
		
		DateTime night = noon.plusHours(12).minusSeconds(1);
		
		TimecardEntry morningEntry = entryComponent.createTimecardEntry();
		TimecardEntry noonEntry = entryComponent.createTimecardEntry(noon);
		TimecardEntry nightEntry = entryComponent.createTimecardEntry(night);
		
		assertTrue(nightEntry.isAfter(noonEntry));
		assertTrue(nightEntry.isAfter(morningEntry));
		
		assertTrue(noonEntry.isAfter(morningEntry));
		assertTrue(noonEntry.isBefore(nightEntry));
		
		assertTrue(morningEntry.isBefore(noonEntry));
		assertTrue(morningEntry.isBefore(nightEntry));
		
		DateTime newMorning = timeComponent.resetClockToJanuaryFirstTwoThousand().getSystemTime();//gets a new time reference so object equality
		                                                  //isn't an issue.
		
		assertEquals(-1, morning.compareTo(night));
		assertEquals(0, morning.compareTo(newMorning));
		assertEquals(1, noon.compareTo(morning));
		
	}
	
	@Test
	public void entriesCanBeSorted(TimecardEntryComponent entryComponent, SystemTimeUtil timeComponent){
		DateTime morning = timeComponent.resetClockToJanuaryFirstTwoThousand().useTestTime().getSystemTime();
		
		DateTime noon = morning.plusHours(12);
		
		DateTime night = noon.plusHours(12).minusSeconds(1);
		
		TimecardEntry morningEntry = entryComponent.createTimecardEntry();
		TimecardEntry noonEntry = entryComponent.createTimecardEntry(noon);
		TimecardEntry nightEntry = entryComponent.createTimecardEntry(night);
		
		List<TimecardEntry> listToSort = Lists.newArrayList(noonEntry, nightEntry, morningEntry);
		
		entryComponent.sortTimecardEntriesAcending(listToSort);
		
		assertEquals(morningEntry, listToSort.get(0));
		assertEquals(noonEntry, listToSort.get(1));
		assertEquals(nightEntry, listToSort.get(2));
		
		entryComponent.sortTimecardEntriesDecending(listToSort);
		
		assertEquals(nightEntry, listToSort.get(0));
		assertEquals(noonEntry, listToSort.get(1));
		assertEquals(morningEntry, listToSort.get(2));
	}

}
