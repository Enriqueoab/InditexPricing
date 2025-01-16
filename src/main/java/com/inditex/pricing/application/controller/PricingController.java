package com.inditex.pricing.application.controller;

import com.inditex.pricing.domain.ports.in.GetPriceUseCase;
import com.inditex.pricing.application.request.PriceRequest;
import com.inditex.pricing.application.response.PriceResponse;
import com.inditex.pricing.utils.exception.DefaultApiResponses;
import com.inditex.pricing.utils.exception.PriceNotFoundException;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class PricingController {
    private final GetPriceUseCase getPriceUseCase;

    public PricingController(GetPriceUseCase getPriceUseCase) {
        this.getPriceUseCase = getPriceUseCase;
    }

    @GetMapping
    @DefaultApiResponses
    public PriceResponse getPrice(@RequestParam int productId, @RequestParam int brandId, @RequestParam String date) throws PriceNotFoundException {
        LocalDateTime applicationDate = LocalDateTime.parse(date);
        PriceRequest request = new PriceRequest(productId, brandId, applicationDate);
        return getPriceUseCase.getApplicablePrice(request);
    }
}

