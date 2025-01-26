package com.inditex.pricing.infrastructure.db.service;

import com.inditex.pricing.application.ports.out.GetPricePort;
import com.inditex.pricing.domain.constants.ExceptionMessage;
import com.inditex.pricing.infrastructure.LoggerConfig;
import com.inditex.pricing.infrastructure.db.repo.PriceRepository;
import com.inditex.pricing.infrastructure.exception.PriceNotFoundException;
import com.inditex.pricing.infrastructure.model.Price;
import com.inditex.pricing.infrastructure.web.request.PriceRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class GetPriceAdapter implements GetPricePort {

    private final PriceRepository priceRepository;

    private final Logger logger;

    public GetPriceAdapter(PriceRepository priceRepository, LoggerConfig.LoggerFactoryBean loggerFactoryBean) {
        this.logger = LogManager.getLogger(GetPriceAdapter.class);
        this.priceRepository = priceRepository;
    }

    @Override
    public Price getApplicablePrice(PriceRequest request) throws PriceNotFoundException {
        Price price = getPrice(request);

        if (price == null) {
            logger.error("No data found with the request: {}", request);
            throw new PriceNotFoundException(ExceptionMessage.PRICE_NOT_FOUND + ", productId: " + request.productId() + ", brandId: "
                    + request.brandId() + " and applicationDate: " + request.applicationDate());
        }

        return price;
    }

    public Price getPrice(PriceRequest request) {
        logger.info("Getting price...");
        LocalDateTime applicationDate = LocalDateTime.parse(request.applicationDate());
        return priceRepository
                .findFirstByProductIdAndBrandIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc(
                        request.productId(), request.brandId(), applicationDate);
    }

}
