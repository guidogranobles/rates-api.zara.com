package com.zara.rates.api.common.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.OffsetDateTime;

@ControllerAdvice
public class ApiExceptionHandler {
    private static final Logger loggerException = LogManager.getLogger(ApiExceptionHandler.class);

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(OffsetDateTime.now(), ex.getMessage(),
                request.getDescription(false));
        loggerException.error(ex);
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({GeneralException.class})
    public ResponseEntity generalExceptionHandler(GeneralException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(OffsetDateTime.now(), ex.getMessage(),
                request.getDescription(false));
        loggerException.error(ex);
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ParameterEmptyException.class)
    public ResponseEntity parameterEmptyException(ParameterEmptyException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(OffsetDateTime.now(), ex.getMessage(),
                request.getDescription(false));
        loggerException.error(ex);
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
}
