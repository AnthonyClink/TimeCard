package com.clinkworks.timecard.config;

import com.clinkworks.timecard.component.SystemTimeComponent;
import com.clinkworks.timecard.util.JpaInit;
import com.clinkworks.timecard.util.SystemTimeUtil;

public class TestCaseConfigBase extends ConfigBase{
	@Override
	protected void configure() {
		bind(SystemTimeComponent.class).to(SystemTimeUtil.class);
		bind(JpaInit.class).asEagerSingleton();
		super.configure();
	}
}
