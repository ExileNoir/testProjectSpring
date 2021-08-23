package com.infernalwhaler.testproject.utilities;

import org.springframework.util.ObjectUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Custom Validator for French resident
 *
 * @author sDeseure
 * @project TestProject
 * @date 19/08/2021
 * @see CountryFrance
 */

public class CountryFranceValidator implements ConstraintValidator<CountryFrance, String> {

    private static final String COUNTRY = "France";

    @Override
    public boolean isValid(String country, ConstraintValidatorContext constraintValidatorContext) {
        return !ObjectUtils.isEmpty(country) && country.equalsIgnoreCase(COUNTRY);
    }
}
