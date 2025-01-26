package com.inditex.pricing.infrastructure.exception;

import org.springframework.http.HttpStatus;

import java.io.Serial;

public class PriceNotFoundException extends InditexPricingException {

    @Serial
    private static final long serialVersionUID = 1453316100622498951L;

    public PriceNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
