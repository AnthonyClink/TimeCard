package com.clinkworks.timecard.config;

import com.clinkworks.timecard.component.SystemTimeComponent;
import com.clinkworks.timecard.component.TestSystemTimeComponent;

public class DevelopmentConfig extends ConfigBase{
	@Override
	public void configure(){
		bind(SystemTimeComponent.class).to(TestSystemTimeComponent.class);
		super.configure();
	}
}
