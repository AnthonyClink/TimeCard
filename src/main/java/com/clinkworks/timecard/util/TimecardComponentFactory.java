package com.clinkworks.timecard.util;

import java.util.Date;

import org.joda.time.DateTime;

import com.clinkworks.timecard.domain.Entry;

public interface TimecardComponentFactory {

	public Entry createNewEntry(DateTime dateTime);

}
