package com.druiz.bosonit.db2.utils.exceptions;

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

    @ExceptionHandler(UnprocesableException.class)
    public ResponseEntity<CustomError> unProcesableException(UnprocesableException unproException) {
        CustomError cError = new CustomError(new Date(), 422, unproException.getMessage());
        return new ResponseEntity<>(cError, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}