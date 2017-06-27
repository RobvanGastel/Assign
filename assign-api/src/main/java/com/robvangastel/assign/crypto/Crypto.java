package com.robvangastel.assign.crypto;

import javax.enterprise.util.Nonbinding;
import javax.inject.Qualifier;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 *
 * @author Rob van Gastel
 */
 
@Qualifier
@Retention(RUNTIME)
@Target({METHOD, FIELD, PARAMETER, TYPE})
public @interface Crypto {

    enum Type {
        PLAIN, BCRYPT
    }

    @Nonbinding Type value() default Type.PLAIN;
}
