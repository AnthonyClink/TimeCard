package com.clinkworks.timecard.service;

import java.util.Date;

import com.clinkworks.timecard.datatypes.Entry;
import com.clinkworks.timecard.util.TimecardComponentFactory;
import com.google.inject.Inject;
import com.google.inject.Provider;

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
	
	public Entry createEntry(Date timeStamp) {
		return TCCF.createNewEntry(timeStamp);
	}

}
