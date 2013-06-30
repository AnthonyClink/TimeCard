package com.clinkworks.timecard.util;

import org.joda.time.DateTime;

import com.clinkworks.timecard.datatypes.Entry;

public interface TimecardComponentFactory {

	public Entry createNewEntry(DateTime dateTime);

}
