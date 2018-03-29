package org.quark.microapidemo.annotation;

import org.quark.microapidemo.config.GlobalConfig;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Approve {
    String role() default GlobalConfig.WebConfig.DEFAULT_ROLE;
}
