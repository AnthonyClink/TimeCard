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

import com.clinkworks.timecard.config.JpaConfig;
import com.clinkworks.timecard.config.TestCaseConfigBase;
import com.clinkworks.timecard.datatypes.Entry;
import com.clinkworks.timecard.service.EntryService;
import com.clinkworks.timecard.service.TestSystemTimeService;
import com.google.common.collect.Lists;

@RunWith(JukitoRunner.class)
@UseModules({ TestCaseConfigBase.class, JpaConfig.class })
public class TestEntryServiceIntegration {
	
	@Test
	public void testEntryServiceCanPersistEntries(EntryService entryService, EntityManager entityManager){
		
		List<Entry> entriesToRemove = new ArrayList<Entry>();
		
		try{
			
			Entry entry = entryService.createEntry();
			
			entriesToRemove.add(entry);
			
			assertNotNull(entry);
			
			Entry queriedEntry = entryService.getEntryById(entry.getId());
			
			assertNotNull(queriedEntry);
			assertEquals(entry.getId(), queriedEntry.getId());
			assertTrue(entry.getTimeStamp().isEqual(queriedEntry.getTimeStamp()));
			
		}finally{
			cleanupEntries(entityManager, entriesToRemove);
		}
	}
	
	@Test
	public void testEntryServiceCanQueryDateRange(EntityManager entityManager, EntryService entryService, TestSystemTimeService timeService){
		
		timeService.resetClockToJanuaryFirstTwoThousand().useTestTime();
		List<Entry> entriesToCleanup = new ArrayList<Entry>();
		
		try{
			//want to give a buffer time to make query by range work easier.
			DateTime startTime = timeService.resetClockToJanuaryFirstTwoThousand().useTestTime().getSystemTime();
			DateTime endTime = startTime.plusDays(1);
			
			Entry entry1 = entryService.createEntry();
			timeService.addHour();
			Entry entry2 = entryService.createEntry();
			timeService.addDay();
			Entry entry3 = entryService.createEntry();
			
			entriesToCleanup.add(entry1);
			entriesToCleanup.add(entry2);
			entriesToCleanup.add(entry3);
			
			timeService.resetClockToJanuaryFirstTwoThousand();
			
			List<Entry> queriedEntries = Lists.newArrayList(entryService.getEntriesBetween(startTime, endTime));
			
			
			assertEquals(2, queriedEntries.size());
			
			assertEquals(entry1.getId(), queriedEntries.get(0).getId());
			assertEquals(entry2.getId(), queriedEntries.get(1).getId());
			
			
		}finally{
			
			cleanupEntries(entityManager, entriesToCleanup);
			timeService.useRealTime();
			
		}
		
		
	}
	
	@Test
	public void testEntryCanBeDeleted(EntryService entryService, EntityManager entityManager){

		Entry entry = entryService.createEntry();
		
		entryService.deleteEntry(entry);
		
		Entry nullEntry = entryService.getEntryById(entry.getId());
		
		assertNull(nullEntry);
		
	}
	
	@Test
	public void testEntrysCanBeDeleted(EntryService entryService, EntityManager entityManager, TestSystemTimeService timeService){
		
		DateTime timeStamp = timeService.setTestTimeToNow().useTestTime().addYears(5);
		
		DateTime start = timeStamp.minusHours(1);
		DateTime end = timeStamp.plusHours(1);
		
		List<Entry> entriesToRemove = Lists.newArrayList(
			entryService.createEntry(),
			entryService.createEntry(),
			entryService.createEntry(),
			entryService.createEntry(),
			entryService.createEntry(),
			entryService.createEntry(),
			entryService.createEntry(),
			entryService.createEntry()
		);
		
		List<Entry> queriedEntries = entryService.getEntriesBetween(start, end);
		
		assertEquals(entriesToRemove.size(), queriedEntries.size());
		
		entryService.deleteEntries(entriesToRemove);
		
		queriedEntries = entryService.getEntriesBetween(start, end);
		
		assertEquals(0, queriedEntries.size());
			
	} 
	
	private void cleanupEntries(EntityManager entityManager, List<Entry> entries){
		
		EntityTransaction transaction = entityManager.getTransaction();
		
		transaction.begin();
		
		for(Entry entry : entries){
			entityManager.remove(entry);
		}
		
		transaction.commit();
		
	}
}