package co.ooci.event.manager;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The annotation for mark the event handling methods.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface OociEventHandler {

    /**
     * Turn this to true to mark this handler as the internal event listener. <p>
     * Internal event listeners will be executed before the normal listener executes. <p>
     * Plugin should <b>NEVER USE</b> this attribute, this is designed for API implementations.
     */
    boolean internal() default false;
}