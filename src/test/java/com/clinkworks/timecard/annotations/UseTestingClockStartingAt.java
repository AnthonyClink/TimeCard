package com.clinkworks.timecard.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * going to refactor testing clock to use annotations
 * @author AnthonyJCLink
 *
 */
@Retention(RetentionPolicy.RUNTIME) 
@Target(ElementType.METHOD)
public @interface UseTestingClockStartingAt {

}
