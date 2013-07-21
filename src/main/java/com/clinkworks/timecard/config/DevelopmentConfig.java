package com.clinkworks.timecard.config;

import com.clinkworks.timecard.api.TimeSegmentService;
import com.clinkworks.timecard.component.SystemTimeComponent;
import com.clinkworks.timecard.util.SystemTimeUtil;

public class DevelopmentConfig extends ConfigBase{
	@Override
	public void configure(){
		bind(TimeSegmentService.class);
		bind(SystemTimeComponent.class).to(SystemTimeUtil.class);
		super.configure();
	}
}
