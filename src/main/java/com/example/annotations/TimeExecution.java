package com.example.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
/**
 * Times executions of a method
 */
public @interface TimeExecution {

    /**
     * The name of the metric
     *
     * @return the name of the metric
     */
    String value();

}
