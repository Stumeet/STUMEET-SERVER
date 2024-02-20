package com.stumeet.server.common.exception.error;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.validation.BindingResult;
public class ErrorField extends Error {

    final String code;

    public ErrorField(String message, String code) {
        super(message);
        this.code = code;
    }

    public static List<Error> toErrors(BindingResult bindingResult) {
        return bindingResult.getAllErrors().stream()
                .map(error ->
                        new ErrorField(
                                error.getDefaultMessage(),
                                error.getCode()))
                .collect(Collectors.toList());
    }
}
