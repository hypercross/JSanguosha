package game.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/** 
 * This should only appear in an event that is related to an action.
 * There will be a universal rule created to launch this event upon conducting the action
 * @author hyper
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface EventAction {
	public String value();
}
