package com.clinkworks.timecard.component;

import java.sql.Date;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TemporalType;

import org.joda.time.DateTime;

import com.clinkworks.timecard.domain.TimecardEntry;
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
public class TimecardEntryComponent{

	@Inject
	private TimecardComponentFactory TCCF;
	
	private SystemTimeComponent timeService;
	
	@Inject 
	private EntityManager em;
	
	@Inject
	public TimecardEntryComponent(SystemTimeComponent timeService){
		this.timeService = timeService;
	}
	
	@Transactional
	public TimecardEntry createTimecardEntry(){
		TimecardEntry retval = TCCF.createNewTimecardEntry(timeService.getSystemTime());
		em.persist(retval);
		return retval;
	}
	
	@Transactional
	public TimecardEntry createTimecardEntry(DateTime timeStamp) {
		TimecardEntry retval = TCCF.createNewTimecardEntry(timeStamp);
		em.persist(retval);
		return retval;
	}

	@Transactional
	public void deleteTimecardEntry(TimecardEntry entry) {
		em.remove(entry);
	}	
	
	@Transactional
	public void deleteTimecardEntries(List<TimecardEntry> entries){
		for(TimecardEntry entry : entries){
			em.remove(entry);
		}
	}
	
	/**
	 * deletes the entry with the specified id.
	 * @param id
	 */
	@Transactional
	public void deleteTimecardEntryById(Long id){
		TimecardEntry entry = em.find(TimecardEntry.class, id);
		if(entry != null){
			em.remove(entry);
		}
	}
	
	public TimecardEntry getTimecardEntryById(Long id) {
		return em.find(TimecardEntry.class, id);
	}	
	
	public List<TimecardEntry> getTimecardEntriesBetween(DateTime startTime, DateTime endTime) {
		//TODO: once jodatime mappings are working, get rid of this call.
		Date startDate = new Date(startTime.getMillis());
		Date endDate = new Date(endTime.getMillis());
		
		return em.createNamedQuery("TimecardEntry.findAllBetween", TimecardEntry.class).
			setParameter("start", startDate, TemporalType.TIMESTAMP).
			setParameter("end",endDate, TemporalType.TIMESTAMP).getResultList();
		
	}

	public List<TimecardEntry> getTimecardEntriesBetweenSortedAcending(DateTime startTime, DateTime endTime){
		//lists returned from JPA are immutable. This allows the sorting to happen.
		List<TimecardEntry> retval = Lists.newArrayList(getTimecardEntriesBetween(startTime, endTime));
		sortTimecardEntriesAcending(Lists.newArrayList(retval));
		return retval;
	}

	public List<TimecardEntry> getTimecardEntriesBetweenSortedDecending(DateTime startTime, DateTime endTime){
		//lists returned from JPA are immutable. This allows the sorting to happen.
		List<TimecardEntry> retval = Lists.newArrayList(getTimecardEntriesBetween(startTime, endTime));
		sortTimecardEntriesDecending(retval);
		return retval;
	}	
	
	public List<TimecardEntry> sortTimecardEntriesDecending(List<TimecardEntry> entries){
		return sortTimecardEntries(entries, new DecendingEntryComparator());
	}
	
	public List<TimecardEntry> sortTimecardEntriesAcending(List<TimecardEntry> entries){
		return sortTimecardEntries(entries, new AscendingEntryComparator());
	}
	
	private List<TimecardEntry> sortTimecardEntries(List<TimecardEntry> entries, Comparator<TimecardEntry> comparator){
		
		Collections.sort(entries, comparator);
		
		return entries;
	}
	
	public static class AscendingEntryComparator implements Comparator<TimecardEntry>{

		@Override
		public int compare(TimecardEntry entry1, TimecardEntry entry2) {
			return entry1.compareTo(entry2);
		}
		
	}
	
	public static class DecendingEntryComparator implements Comparator<TimecardEntry>{

		@Override
		public int compare(TimecardEntry entry1, TimecardEntry entry2) {
			return entry1.compareTo(entry2) * -1;
		}		
		
	}

}
