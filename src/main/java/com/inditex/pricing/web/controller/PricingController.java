package com.inditex.pricing.web.controller;

import com.inditex.pricing.application.ports.in.GetPriceUseCase;
import com.inditex.pricing.domain.exception.DateTimeFormatException;
import com.inditex.pricing.domain.model.Price;
import com.inditex.pricing.domain.validation.DataValidator;
import com.inditex.pricing.web.request.PriceRequest;
import com.inditex.pricing.web.response.PriceResponse;
import com.inditex.pricing.domain.exception.DefaultApiResponses;
import com.inditex.pricing.domain.exception.PriceNotFoundException;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Digits;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@Tag(name = "Price")
@RequestMapping("/v1/price")
@Validated
public class PricingController {
    private final GetPriceUseCase getPriceUseCase;

    public PricingController(GetPriceUseCase getPriceUseCase) {
        this.getPriceUseCase = getPriceUseCase;
    }

    @GetMapping
    public PriceResponse getPrice(@RequestParam int productId, @RequestParam int brandId,
                                  @RequestParam String applicationDate) throws PriceNotFoundException, DateTimeFormatException {
        var request = new PriceRequest(productId, brandId, applicationDate);
        return getPriceUseCase.getApplicablePrice(request);
    }


}

