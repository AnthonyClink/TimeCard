package com.clinkworks.timecard.config;

import com.clinkworks.timecard.service.TimeService;
import com.clinkworks.timecard.services.MockTimeService;
import com.google.inject.Guice;

public class TestCaseConfigBase extends ConfigBase{
	@Override
	protected void configure() {
		bind(TimeService.class).to(MockTimeService.class);
		super.configure();
	}
}
