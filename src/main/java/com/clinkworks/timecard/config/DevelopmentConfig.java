package com.clinkworks.timecard.config;

import com.clinkworks.timecard.service.SystemTimeService;
import com.clinkworks.timecard.service.TestSystemTimeService;

public class DevelopmentConfig extends ConfigBase{
	@Override
	public void configure(){
		bind(SystemTimeService.class).to(TestSystemTimeService.class);
		super.configure();
	}
}
