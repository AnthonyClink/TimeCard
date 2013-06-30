package com.clinkworks.timecard.config;

import com.clinkworks.timecard.service.SystemTimeService;
import com.clinkworks.timecard.service.TestingClockService;

public class TestCaseConfigBase extends ConfigBase{
	@Override
	protected void configure() {
		bind(SystemTimeService.class).to(TestingClockService.class);
		super.configure();
	}
}
