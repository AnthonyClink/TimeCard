package com.clinkworks.timecard.service;


import org.joda.time.DateTime;

import com.google.inject.Singleton;

@Singleton
public class TimeService {
	
	public TimeService(){
	
	}
	
	public DateTime getSystemTime(){
		return new DateTime();
	}
}
