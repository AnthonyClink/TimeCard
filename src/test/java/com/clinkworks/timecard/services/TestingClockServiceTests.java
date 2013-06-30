package com.clinkworks.timecard.services;

import java.util.ArrayList;
import java.util.List;

import org.jukito.JukitoRunner;
import org.jukito.UseModules;
import org.junit.Assert;

import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.clinkworks.timecard.config.TestCaseConfigBase;
import com.clinkworks.timecard.service.TestSystemTimeService;

public class TestingClockServiceTests{
	
	@Test
	public void testRealTimeVsNotRealTime() throws InterruptedException{
		TestSystemTimeService clockService = new TestSystemTimeService();
		
		//set the internal test date
		DateTime januaryfirstTwoThousand = new DateTime(2000, 1, 1, 0, 0, 0);
		
		clockService.setTestSystemTime(januaryfirstTwoThousand);
		
		//using real time should return the system time.
		clockService.useRealTime();
		
		
		DateTime serviceProvidedTimeStamp = clockService.getSystemTime();
		
		Thread.sleep(1);
		
		DateTime realTimeTimeStamp = new DateTime();
		
		Assert.assertTrue(serviceProvidedTimeStamp.isBefore(realTimeTimeStamp));
		Assert.assertTrue(serviceProvidedTimeStamp.isAfter(realTimeTimeStamp.plusSeconds(-2)));
		
		clockService.useTestTime();
		
		Assert.assertEquals(januaryfirstTwoThousand, clockService.getSystemTime());
	}
	
	@Test
	public void testTestClockTimeManipulationFeatures(){
		TestSystemTimeService clockService = new TestSystemTimeService();

		DateTime januaryfirstTwoThousand = new DateTime(2000, 1, 1, 0, 0, 0);
		
		clockService.setTestSystemTime(januaryfirstTwoThousand);
		
		clockService.useTestTime();
			//Year, Month, Day, Hour, Minute, Second, Millisecond
		int[][] addUnitsWithoutParams = {
			{2000, 1, 1, 0, 0, 0, 0},
			{2000, 1, 1, 0, 0, 0, 1},
			{2000, 1, 1, 0, 0, 1, 1},
			{2000, 1, 1, 0, 1, 1, 1},
			{2000, 1, 1, 1, 1, 1, 1},
			{2000, 1, 2, 1, 1, 1, 1},
			{2000, 2, 2, 1, 1, 1, 1},
			{2001, 2, 2, 1, 1, 1, 1}
		};
		
		int[][] addUnitWithParamSetToTwo = {
			{2000, 1, 1, 0, 0, 0, 0},
			{2000, 1, 1, 0, 0, 0, 2},
			{2000, 1, 1, 0, 0, 2, 2},
			{2000, 1, 1, 0, 2, 2, 2},
			{2000, 1, 1, 2, 2, 2, 2},
			{2000, 1, 3, 2, 2, 2, 2},
			{2000, 3, 3, 2, 2, 2, 2},
			{2002, 3, 3, 2, 2, 2, 2}
		};
		
		int countParam = 2;
		
		List<DateTime> unitValidationWithoutParam = createListFromData(addUnitsWithoutParams);
		
		validateDate(unitValidationWithoutParam.get(0), clockService.getSystemTime());
		validateDate(unitValidationWithoutParam.get(1), clockService.addmillisecond());
		validateDate(unitValidationWithoutParam.get(2), clockService.addSecond());
		validateDate(unitValidationWithoutParam.get(3), clockService.addMinute());
		validateDate(unitValidationWithoutParam.get(4), clockService.addHour());
		validateDate(unitValidationWithoutParam.get(5), clockService.addDay());
		validateDate(unitValidationWithoutParam.get(6), clockService.addMonth());
		validateDate(unitValidationWithoutParam.get(7), clockService.addYear());

		List<DateTime> unitValidationWithCountParamOfTwo = createListFromData(addUnitWithParamSetToTwo);
		
		clockService.setTestSystemTime(januaryfirstTwoThousand);
		
		validateDate(unitValidationWithCountParamOfTwo.get(0), clockService.getSystemTime());
		validateDate(unitValidationWithCountParamOfTwo.get(1), clockService.addmilliseconds(countParam));
		validateDate(unitValidationWithCountParamOfTwo.get(2), clockService.addSeconds(countParam));
		validateDate(unitValidationWithCountParamOfTwo.get(3), clockService.addMinutes(countParam));
		validateDate(unitValidationWithCountParamOfTwo.get(4), clockService.addHours(countParam));
		validateDate(unitValidationWithCountParamOfTwo.get(5), clockService.addDays(countParam));
		validateDate(unitValidationWithCountParamOfTwo.get(6), clockService.addMonths(countParam));
		validateDate(unitValidationWithCountParamOfTwo.get(7), clockService.addYears(countParam));		
	}
	
	private void validateDate(DateTime expected, DateTime actual){
		Assert.assertTrue(expected.isEqual(actual));
	}
	
	private List<DateTime> createListFromData(int[][] validationData){
		List<DateTime> retval = new ArrayList<DateTime>();
		for(int[] validationRow : validationData){
			retval.add(createValidationDate(validationRow));
		}
		return retval;
	}
	
	private DateTime createValidationDate(int[] validationRow){
		int[] data = validationRow;
		return new DateTime(data[0], data[1], data[2], data[3], data[4], data[5], data[6]);
	}
}
