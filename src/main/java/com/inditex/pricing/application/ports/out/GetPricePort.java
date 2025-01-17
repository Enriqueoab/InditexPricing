package com.inditex.pricing.application.ports.out;

import com.inditex.pricing.domain.model.Price;
import com.inditex.pricing.web.request.PriceRequest;
import com.inditex.pricing.domain.exception.PriceNotFoundException;

public interface GetPricePort {
    Price getApplicablePrice(PriceRequest request) throws PriceNotFoundException;
}
