package game.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * This should only appear in an IEvent object's field declaration.
 * 
 * For parameters passed into a IEvent object, for possible handling.
 * No need to create constructors for them. Parameters are passed in in order by type.
 * 
 * @author hyper
 *
 */


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface EventContext { 
}
