package com.infernalwhaler.testproject.utilities;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

/**
 * Custom Validator for adult age
 *
 * @author sDeseure
 * @project TestProject
 * @date 19/08/2021
 * @see Adult
 */

public class AdultValidator implements ConstraintValidator<Adult, LocalDate> {

    private static final int ADULT_AGE = 18;

    @Override
    public boolean isValid(final LocalDate dateOfBirth, final ConstraintValidatorContext constraintValidatorContext) {
        return dateOfBirth != null && LocalDate.now().minusYears(ADULT_AGE).isAfter(dateOfBirth);
    }
}
