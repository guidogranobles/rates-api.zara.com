package com.zara.rates.api.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ParameterEmptyException  extends Exception {
    public ParameterEmptyException(String message) {
        super(message);
    }

    public ParameterEmptyException(String message, Throwable cause) {
        super(message, cause);
    }
}
