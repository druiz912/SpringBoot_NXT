package com.druiz.bosonit.crudjdbc.person.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.function.Supplier;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException implements Supplier<X> {
    public NotFoundException(String message) {
        super(message);
    }

}
