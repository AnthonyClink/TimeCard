package com.clinkworks.timecard.service;

import java.sql.Date;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TemporalType;

import org.joda.time.DateTime;

import com.clinkworks.timecard.domain.Entry;
import com.clinkworks.timecard.util.TimecardComponentFactory;
import com.google.common.collect.Lists;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.persist.Transactional;

/**
 * This service provides the ability to manipulate entries.

 * @author AnthonyJCLink
 *
 */
@Singleton
public class EntryService{

	@Inject
	private TimecardComponentFactory TCCF;
	
	private SystemTimeService timeService;
	
	@Inject 
	private EntityManager em;
	
	@Inject
	public EntryService(SystemTimeService timeService){
		this.timeService = timeService;
	}
	
	@Transactional
	public Entry createEntry(){
		Entry retval = TCCF.createNewEntry(timeService.getSystemTime());
		em.persist(retval);
		return retval;
	}
	
	@Transactional
	public Entry createEntry(DateTime timeStamp) {
		Entry retval = TCCF.createNewEntry(timeStamp);
		em.persist(retval);
		return retval;
	}

	@Transactional
	public void deleteEntry(Entry entry) {
		em.remove(entry);
	}	
	
	@Transactional
	public void deleteEntries(List<Entry> entries){
		for(Entry entry : entries){
			em.remove(entry);
		}
	}
	
	/**
	 * deletes the entry with the specified id.
	 * @param id
	 */
	@Transactional
	public void deleteEntryById(Long id){
		Entry entry = em.find(Entry.class, id);
		if(entry != null){
			em.remove(entry);
		}
	}
	
	public Entry getEntryById(Long id) {
		return em.find(Entry.class, id);
	}	
	
	public List<Entry> getEntriesBetween(DateTime startTime, DateTime endTime) {
		//TODO: once jodatime mappings are working, get rid of this call.
		Date startDate = new Date(startTime.getMillis());
		Date endDate = new Date(endTime.getMillis());
		
		return em.createNamedQuery("Entry.findAllBetween", Entry.class).
			setParameter("start", startDate, TemporalType.TIMESTAMP).
			setParameter("end",endDate, TemporalType.TIMESTAMP).getResultList();
		
	}

	public List<Entry> getEntriesBetweenSortedAcending(DateTime startTime, DateTime endTime){
		//lists returned from JPA are immutable. This allows the sorting to happen.
		List<Entry> retval = Lists.newArrayList(getEntriesBetween(startTime, endTime));
		sortEntriesAcending(Lists.newArrayList(retval));
		return retval;
	}

	public List<Entry> getEntriesBetweenSortedDecending(DateTime startTime, DateTime endTime){
		//lists returned from JPA are immutable. This allows the sorting to happen.
		List<Entry> retval = Lists.newArrayList(getEntriesBetween(startTime, endTime));
		sortEntriesDecending(retval);
		return retval;
	}	
	
	public List<Entry> sortEntriesDecending(List<Entry> entries){
		return sortEntries(entries, new DecendingEntryComparator());
	}
	
	public List<Entry> sortEntriesAcending(List<Entry> entries){
		return sortEntries(entries, new AscendingEntryComparator());
	}
	
	private List<Entry> sortEntries(List<Entry> entries, Comparator<Entry> comparator){
		
		Collections.sort(entries, comparator);
		
		return entries;
	}
	
	public static class AscendingEntryComparator implements Comparator<Entry>{

		@Override
		public int compare(Entry entry1, Entry entry2) {
			return entry1.compareTo(entry2);
		}
		
	}
	
	public static class DecendingEntryComparator implements Comparator<Entry>{

		@Override
		public int compare(Entry entry1, Entry entry2) {
			return entry1.compareTo(entry2) * -1;
		}		
		
	}

}
