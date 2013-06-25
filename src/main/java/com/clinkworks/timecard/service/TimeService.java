package com.clinkworks.timecard.service;

import java.util.Date;

import com.google.inject.Singleton;

@Singleton
public class TimeService {
	
	public TimeService(){
	
	}
	
	public Date getSystemTime(){
		return new Date();
	}
}
