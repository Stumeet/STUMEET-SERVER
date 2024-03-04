package com.stumeet.server.common.annotation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NullOrValidSizeValidator implements ConstraintValidator<NullOrValidSize, String> {
    private int min;
    private int max;

    @Override
    public void initialize(NullOrValidSize constraintAnnotation) {
        min = constraintAnnotation.min();
        max = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        return value.length() >= min && value.length() <= max;
    }
}
