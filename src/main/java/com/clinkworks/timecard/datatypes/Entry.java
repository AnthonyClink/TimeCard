package com.clinkworks.timecard.datatypes;

import java.util.Date;

import com.clinkworks.timecard.service.TimeService;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

public class Entry {
	private final Date timeStamp;
	
	@Inject
	public Entry(@Assisted Date timeStamp){
		this.timeStamp = timeStamp;
	}
	
	public Date getTimeStamp(){
		return timeStamp;
	}
	
	
}
