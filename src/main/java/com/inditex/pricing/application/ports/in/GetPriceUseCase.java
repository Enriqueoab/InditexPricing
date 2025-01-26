package com.inditex.pricing.application.ports.in;

import com.inditex.pricing.infrastructure.exception.DateTimeFormatException;
import com.inditex.pricing.infrastructure.exception.PriceNotFoundException;
import com.inditex.pricing.infrastructure.web.request.PriceRequest;
import com.inditex.pricing.infrastructure.web.response.PriceResponse;

public interface GetPriceUseCase {
    PriceResponse getApplicablePrice(PriceRequest request) throws PriceNotFoundException, DateTimeFormatException;
}
