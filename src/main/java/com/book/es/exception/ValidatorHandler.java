package com.book.es.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ValidatorHandler {
    @ExceptionHandler(ValidatorException.class)
    public Object handle(ValidatorException e) {
        return e.getWebResponse();
    }
}
