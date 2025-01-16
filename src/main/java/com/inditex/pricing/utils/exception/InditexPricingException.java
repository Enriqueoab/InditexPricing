package com.inditex.pricing.utils.exception;

import java.io.Serial;

public abstract class InditexPricingException extends Exception {

    @Serial
    private static final long serialVersionUID = 4969688172982128054L;

    protected InditexPricingException(String message){
        super(message);
    }

}
