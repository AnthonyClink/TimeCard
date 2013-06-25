package com.clinkworks.timecard.util;

import java.util.Date;

import com.clinkworks.timecard.config.TestCaseConfigBase;
import com.clinkworks.timecard.service.EntryService;
import com.clinkworks.timecard.service.TimeService;
import com.clinkworks.timecard.services.MockTimeService;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

public class TestCaseBase{
	private final Injector injector;
	
	private MockTimeService timeService;
	
	public TestCaseBase(){
		injector = Guice.createInjector(new TestCaseConfigBase());
	}
	
	public TestCaseBase(Module... modules){
		injector = Guice.createInjector(modules);
	}
	
	protected final TimeService getTimeService(){
		if(timeService == null){
			timeService = injector.getInstance(MockTimeService.class);
		}
		return timeService;
	}
	
	protected final void toggleTimeMockOff(){
		((MockTimeService)getTimeService()).switchMockTimeOff();
	}

	protected final void toggleTimeMockOn(){
		((MockTimeService)getTimeService()).switchMockTimeOn();
	}
	
	protected final void setSystemTime(Date systemTime){
		((MockTimeService)getTimeService()).setSystemTime(systemTime);
	}
	
	protected final EntryService getEntryService(){
		return injector.getInstance(EntryService.class);
	}
}
