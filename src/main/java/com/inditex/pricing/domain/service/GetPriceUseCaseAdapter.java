package com.inditex.pricing.domain.service;

import com.inditex.pricing.domain.ports.in.GetPriceUseCase;
import com.inditex.pricing.application.request.PriceRequest;
import com.inditex.pricing.application.response.PriceResponse;
import com.inditex.pricing.domain.ports.out.GetPricePort;
import com.inditex.pricing.utils.exception.PriceNotFoundException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GetPriceUseCaseAdapter implements GetPriceUseCase {

    private final GetPricePort getPricePort;

    @Override
    public PriceResponse getApplicablePrice(PriceRequest request) throws PriceNotFoundException {
        return getPricePort.getApplicablePrice(request);
//        return priceRepository
//                .findApplicablePrice(request.getProductId(), request.getBrandId(), request.getApplicationDate())
//                .map(price -> new PriceResponse(
//                        price.getProductId(),
//                        price.getBrandId(),
//                        price.getPriceList(),
//                        price.getStartDate(),
//                        price.getEndDate(),
//                        price.getPrice()
//                ))
//                .orElseThrow(() -> new PriceNotFoundException("Price not found for the given parameters"));
    }
}
