package com.zara.rates.api.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.OffsetDateTime;


@Getter
@AllArgsConstructor
public class ErrorDetails {
    private OffsetDateTime timestamp;
    private String message;
    private String details;
}
