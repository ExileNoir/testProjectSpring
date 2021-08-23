package com.infernalwhaler.testproject.utilities;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Custom Annotation for French resident
 *
 * @author sDeseure
 * @project TestProject
 * @date 19/08/2021
 * @see CountryFranceValidator
 */

@Documented
@Retention(RUNTIME)
@Target({FIELD, PARAMETER, METHOD})
@Constraint(validatedBy = CountryFranceValidator.class)
public @interface CountryFrance {
    String message() default "{You must be a French resident to register}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
