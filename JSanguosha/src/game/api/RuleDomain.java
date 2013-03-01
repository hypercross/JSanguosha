package game.api;


import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * This should only exist on a IRule field in a card prototype object's declaration.
 * @author hyper
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface RuleDomain {

	Class<? extends IRuleDomain> value();
}
