package com.clinkworks.timecard.service;

import javax.persistence.EntityManager;

import org.joda.time.DateTime;

import com.clinkworks.timecard.datatypes.Entry;
import com.clinkworks.timecard.util.TimecardComponentFactory;
import com.google.inject.Inject;
import com.google.inject.persist.Transactional;

/**
 * This service provides the ability to manipulate entries.
 * @author AnthonyJCLink
 *
 */
public class EntryService {

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
	
	public Entry createEntry(DateTime timeStamp) {
		return TCCF.createNewEntry(timeStamp);
	}

}
