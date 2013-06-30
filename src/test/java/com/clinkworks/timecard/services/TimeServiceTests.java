package com.clinkworks.timecard.services;

import org.joda.time.DateTime;

import org.jukito.JukitoRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.clinkworks.timecard.service.SystemTimeService;

@RunWith(JukitoRunner.class)
public class TimeServiceTests{

	
	@Test
	public void testTimeServiceTimeStamp(SystemTimeService timeService) throws InterruptedException{
		
		DateTime realTimeTimeStamp = new DateTime();
		
		Thread.sleep(1);
		
		DateTime serviceProvidedTimeStamp = timeService.getSystemTime();
		
		Assert.assertTrue(realTimeTimeStamp.isBefore(serviceProvidedTimeStamp));		
	}
}
