package com.clinkworks.timecard.services;

import java.util.Calendar;
import java.util.Date;

import org.joda.time.DateTime;

import com.clinkworks.timecard.service.TimeService;
import com.google.inject.Singleton;

/**
 * This class keeps track of the system time.
 * This service is provided as a means to unit test system time sensitive processes.
 * @author AnthonyJCLink
 *
 */
@Singleton
public final class TestingClockService extends TimeService{
	
	public static final DateTime JANUARY_FIRST_TWO_THOUSAND = new DateTime(2000, 1, 1, 0, 0, 0, 0);
	
	private DateTime systemDate;
	private boolean useRealTime;
	
	public TestingClockService(){
		systemDate = JANUARY_FIRST_TWO_THOUSAND;
		useRealTime = false;
	}
	
	public final DateTime resetClockToJanuaryFirstTwoThousand(){
		systemDate = JANUARY_FIRST_TWO_THOUSAND;
		return systemDate;
	}
	
	public TestingClockService useRealTime(){
		useRealTime = true;
		return this;
	}
	
	public TestingClockService useTestTime(){
		useRealTime = false;
		return this;
	}
	
	public TimeService setSystemTime(DateTime date){
		systemDate = date;
		return this;
	}	
	
	@Override
	public DateTime getSystemTime(){
		
		if(useRealTime){
			return new DateTime();
		}
		
		if(systemDate == null){
			resetClockToJanuaryFirstTwoThousand();
		}
		
		return systemDate;

	}
	
	public DateTime addYear(){
		systemDate = systemDate.plusYears(1);
		return systemDate;
	}

	public DateTime addMonth(){
		systemDate = systemDate.plusMonths(1);
		return systemDate;
	}
	
	public DateTime addDay(){
		systemDate = systemDate.plusDays(1);
		return systemDate;
	}
	
	public DateTime addHour(){
		systemDate = systemDate.plusHours(1);
		return systemDate;
	}
	
	public DateTime addMinute(){
		systemDate = systemDate.plusMinutes(1);
		return systemDate;
	}
	
	public DateTime addSecond(){
		systemDate = systemDate.plusSeconds(1);
		return systemDate;
	}
	
	public DateTime addmillisecond(){
		systemDate = systemDate.plusMillis(1);
		return systemDate;
	}	
	
	public DateTime addYears(int years){
		systemDate = systemDate.plusYears(years);
		return systemDate;
	}
	
	public DateTime addMonths(int months){
		systemDate = systemDate.plusMonths(months);
		return systemDate;
	}
	
	public DateTime addDays(int days){
		systemDate = systemDate.plusDays(days);
		return systemDate;
	}
	

	
	public DateTime addHours(int hours){
		systemDate = systemDate.plusHours(hours);
		return systemDate;
	}
	
	public DateTime addMinutes(int minutes){
		systemDate = systemDate.plusMinutes(minutes);
		return systemDate;
	}
	
	public DateTime addSeconds(int seconds){
		systemDate = systemDate.plusSeconds(seconds);
		return systemDate;
	}
	
	public DateTime addmilliseconds(int milliseconds){
		systemDate = systemDate.plusMillis(milliseconds);
		return systemDate;
	}
}
