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
import com.clinkworks.timecard.util.SystemTimeUtil;

@RunWith(JukitoRunner.class)
@UseModules({ TestCaseConfigBase.class })
public class TestTestingClockComponent{
	
	@Test
	public void testRealTimeVsNotRealTime(SystemTimeUtil timeService) throws InterruptedException{
		
		//set the internal test date
		DateTime januaryfirstTwoThousand = new DateTime(2000, 1, 1, 0, 0, 0);
		
		timeService.setTestSystemTime(januaryfirstTwoThousand);
		
		//using real time should return the system time.
		timeService.useRealTime();
		
		DateTime serviceProvidedTimeStamp = timeService.getSystemTime();
		
		Thread.sleep(1);
		
		DateTime realTimeTimeStamp = new DateTime();
		
		Assert.assertTrue(serviceProvidedTimeStamp.isBefore(realTimeTimeStamp));
		Assert.assertTrue(serviceProvidedTimeStamp.isAfter(realTimeTimeStamp.minusSeconds(2)));
		
		timeService.useTestTime();
		
		Assert.assertEquals(januaryfirstTwoThousand, timeService.getSystemTime());
	}
	
	@Test
	public void testTestClockTimeManipulationFeatures(SystemTimeUtil timeService){

		DateTime januaryfirstTwoThousand = new DateTime(2000, 1, 1, 0, 0, 0);
		
		timeService.setTestSystemTime(januaryfirstTwoThousand);
		
		timeService.useTestTime();
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
		
		validateDate(unitValidationWithoutParam.get(0), timeService.getSystemTime());
		validateDate(unitValidationWithoutParam.get(1), timeService.addmillisecond().getSystemTime());
		validateDate(unitValidationWithoutParam.get(2), timeService.addSecond().getSystemTime());
		validateDate(unitValidationWithoutParam.get(3), timeService.addMinute().getSystemTime());
		validateDate(unitValidationWithoutParam.get(4), timeService.addHour().getSystemTime());
		validateDate(unitValidationWithoutParam.get(5), timeService.addDay().getSystemTime());
		validateDate(unitValidationWithoutParam.get(6), timeService.addMonth().getSystemTime());
		validateDate(unitValidationWithoutParam.get(7), timeService.addYear().getSystemTime());

		List<DateTime> unitValidationWithCountParamOfTwo = createListFromData(addUnitWithParamSetToTwo);
		
		timeService.setTestSystemTime(januaryfirstTwoThousand);
		
		validateDate(unitValidationWithCountParamOfTwo.get(0), timeService.getSystemTime());
		validateDate(unitValidationWithCountParamOfTwo.get(1), timeService.addmilliseconds(countParam).getSystemTime());
		validateDate(unitValidationWithCountParamOfTwo.get(2), timeService.addSeconds(countParam).getSystemTime());
		validateDate(unitValidationWithCountParamOfTwo.get(3), timeService.addMinutes(countParam).getSystemTime());
		validateDate(unitValidationWithCountParamOfTwo.get(4), timeService.addHours(countParam).getSystemTime());
		validateDate(unitValidationWithCountParamOfTwo.get(5), timeService.addDays(countParam).getSystemTime());
		validateDate(unitValidationWithCountParamOfTwo.get(6), timeService.addMonths(countParam).getSystemTime());
		validateDate(unitValidationWithCountParamOfTwo.get(7), timeService.addYears(countParam).getSystemTime());		
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
