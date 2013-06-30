package com.clinkworks.timecard.web.metrics;

import com.google.inject.AbstractModule;

public final class JerseyMetricsModule extends AbstractModule{
    @Override
    protected void configure() {
        bind(TimingResourceMethodDispatchAdapter.class);
        bind(HttpStatusCodeMetricResourceFilterFactory.class);
    }
}
