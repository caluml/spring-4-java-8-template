package com.example;

import com.yammer.metrics.Metrics;
import com.yammer.metrics.core.Counter;
import com.yammer.metrics.core.MetricName;
import com.yammer.metrics.reporting.GraphiteReporter;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class MetricUtils {

    private final Counter called;
    private final Counter success;
    private final Counter failure;

    public MetricUtils() {
        GraphiteReporter.enable(1, TimeUnit.MINUTES, "graphite", 2003);

        called = Metrics.newCounter(new MetricName(getClass(), "called"));
        success = Metrics.newCounter(new MetricName(getClass(), "success"));
        failure = Metrics.newCounter(new MetricName(getClass(), "failure"));
    }

    public void markCalled() {
        called.inc();
    }

    public void markSuccess() {
        success.inc();
    }

    public void markFailure() {
        failure.inc();
    }
}
