package com.stumeet.server.common.annotation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NullOrPositiveValidator implements ConstraintValidator<NullOrPositive, Long> {
    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        return value > 0;
    }
}
