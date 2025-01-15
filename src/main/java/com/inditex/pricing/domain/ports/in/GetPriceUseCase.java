package com.inditex.pricing.domain.ports.in;

import com.inditex.pricing.application.request.PriceRequest;
import com.inditex.pricing.application.response.PriceResponse;
import com.inditex.pricing.utils.exception.PriceNotFoundException;

public interface GetPriceUseCase {
    PriceResponse getApplicablePrice(PriceRequest request) throws PriceNotFoundException;
}
