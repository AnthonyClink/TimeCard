package com.clinkworks.timecard.service;

import org.joda.time.DateTime;

import com.clinkworks.timecard.domain.Entry;
import com.clinkworks.timecard.util.TimecardComponentFactory;
import com.google.inject.Inject;

public class EntryService {

	
	@Inject
	private TimecardComponentFactory TCCF;
	
	private TimeService timeService;
	
	@Inject
	public EntryService(TimeService timeService){
		this.timeService = timeService;
	}
	
	public Entry createEntry(){
		return TCCF.createNewEntry(timeService.getSystemTime());
	}
	
	public Entry createEntry(DateTime timeStamp) {
		return TCCF.createNewEntry(timeStamp);
	}

}
