package com.clinkworks.timecard.config;

import com.clinkworks.timecard.util.TimecardComponentFactory;
import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

public class ConfigBase extends AbstractModule{

	@Override
	protected void configure() {
		
		FactoryModuleBuilder factoryModuleBuilder = new FactoryModuleBuilder();
		
		configureFactoryModuleBuilder(factoryModuleBuilder);
		
		install(factoryModuleBuilder.
	     build(getTimecardComponentFactory()));
	}
	
	/**
	 * Override this method to provide an extension implemention. I created this method instead of using
	 * the default guice binders, but I couldn't figure out how to preserve the super call and still be super
	 * configurable. I am compleltly open to suggestion, this screams fix me.
	 * @return the default TimecardComponentFactory
	 */
	@SuppressWarnings("unchecked")
	protected <TCF extends TimecardComponentFactory> Class<TCF> getTimecardComponentFactory(){
		return (Class<TCF>)TimecardComponentFactory.class;
	}
	
	/**
	 * override this if you want to bind to different implementations useful for test configs.
	 * @param timecardComponentFactoryBuilder
	 */
	protected void configureFactoryModuleBuilder(FactoryModuleBuilder timecardComponentFactoryBuilder){

	}
}
