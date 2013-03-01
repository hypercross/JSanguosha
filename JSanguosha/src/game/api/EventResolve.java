package game.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;


/**
 * This should only appear in an IEvent object's field declaration.
 * 
 * For methods that are called when the event resolves.
 * If multiple methods are present,
 * Priority represents the order of execution.
 * Between these resolve calls, rule triggers can happen and intercept/modify event outcome. 
 * 
 * @author hyper
 *
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface EventResolve {
	int value() default 0;
}
