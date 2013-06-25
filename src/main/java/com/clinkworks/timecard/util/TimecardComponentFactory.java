package com.clinkworks.timecard.util;

import java.util.Date;

import com.clinkworks.timecard.datatypes.Entry;

public interface TimecardComponentFactory {

	public Entry createNewEntry(Date timeStamp);

}
