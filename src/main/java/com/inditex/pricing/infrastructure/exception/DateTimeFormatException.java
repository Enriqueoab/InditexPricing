package com.inditex.pricing.infrastructure.exception;

import org.springframework.http.HttpStatus;

public class DateTimeFormatException extends InditexPricingException {

    public DateTimeFormatException(String message) {
        super(message, HttpStatus.BAD_REQUEST);

    }

}
