package com.clinkworks.timecard.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.joda.time.DateTime;
import org.jukito.JukitoRunner;

import org.jukito.UseModules;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.clinkworks.timecard.api.TimeSegmentService;
import com.clinkworks.timecard.component.TimecardEntryComponent;
import com.clinkworks.timecard.config.JpaConfig;
import com.clinkworks.timecard.config.TestCaseConfigBase;
import com.clinkworks.timecard.domain.TimecardEntry;
import com.clinkworks.timecard.util.SystemTimeUtil;
import com.google.common.collect.Lists;

@RunWith(JukitoRunner.class)
@UseModules({ TestCaseConfigBase.class, JpaConfig.class })
public class TestTimecardEntryComponentIntegration {
	
	@Test
	public void testSegmentServiceCanPersistEntries(TimeSegmentService segmentService){
		TimecardEntry entry = (TimecardEntry)segmentService.getTimecardEntry().getEntity();
		assertNotNull(entry);
	}
	
	@Test
	public void testEntryServiceCanPersistEntries(TimecardEntryComponent entryService, EntityManager entityManager){
		
		List<TimecardEntry> entriesToRemove = new ArrayList<TimecardEntry>();
		
		try{
			
			TimecardEntry entry = entryService.createTimecardEntry();
			
			entriesToRemove.add(entry);
			
			assertNotNull(entry);
			
			TimecardEntry queriedEntry = entryService.getTimecardEntryById(entry.getId());
			
			assertNotNull(queriedEntry);
			assertEquals(entry.getId(), queriedEntry.getId());
			
			assertTrue(entry.getTimestamp().isEqual(queriedEntry.getTimestamp()));
			
		}finally{
			cleanupEntries(entityManager, entriesToRemove);
		}
	}
	
	@Test
	public void testEntryServiceCanQueryDateRange(EntityManager entityManager, TimecardEntryComponent entryService, SystemTimeUtil timeService){
		
		DateTime startTime = timeService.resetClockToJanuaryFirstTwoThousand().useTestTime().getSystemTime();
		DateTime endTime = startTime.plusDays(1);
		
		List<TimecardEntry> entriesToCleanup = new ArrayList<TimecardEntry>();
		
		try{
			//want to give a buffer time to make query by range work easier.
			TimecardEntry entry1 = entryService.createTimecardEntry();
			timeService.addHour();
			TimecardEntry entry2 = entryService.createTimecardEntry();
			timeService.addDay();
			TimecardEntry entry3 = entryService.createTimecardEntry();
			
			entriesToCleanup.add(entry1);
			entriesToCleanup.add(entry2);
			entriesToCleanup.add(entry3);
			
			List<TimecardEntry> queriedEntries = Lists.newArrayList(entryService.getTimecardEntriesBetween(startTime, endTime));
			
			
			assertEquals(2, queriedEntries.size());
			
			assertEquals(entry1.getId(), queriedEntries.get(0).getId());
			assertEquals(entry2.getId(), queriedEntries.get(1).getId());
			
			
		}finally{
			
			cleanupEntries(entityManager, entriesToCleanup);
			timeService.useRealTime();
			
		}
		
		
	}
	
	@Test
	public void testEntryCanBeDeleted(TimecardEntryComponent entryService, EntityManager entityManager) throws InterruptedException{

		TimecardEntry entry = entryService.createTimecardEntry();
		
		entryService.deleteTimecardEntry(entry);
		
		TimecardEntry nullEntry = entryService.getTimecardEntryById(entry.getId());
		
		assertNull(nullEntry);
		
		TimecardEntry newEntry = entryService.createTimecardEntry();
		
		Long id = newEntry.getId();
		
		assertNotNull(newEntry);
		
		entryService.deleteTimecardEntryById(id);
		
		TimecardEntry newNullEntry = entryService.getTimecardEntryById(id);
		
		assertNull(newNullEntry);
		
	}
	
	@Test
	public void testEntrysCanBeDeleted(TimecardEntryComponent entryService, SystemTimeUtil timeService){
		
		DateTime timeStamp = timeService.setTestTimeToNow().useTestTime().addYears(5).getSystemTime();
		
		DateTime start = timeStamp.minusHours(1);
		DateTime end = timeStamp.plusHours(1);
		
		List<TimecardEntry> entriesToRemove = Lists.newArrayList(
			entryService.createTimecardEntry(),
			entryService.createTimecardEntry(),
			entryService.createTimecardEntry(),
			entryService.createTimecardEntry(),
			entryService.createTimecardEntry(),
			entryService.createTimecardEntry(),
			entryService.createTimecardEntry(),
			entryService.createTimecardEntry()
		);
		
		List<TimecardEntry> queriedEntries = entryService.getTimecardEntriesBetween(start, end);
		
		//reason to check is its greater, just incase the test failed in the middle.
		//since this is an integration test... jpa will not allow delete to be called on 
		//twice on the same id, so... this is a bit of a safeguard is something else went wrong
		//either in the entity, configuration, etc.
		assertTrue(entriesToRemove.size() >= 8);
		
		entryService.deleteTimecardEntries(queriedEntries);
		
		queriedEntries = entryService.getTimecardEntriesBetween(start, end);
		
		assertEquals(0, queriedEntries.size());
			
	} 
	
	private void cleanupEntries(EntityManager entityManager, List<TimecardEntry> entries){
		
		EntityTransaction transaction = entityManager.getTransaction();
		
		transaction.begin();
		
		for(TimecardEntry entry : entries){
			if(entityManager.contains(entry.getEntry())){
				entityManager.remove(entry.getEntry());
			}
		}
		
		transaction.commit();
		
	}
}
