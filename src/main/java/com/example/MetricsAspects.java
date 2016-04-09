package com.example;

import com.example.annotations.TimeExecution;
import com.example.annotations.CountExceptions;
import com.yammer.metrics.Metrics;
import com.yammer.metrics.core.MetricName;
import com.yammer.metrics.core.Timer;
import com.yammer.metrics.core.TimerContext;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

@Aspect
@Component
public class MetricsAspects {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public MetricsAspects() {
        logger.info("Instantiated {}", this);
    }

    @Around("@annotation(com.example.annotations.TimeExecution)")
    public Object timeExecution(ProceedingJoinPoint pjp) throws Throwable {
        final String methodName = pjp.getSignature().getName();
        final MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        Method method = methodSignature.getMethod();
        if (method.getDeclaringClass().isInterface()) {
            method = pjp.getTarget().getClass().getDeclaredMethod(methodName, method.getParameterTypes());
        }
        TimeExecution annotation = method.getAnnotation(TimeExecution.class);
        String name = annotation.value();

        MetricName metricName = new MetricName("com", "example", name);
        Timer timer = Metrics.newTimer(metricName, TimeUnit.MILLISECONDS, TimeUnit.MINUTES);
        TimerContext timerContext = timer.time();
        Object result = pjp.proceed();
        timerContext.stop();
        return result;
    }

    @Around("@annotation(com.example.annotations.CountExceptions)")
    public Object countExceptions(ProceedingJoinPoint pjp) throws Throwable {
        try {
            return pjp.proceed();
        } catch (Throwable e) {
            final String methodName = pjp.getSignature().getName();
            final MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
            Method method = methodSignature.getMethod();
            if (method.getDeclaringClass().isInterface()) {
                method = pjp.getTarget().getClass().getDeclaredMethod(methodName, method.getParameterTypes());
            }
            CountExceptions annotation = method.getAnnotation(CountExceptions.class);
            String name = annotation.value();

            String exceptionName = e.getClass().getCanonicalName();
            MetricName metricName = new MetricName("com", "example", name + "." + exceptionName);
            Metrics.newCounter(metricName).inc();

            throw e;
        }
    }
}