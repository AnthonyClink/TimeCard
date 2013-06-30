package com.clinkworks.timecard.services;

import org.joda.time.DateTime;
import org.jukito.JukitoRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.clinkworks.timecard.service.SystemTimeService;

@RunWith(JukitoRunner.class)
public class SystemTimeServiceTests {

	@Test
	public void testSystemTimeServiceGivesSystemTime() throws InterruptedException{
		SystemTimeService systemTimeService = new SystemTimeService();
		
		DateTime then = systemTimeService.getSystemTime();
		
		Thread.sleep(1);
		
		DateTime now = new DateTime();
		
		DateTime nowTwoSecondAgo = now.plusSeconds(-2);
		
		//since execution time may vary depending on the system used for testing
		//we go back two seconds and make sure the systemTimeService returns a unit of time between
		//a milisecond ahead and two seconds in teh past.
		Assert.assertTrue(now.isAfter(then));
		Assert.assertTrue(then.isAfter(nowTwoSecondAgo));
	}
}
