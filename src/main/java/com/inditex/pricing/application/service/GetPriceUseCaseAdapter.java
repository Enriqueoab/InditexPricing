package com.inditex.pricing.application.service;

import com.inditex.pricing.application.ports.in.GetPriceUseCase;
import com.inditex.pricing.application.ports.out.GetPricePort;
import com.inditex.pricing.infrastructure.LoggerConfig;
import com.inditex.pricing.infrastructure.exception.DateTimeFormatException;
import com.inditex.pricing.infrastructure.exception.PriceNotFoundException;
import com.inditex.pricing.infrastructure.model.Price;
import com.inditex.pricing.infrastructure.validation.DataValidator;
import com.inditex.pricing.infrastructure.web.request.PriceRequest;
import com.inditex.pricing.infrastructure.web.response.PriceResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class GetPriceUseCaseAdapter implements GetPriceUseCase {

    private final GetPricePort getPricePort;

    private final Logger logger;

    public GetPriceUseCaseAdapter(GetPricePort getPricePort, LoggerConfig.LoggerFactoryBean loggerFactoryBean) {
        this.logger = LogManager.getLogger(GetPriceUseCaseAdapter.class);
        this.getPricePort = getPricePort;
    }

    @Override
    public PriceResponse getApplicablePrice(PriceRequest request) throws PriceNotFoundException, DateTimeFormatException {
        DataValidator.validateLocalDateTimeFormat(request.applicationDate());
        logger.info("Calling DB with request: {}", request);
        return buildPriceResponse(getPricePort.getApplicablePrice(request));
    }

    private PriceResponse buildPriceResponse(Price price) {
        return new PriceResponse(price.getProductId(),
                price.getBrandId(),
                price.getStartDate(),
                price.getEndDate(),
                price.getPrice());
    }

}
