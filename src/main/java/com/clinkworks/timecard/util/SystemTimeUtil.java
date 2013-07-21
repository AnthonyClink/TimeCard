package com.clinkworks.timecard.util;

import org.joda.time.DateTime;

import com.clinkworks.timecard.component.SystemTimeComponent;
import com.clinkworks.timecard.datatypes.Entry;
import com.clinkworks.timecard.domain.TimecardEntry;
import com.google.inject.Singleton;

/**
 * This class keeps track of the system time.
 * This service is provided as a means to unit test system time sensitive processes.
 * @author AnthonyJCLink
 *
 */
@Singleton
public final class SystemTimeUtil extends SystemTimeComponent{
	
	public static final DateTime JANUARY_FIRST_TWO_THOUSAND = new DateTime(2000, 1, 1, 0, 0, 0, 0);
	
	private DateTime systemDate;
	private boolean useRealTime;
	
	public SystemTimeUtil(){
		systemDate = JANUARY_FIRST_TWO_THOUSAND;
		useRealTime = true;
	}
	
	public TimecardEntry getSystemTimeAsTimecardEntry(){
		return new TimecardEntry(new Entry(getSystemTime()));
	}
	
	public synchronized final SystemTimeUtil resetClockToJanuaryFirstTwoThousand(){
		systemDate = JANUARY_FIRST_TWO_THOUSAND;
		return this;
	}
	
	public synchronized SystemTimeUtil useRealTime(){
		useRealTime = true;
		return this;
	}
	
	
	public synchronized SystemTimeUtil setTestTimeToNow(){
		systemDate = new DateTime();
		return this;
	}
	
	public synchronized SystemTimeUtil useTestTime(){
		useRealTime = false;
		return this;
	}
	
	public synchronized SystemTimeUtil setTestSystemTime(DateTime date){
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
	
	public synchronized SystemTimeUtil subtractYear(){
		systemDate = systemDate.minusYears(1);
		return this;
	}

	public synchronized SystemTimeUtil subtractMonth(){
		systemDate = systemDate.minusMonths(1);
		return this;
	}
	
	public synchronized SystemTimeUtil subtractDay(){
		systemDate = systemDate.minusDays(1);
		return this;
	}
	
	public synchronized SystemTimeUtil subtractHour(){
		systemDate = systemDate.minusHours(1);
		return this;
	}
	
	public synchronized SystemTimeUtil subtractMinute(){
		systemDate = systemDate.minusMinutes(1);
		return this;
	}
	
	public synchronized SystemTimeUtil subtractSecond(){
		systemDate = systemDate.minusSeconds(1);
		return this;
	}
	
	public synchronized SystemTimeUtil subtractmillisecond(){
		systemDate = systemDate.minusMillis(1);
		return this;
	}	
	
	public synchronized SystemTimeUtil subtractYears(int years){
		systemDate = systemDate.minusYears(years);
		return this;
	}
	
	public synchronized SystemTimeUtil subtractMonths(int months){
		systemDate = systemDate.minusMonths(months);
		return this;
	}
	
	public synchronized SystemTimeUtil subtractDays(int days){
		systemDate = systemDate.minusDays(days);
		return this;
	}
	
	public synchronized SystemTimeUtil subtractHours(int hours){
		systemDate = systemDate.minusHours(hours);
		return this;
	}
	
	public synchronized SystemTimeUtil subtractMinutes(int minutes){
		systemDate = systemDate.minusMinutes(minutes);
		return this;
	}
	
	public synchronized SystemTimeUtil subtractSeconds(int seconds){
		systemDate = systemDate.minusSeconds(seconds);
		return this;
	}
	
	public synchronized SystemTimeUtil subtractmilliseconds(int milliseconds){
		systemDate = systemDate.minusMillis(milliseconds);
		return this;
	}	
	
	public synchronized SystemTimeUtil addYear(){
		systemDate = systemDate.plusYears(1);
		return this;
	}

	public synchronized SystemTimeUtil addMonth(){
		systemDate = systemDate.plusMonths(1);
		return this;
	}
	
	public synchronized SystemTimeUtil addDay(){
		systemDate = systemDate.plusDays(1);
		return this;
	}
	
	public synchronized SystemTimeUtil addHour(){
		systemDate = systemDate.plusHours(1);
		return this;
	}
	
	public synchronized SystemTimeUtil addMinute(){
		systemDate = systemDate.plusMinutes(1);
		return this;
	}
	
	public synchronized SystemTimeUtil addSecond(){
		systemDate = systemDate.plusSeconds(1);
		return this;
	}
	
	public synchronized SystemTimeUtil addmillisecond(){
		systemDate = systemDate.plusMillis(1);
		return this;
	}	
	
	public synchronized SystemTimeUtil addYears(int years){
		systemDate = systemDate.plusYears(years);
		return this;
	}
	
	public synchronized SystemTimeUtil addMonths(int months){
		systemDate = systemDate.plusMonths(months);
		return this;
	}
	
	public synchronized SystemTimeUtil addDays(int days){
		systemDate = systemDate.plusDays(days);
		return this;
	}
	
	public synchronized SystemTimeUtil addHours(int hours){
		systemDate = systemDate.plusHours(hours);
		return this;
	}
	
	public synchronized SystemTimeUtil addMinutes(int minutes){
		systemDate = systemDate.plusMinutes(minutes);
		return this;
	}
	
	public synchronized SystemTimeUtil addSeconds(int seconds){
		systemDate = systemDate.plusSeconds(seconds);
		return this;
	}
	
	public synchronized SystemTimeUtil addmilliseconds(int milliseconds){
		systemDate = systemDate.plusMillis(milliseconds);
		return this;
	}
	

}
