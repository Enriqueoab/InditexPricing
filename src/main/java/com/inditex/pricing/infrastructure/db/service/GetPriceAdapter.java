package com.inditex.pricing.infrastructure.db.service;

import com.inditex.pricing.web.request.PriceRequest;
import com.inditex.pricing.domain.model.Price;
import com.inditex.pricing.infrastructure.db.repo.PriceRepository;
import com.inditex.pricing.application.ports.out.GetPricePort;
import com.inditex.pricing.domain.exception.PriceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class GetPriceAdapter implements GetPricePort {

    private final PriceRepository priceRepository;

    public GetPriceAdapter(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    @Override
    public Price getApplicablePrice(PriceRequest request) throws PriceNotFoundException {
         return priceRepository
                 .findFirstByProductIdAndBrandIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc(
                         request.getProductId(), request.getBrandId(), request.getApplicationDate(), request.getApplicationDate());
   }



}
