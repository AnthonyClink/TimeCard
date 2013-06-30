package com.clinkworks.timecard.web;

import java.util.EnumSet;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import com.clinkworks.timecard.config.ConfigBase;
import com.clinkworks.timecard.web.metrics.JerseyMetricsModule;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.servlet.GuiceFilter;

import com.yammer.metrics.Metrics;
import com.yammer.metrics.core.MetricsRegistry;

public class JettyServ {
	
	public static void main(String[] args) throws ServerFailedToStartException{
        Injector injector = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {

                install(getInjectorForModule(ConfigBase.class));
                install(getInjectorForModule(JerseyMetricsModule.class));

                bind(GuiceFilter.class);
                bind(MetricsRegistry.class).toInstance(Metrics.defaultRegistry());
            }
        });
        
        Server server = new Server(8080);
        
        ServletContextHandler handler = new ServletContextHandler();
        handler.setContextPath("/");

        handler.addServlet(new ServletHolder(new InvalidRequestServlet()), "/*");

        FilterHolder guiceFilter = new FilterHolder(injector.getInstance(GuiceFilter.class));
        handler.addFilter(guiceFilter, "/*", EnumSet.allOf(javax.servlet.DispatcherType.class));

        server.setHandler(handler);
        
        try{
        	server.start();
        }catch(Exception e){
        	throw new ServerFailedToStartException(e);
        }
        
	}
	
	public static Module getInjectorForModule(Class<?> worldcraftModule){
		Module myModule = null;
		
		try {
			myModule = (Module) worldcraftModule.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Injector injector = Guice.createInjector(myModule);
		return myModule;
	}
}
