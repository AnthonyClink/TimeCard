package com.clinkworks.timecard.component;


import org.joda.time.DateTime;

import com.google.inject.Singleton;

@Singleton
public class SystemTimeComponent {
	
	public DateTime getSystemTime(){
		return new DateTime();
	}
}
