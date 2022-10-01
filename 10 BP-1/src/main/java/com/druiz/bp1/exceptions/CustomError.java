package com.druiz.bp1.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@AllArgsConstructor
@Getter
public class CustomError {
    private Date timestamp;
    private int httpCode;
    private String message;
}
