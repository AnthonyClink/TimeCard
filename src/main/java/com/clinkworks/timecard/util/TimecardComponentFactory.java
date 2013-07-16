package com.clinkworks.timecard.util;

import org.joda.time.DateTime;

import com.clinkworks.timecard.domain.TimecardEntry;

public interface TimecardComponentFactory {

	public TimecardEntry createNewTimecardEntry(DateTime dateTime);

}
