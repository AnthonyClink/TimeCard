package com.clinkworks.timecard.domain;

import org.joda.time.Duration;

public class TimecardSegment implements Comparable<TimecardSegment>{
	
	private TimecardEntry startTime;
	private TimecardEntry endTime;
	
	public TimecardEntry getStartTime() {
		return startTime;
	}
	
	public void setStartTime(TimecardEntry startTime) {
		this.startTime = startTime;
	}
	
	public TimecardEntry getEndTime() {
		return endTime;
	}
	
	public void setEndTime(TimecardEntry endTime) {
		this.endTime = endTime;
	}

	public Duration getDuration() {
		return new Duration(startTime, endTime);
	}

	public boolean isEqual(TimecardSegment segment) {
		return getDuration().isEqual(segment.getDuration());
	}
	
	public boolean isLongerThan(TimecardSegment segment){
		return getDuration().isLongerThan(segment.getDuration());
	}
	
	public boolean isShorterThan(TimecardSegment segment){
		return getDuration().isShorterThan(segment.getDuration());
	}

	@Override
	public int compareTo(TimecardSegment segment) {
		if(this.isEqual(segment)){
			return 0;
		}
		
		else if(this.isLongerThan(segment)){
			return 1;
		}
		
		return -1;
	}
	
}
