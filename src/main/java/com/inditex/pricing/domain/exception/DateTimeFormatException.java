package com.inditex.pricing.domain.exception;

import org.springframework.http.HttpStatus;

public class DateTimeFormatException extends InditexPricingException {

    public DateTimeFormatException(String message) {
        super(message, HttpStatus.CONFLICT);

    }

}
