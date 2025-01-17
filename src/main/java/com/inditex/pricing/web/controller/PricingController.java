package com.inditex.pricing.web.controller;

import com.inditex.pricing.application.ports.in.GetPriceUseCase;
import com.inditex.pricing.domain.model.Price;
import com.inditex.pricing.web.request.PriceRequest;
import com.inditex.pricing.web.response.PriceResponse;
import com.inditex.pricing.domain.exception.DefaultApiResponses;
import com.inditex.pricing.domain.exception.PriceNotFoundException;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;

@RestController
@Tag(name = "Price")
@RequestMapping("/v1/price")
public class PricingController {
    private final GetPriceUseCase getPriceUseCase;

    public PricingController(GetPriceUseCase getPriceUseCase) {
        this.getPriceUseCase = getPriceUseCase;
    }

    @GetMapping
    @DefaultApiResponses
    public PriceResponse getPrice(@Valid @RequestParam int productId, @Valid @RequestParam int brandId,
                                  @Valid @RequestParam String date) throws PriceNotFoundException {

        var applicationDate = LocalDateTime.parse(date);
        var request = new PriceRequest(productId, brandId, applicationDate);
        return buildPriceResponse(getPriceUseCase.getApplicablePrice(request));
    }

    private PriceResponse buildPriceResponse(Price price) {
        return new PriceResponse(price.getProductId(),
                price.getBrandId(),
                price.getPrice(),
                price.getStartDate(),
                price.getEndDate());
    }

}

