package com.agilic.pages;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface PlaywrightFindBy {
    String css() default "";

    String xpath() default "";

    String text() default "";

    String id() default "";

    String className() default "";

    String attribute() default "";

    String combined() default "";

    String role() default "";

    String name() default "";

    String altText() default "";

    String title() default "";

    String testId() default "";

    String placeholder() default "";

    String byText() default "";

    String shadowParentTag() default "";

    String shadowElementText() default "";

    String shadowByText() default "";

    boolean exactMatch() default false;
}
