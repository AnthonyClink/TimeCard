package com.clinkworks.timecard.services;

import java.util.Calendar;
import java.util.Date;

import com.clinkworks.timecard.service.TimeService;
import com.google.inject.Singleton;

/**
 * This class keeps track of the system time.
 * This service is provided as a means to unit test system time sensitive processes.
 * @author AnthonyJCLink
 *
 */
@Singleton
public class MockTimeService extends TimeService{
	
	private Date systemDate;
	private boolean useMockTime = false;
	
	public MockTimeService(){
		Calendar cal = Calendar.getInstance();
		cal.set(2000, 0, 1, 0, 0);
		systemDate = cal.getTime();
	}
	
	public void switchMockTimeOn(){
		useMockTime = true;
	}
	
	public void switchMockTimeOff(){
		useMockTime = false;
	}
	
	public TimeService setSystemTime(Date date){
		systemDate = date;
		return this;
	}	
	
	@Override
	public Date getSystemTime(){
		if(useMockTime && systemDate != null){
			return systemDate;
		}else{
			return new Date();
		}
	}
}
