package com.inditex.pricing.infrastructure.db.service;

import com.inditex.pricing.application.request.PriceRequest;
import com.inditex.pricing.application.response.PriceResponse;
import com.inditex.pricing.infrastructure.repo.PriceRepository;
import com.inditex.pricing.domain.ports.out.GetPricePort;
import com.inditex.pricing.utils.exception.PriceNotFoundException;

public class GetPriceAdapter implements GetPricePort {

    private final PriceRepository priceRepository;

    public GetPriceAdapter(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    @Override
    public PriceResponse getApplicablePrice(PriceRequest request) throws PriceNotFoundException {
        return priceRepository
                .findApplicablePrice(request.getProductId(), request.getBrandId(), request.getApplicationDate())
                .map(price -> new PriceResponse(
                        price.getProductId(),
                        price.getBrandId(),
                        price.getPriceList(),
                        price.getStartDate(),
                        price.getEndDate(),
                        price.getPrice()
                ))
                .orElseThrow(() -> new PriceNotFoundException("Price not found for the given parameters"));
    }

}
