package com.infernalwhaler.testproject.utilities;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Custom Annotation for adult age
 *
 * @author sDeseure
 * @project TestProject
 * @date 19/08/2021
 * @see AdultValidator
 */

@Documented
@Retention(RUNTIME)
@Target({FIELD, PARAMETER, METHOD})
@Constraint(validatedBy = AdultValidator.class)
public @interface Adult {
    String message() default "{You must be an adult to register}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}