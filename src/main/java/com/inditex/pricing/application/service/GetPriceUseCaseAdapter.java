package com.inditex.pricing.application.service;

import com.inditex.pricing.application.ports.in.GetPriceUseCase;
import com.inditex.pricing.domain.model.Price;
import com.inditex.pricing.web.request.PriceRequest;
import com.inditex.pricing.application.ports.out.GetPricePort;
import com.inditex.pricing.domain.exception.PriceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class GetPriceUseCaseAdapter implements GetPriceUseCase {

    private final GetPricePort getPricePort;

    public GetPriceUseCaseAdapter(GetPricePort getPricePort) {
        this.getPricePort = getPricePort;
    }

    @Override
    public Price getApplicablePrice(PriceRequest request) throws PriceNotFoundException {
        return getPricePort.getApplicablePrice(request);
    }
}
