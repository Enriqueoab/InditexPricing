package com.inditex.pricing.domain.ports.out;

import com.inditex.pricing.application.request.PriceRequest;
import com.inditex.pricing.application.response.PriceResponse;
import com.inditex.pricing.utils.exception.PriceNotFoundException;

public interface GetPricePort {
    PriceResponse getApplicablePrice(PriceRequest request) throws PriceNotFoundException;
}
