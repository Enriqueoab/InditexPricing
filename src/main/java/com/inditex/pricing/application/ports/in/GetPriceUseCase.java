package com.inditex.pricing.application.ports.in;

import com.inditex.pricing.domain.exception.DateTimeFormatException;
import com.inditex.pricing.web.request.PriceRequest;
import com.inditex.pricing.domain.exception.PriceNotFoundException;
import com.inditex.pricing.web.response.PriceResponse;

public interface GetPriceUseCase {
    PriceResponse getApplicablePrice(PriceRequest request) throws PriceNotFoundException, DateTimeFormatException;
}
