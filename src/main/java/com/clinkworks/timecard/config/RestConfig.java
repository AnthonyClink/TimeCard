package com.clinkworks.timecard.config;

import java.util.HashMap;
import java.util.Map;

import com.clinkworks.timecard.web.CorsFilter;
import com.clinkworks.timecard.web.metrics.HttpStatusCodeMetricResourceFilterFactory;
import com.clinkworks.timecard.web.metrics.JerseyMetricsModule;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.google.inject.Scopes;
import com.google.inject.persist.PersistFilter;
import com.google.inject.servlet.ServletModule;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;

/**
 * This class is used to configure rest classes.
 * @author AnthonyJCLink
 *
 */
public class RestConfig extends ServletModule{

	@Override
    protected void configureServlets() {

        // hook Jersey into Guice Servlet
        bind(GuiceContainer.class);

        // hook Jackson into Jersey as the POJO <-> JSON mapper
        bind(JacksonJsonProvider.class).in(Scopes.SINGLETON);
        
        install(new JerseyMetricsModule());
        
        Map<String, String> guiceContainerConfig = new HashMap<String, String>();
        
        guiceContainerConfig.put(ResourceConfig.PROPERTY_RESOURCE_FILTER_FACTORIES,
            HttpStatusCodeMetricResourceFilterFactory.class.getCanonicalName());
        
        serve("/*").with(GuiceContainer.class, guiceContainerConfig);
        
        filter("/*").through(CorsFilter.class);
        filter("/*").through(PersistFilter.class);
    }
}

