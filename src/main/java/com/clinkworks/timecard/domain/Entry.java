package com.clinkworks.timecard.domain;

import org.joda.time.DateTime;

import com.clinkworks.timecard.service.TimeService;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

public class Entry {
	private final DateTime timeStamp;
	
	@Inject
	public Entry(@Assisted DateTime timeStamp){
		this.timeStamp = timeStamp;
	}
	
	public DateTime getTimeStamp(){
		return timeStamp;
	}
	
	
}
