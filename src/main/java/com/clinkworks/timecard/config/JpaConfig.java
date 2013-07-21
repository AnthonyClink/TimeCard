package com.clinkworks.timecard.config;

import com.clinkworks.timecard.datatypes.StringConstants;
import com.clinkworks.timecard.util.JpaInit;
import com.google.inject.AbstractModule;
import com.google.inject.persist.jpa.JpaPersistModule;

public class JpaConfig extends AbstractModule{

	@Override
	protected void configure() {
		install(new JpaPersistModule(StringConstants.TIMECARD_DEFAULT_PERSIST_UNIT.getConstantValue()));
	}
}
