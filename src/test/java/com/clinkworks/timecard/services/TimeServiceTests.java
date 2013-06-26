package com.clinkworks.timecard.services;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;

import com.clinkworks.timecard.service.TimeService;
import com.clinkworks.timecard.util.TestCaseBase;

public class TimeServiceTests extends TestCaseBase{
	@Test
	public void testTimeServiceTimeStamp() throws InterruptedException{

		TimeService timeService = getTimeService();
		
		DateTime realTimeTimeStamp = new DateTime();
		
		Thread.sleep(1);
		
		DateTime serviceProvidedTimeStamp = timeService.getSystemTime();
		
		Assert.assertTrue(realTimeTimeStamp.isBefore(serviceProvidedTimeStamp));		
	}
}
