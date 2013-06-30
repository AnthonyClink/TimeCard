package com.clinkworks.timecard.util;

import org.joda.time.DateTime;
import org.junit.Before;

import com.clinkworks.timecard.config.TestCaseConfigBase;
import com.clinkworks.timecard.service.EntryService;
import com.clinkworks.timecard.service.SystemTimeService;
import com.clinkworks.timecard.services.TestingClockService;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.persist.jpa.JpaPersistModule;

/**
 * Note: kidna smells like you are backing yourself into a bad design desision.
 * this is assuming that every implementation is going to use the mock time service.
 * Note, im relying on the fact that the MockService is a singleton and is injected into
 * the test. This is also assuming no desendant injectors.
 * 
 * @author AnthonyJCLink
 *
 */
public class TestCaseBase{
	private final Injector injector;
	private static final String TIME_CARD_PERSIST_UNIT = "TimeSystem";
	private final TestingClockService timeService;
	
	public TestCaseBase(){
		injector = Guice.createInjector(new TestCaseConfigBase(), 
				new JpaPersistModule(TIME_CARD_PERSIST_UNIT));
		timeService = injector.getInstance(TestingClockService.class);
		JPAInitializer jpaInitalizer = injector.getInstance(JPAInitializer.class);
	}
	
	public TestCaseBase(Module... modules){
		injector = Guice.createInjector(modules);
		timeService = injector.getInstance(TestingClockService.class);
	}
	
	protected final SystemTimeService getTimeService(){
		return timeService;
	}
	
	protected final void setSystemTime(DateTime systemTime){
		timeService.setTestSystemTime(systemTime);
	}
	
	protected final TestingClockService getTestingClockService(){
		return timeService;
	}
	
	@Before
	public void doSetup(){
		TestingClockService service = getTestingClockService();
		service.resetClockToJanuaryFirstTwoThousand();
		service.useRealTime();
	}
	
	protected final EntryService getEntryService(){
		return injector.getInstance(EntryService.class);
	}
}
