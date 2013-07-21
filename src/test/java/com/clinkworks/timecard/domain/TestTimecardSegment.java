package com.clinkworks.timecard.domain;

import static org.junit.Assert.*;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.junit.Test;

import com.clinkworks.timecard.domain.TimecardEntry;
import com.clinkworks.timecard.domain.TimecardSegment;
import com.clinkworks.timecard.util.SystemTimeUtil;

public class TestTimecardSegment {
	@Test
	public void testSegmentComponentDurationFeature(){
		TimecardSegment segment1 = new TimecardSegment();
		
		SystemTimeUtil testingClock = getSystemTimeUtilSetToNow();
		
		TimecardEntry now = testingClock.getSystemTimeAsTimecardEntry();
		testingClock.addHour();
		TimecardEntry anHourFromNow = testingClock.getSystemTimeAsTimecardEntry();
		
		segment1.setStartTime(now);
		segment1.setEndTime(anHourFromNow);
		
		Duration oneHour = Duration.standardHours(1);
		
		Duration segmentDuration = segment1.getDuration();
		
		assertTrue(oneHour.isEqual(segmentDuration));
	}
	
	@Test
	public void testSegmentComponentCompares(){
		SystemTimeUtil testingClock = getSystemTimeUtilSetToNow();
		
		TimecardEntry now = testingClock.getSystemTimeAsTimecardEntry();
		TimecardEntry oneHourLater = testingClock.addHour().getSystemTimeAsTimecardEntry();
		
		TimecardSegment segment1 = getNewSegment(now, oneHourLater);
		
		TimecardSegment segment2 = getNewSegment(oneHourLater, testingClock.addHour().getSystemTimeAsTimecardEntry());
		
		TimecardSegment segment3 = getNewSegment(
			
				testingClock.getSystemTimeAsTimecardEntry(), 
				
				testingClock.addHour().
					subtractmillisecond().
				getSystemTimeAsTimecardEntry()
			);
		
		TimecardSegment segment4 = getNewSegment(
				segment3.getStartTime(), 
				testingClock.addmilliseconds(2).
				getSystemTimeAsTimecardEntry()
			);
		
		boolean segment1IsAsLongAsSegmentTwo = segment1.isEqual(segment2);
		assertTrue(segment1IsAsLongAsSegmentTwo);
		
		boolean segment2IsLongerThanSegment3 = segment2.isLongerThan(segment3);
		assertTrue(segment2IsLongerThanSegment3);
		
		boolean segment3IsShorterThanSegment4 = segment3.isShorterThan(segment4);
		assertTrue(segment3IsShorterThanSegment4);
	}
	
	private TimecardSegment getNewSegment(TimecardEntry start, TimecardEntry end){
		TimecardSegment retval = new TimecardSegment();
		retval.setStartTime(start);
		retval.setEndTime(end);
		return retval;
	}
	
	private SystemTimeUtil getSystemTimeUtilSetToNow(){
		return new SystemTimeUtil().useTestTime().setTestTimeToNow();
	}
}
