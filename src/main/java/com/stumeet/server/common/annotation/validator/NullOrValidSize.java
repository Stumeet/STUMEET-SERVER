package com.stumeet.server.common.annotation.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;


@Documented
@Constraint(validatedBy = NullOrValidSizeValidator.class)
@Target({ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface NullOrValidSize {
    String message() default "값이 전달되지 않거나 유효한 크기여야 합니다.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    int min() default 0;
    int max() default 255;
}
