package com.inditex.pricing.infrastructure.db.service;

import com.inditex.pricing.domain.constants.ExceptionMessage;
import com.inditex.pricing.web.request.PriceRequest;
import com.inditex.pricing.domain.model.Price;
import com.inditex.pricing.infrastructure.db.repo.PriceRepository;
import com.inditex.pricing.application.ports.out.GetPricePort;
import com.inditex.pricing.domain.exception.PriceNotFoundException;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class GetPriceAdapter implements GetPricePort {

    private final PriceRepository priceRepository;

    public GetPriceAdapter(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    @Override
    public Price getApplicablePrice(PriceRequest request) throws PriceNotFoundException {
        var applicationDate = LocalDateTime.parse(request.applicationDate());

        var price = getPrice(request, applicationDate);

        if (price == null) {
            throw new PriceNotFoundException(ExceptionMessage.PRICE_NOT_FOUND+", productId: " + request.productId() +", brandId: "
                    + request.brandId() +" and applicationDate: "+ applicationDate);
        }

        return price;
   }

    public Price getPrice(PriceRequest request, LocalDateTime applicationDate) {
        return priceRepository
                .findFirstByProductIdAndBrandIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc(
                        request.productId(), request.brandId(), applicationDate, applicationDate);
    }

}
