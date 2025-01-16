package com.inditex.pricing.application.controller;

import com.inditex.pricing.domain.ports.in.GetPriceUseCase;
import com.inditex.pricing.application.request.PriceRequest;
import com.inditex.pricing.application.response.PriceResponse;
import com.inditex.pricing.utils.exception.DefaultApiResponses;
import com.inditex.pricing.utils.exception.PriceNotFoundException;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@Tag(name = "Price")
@RequestMapping("/price")
public class PricingController {
    private final GetPriceUseCase getPriceUseCase;

    public PricingController(GetPriceUseCase getPriceUseCase) {
        this.getPriceUseCase = getPriceUseCase;
    }

    @GetMapping
    @DefaultApiResponses
    public PriceResponse getPrice(@Valid @RequestParam int productId, @Valid @RequestParam int brandId,
                                  @Valid @RequestParam String date) throws PriceNotFoundException {

        LocalDateTime applicationDate = LocalDateTime.parse(date);
        PriceRequest request = new PriceRequest(productId, brandId, applicationDate);
        return getPriceUseCase.getApplicablePrice(request);
    }
}

