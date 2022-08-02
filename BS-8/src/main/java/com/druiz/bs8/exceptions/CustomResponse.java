package com.druiz.bs8.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestControllerAdvice
public class CustomResponse extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<CustomError> notFoundException(NotFoundException ntException) {
        CustomError cError = new CustomError(new Date(), 404, ntException.getMessage());
        return new ResponseEntity<>(cError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UnprocessableException.class)
    public ResponseEntity<CustomError> unProcesableException(UnprocessableException unproException) {
        CustomError cError = new CustomError(new Date(), 422, unproException.getMessage());
        return new ResponseEntity<>(cError, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}