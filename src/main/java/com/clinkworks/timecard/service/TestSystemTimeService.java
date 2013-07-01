package com.clinkworks.timecard.service;

import org.joda.time.DateTime;

import com.google.inject.Singleton;

/**
 * This class keeps track of the system time.
 * This service is provided as a means to unit test system time sensitive processes.
 * @author AnthonyJCLink
 *
 */
@Singleton
public final class TestSystemTimeService extends SystemTimeService{
	
	public static final DateTime JANUARY_FIRST_TWO_THOUSAND = new DateTime(2000, 1, 1, 0, 0, 0, 0);
	
	private DateTime systemDate;
	private boolean useRealTime;
	
	public TestSystemTimeService(){
		systemDate = JANUARY_FIRST_TWO_THOUSAND;
		useRealTime = true;
	}
	
	public synchronized final TestSystemTimeService resetClockToJanuaryFirstTwoThousand(){
		systemDate = JANUARY_FIRST_TWO_THOUSAND;
		return this;
	}
	
	public synchronized TestSystemTimeService useRealTime(){
		useRealTime = true;
		return this;
	}
	
	
	public synchronized TestSystemTimeService setTestTimeToNow(){
		systemDate = new DateTime();
		return this;
	}
	
	public synchronized TestSystemTimeService useTestTime(){
		useRealTime = false;
		return this;
	}
	
	public synchronized TestSystemTimeService setTestSystemTime(DateTime date){
		systemDate = date;
		return this;
	}	
	
	@Override
	public synchronized DateTime getSystemTime(){
		
		if(useRealTime){
			return new DateTime();
		}
		
		if(systemDate == null){
			resetClockToJanuaryFirstTwoThousand();
		}
		
		return systemDate;

	}
	
	public synchronized DateTime addYear(){
		systemDate = systemDate.plusYears(1);
		return systemDate;
	}

	public synchronized DateTime addMonth(){
		systemDate = systemDate.plusMonths(1);
		return systemDate;
	}
	
	public synchronized DateTime addDay(){
		systemDate = systemDate.plusDays(1);
		return systemDate;
	}
	
	public synchronized DateTime addHour(){
		systemDate = systemDate.plusHours(1);
		return systemDate;
	}
	
	public synchronized DateTime addMinute(){
		systemDate = systemDate.plusMinutes(1);
		return systemDate;
	}
	
	public synchronized DateTime addSecond(){
		systemDate = systemDate.plusSeconds(1);
		return systemDate;
	}
	
	public synchronized DateTime addmillisecond(){
		systemDate = systemDate.plusMillis(1);
		return systemDate;
	}	
	
	public synchronized DateTime addYears(int years){
		systemDate = systemDate.plusYears(years);
		return systemDate;
	}
	
	public synchronized DateTime addMonths(int months){
		systemDate = systemDate.plusMonths(months);
		return systemDate;
	}
	
	public synchronized DateTime addDays(int days){
		systemDate = systemDate.plusDays(days);
		return systemDate;
	}
	

	
	public synchronized DateTime addHours(int hours){
		systemDate = systemDate.plusHours(hours);
		return systemDate;
	}
	
	public synchronized DateTime addMinutes(int minutes){
		systemDate = systemDate.plusMinutes(minutes);
		return systemDate;
	}
	
	public synchronized DateTime addSeconds(int seconds){
		systemDate = systemDate.plusSeconds(seconds);
		return systemDate;
	}
	
	public synchronized DateTime addmilliseconds(int milliseconds){
		systemDate = systemDate.plusMillis(milliseconds);
		return systemDate;
	}
}
