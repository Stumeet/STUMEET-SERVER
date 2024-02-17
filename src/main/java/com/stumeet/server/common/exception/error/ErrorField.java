package com.stumeet.server.common.exception.error;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.validation.BindingResult;
public class ErrorField extends Error {

    @JsonProperty(index = 1)
    final String code;

    public ErrorField(String message, String code) {
        super(message);
        this.code = code;
    }

    public static List<Error> of(BindingResult bindingResult) {
        return bindingResult.getAllErrors().stream()
                .map(error ->
                        new ErrorField(
                                error.getDefaultMessage(),
                                error.getCode()))
                .collect(Collectors.toList());
    }
}
