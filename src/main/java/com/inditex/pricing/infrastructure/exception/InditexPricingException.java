package com.inditex.pricing.infrastructure.exception;

import org.springframework.http.HttpStatus;

import java.io.Serial;

public abstract class InditexPricingException extends Exception {

    private final HttpStatus status;

    @Serial
    private static final long serialVersionUID = 4969688172982128054L;

    protected InditexPricingException(String message, HttpStatus status){
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }

}
