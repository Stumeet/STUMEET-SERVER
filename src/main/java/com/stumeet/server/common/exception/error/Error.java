package com.stumeet.server.common.exception.error;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Error {

    @JsonProperty(index = 2)
    private final String message;

    public Error(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
