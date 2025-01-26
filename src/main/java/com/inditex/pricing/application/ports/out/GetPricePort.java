package com.inditex.pricing.application.ports.out;

import com.inditex.pricing.infrastructure.exception.PriceNotFoundException;
import com.inditex.pricing.infrastructure.model.Price;
import com.inditex.pricing.infrastructure.web.request.PriceRequest;

public interface GetPricePort {
    Price getApplicablePrice(PriceRequest request) throws PriceNotFoundException;
}
