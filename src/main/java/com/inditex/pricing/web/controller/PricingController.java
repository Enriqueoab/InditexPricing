package com.inditex.pricing.web.controller;

import com.inditex.pricing.application.ports.in.GetPriceUseCase;
import com.inditex.pricing.domain.exception.DateTimeFormatException;
import com.inditex.pricing.domain.exception.PriceNotFoundException;
import com.inditex.pricing.web.request.PriceRequest;
import com.inditex.pricing.web.response.PriceResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Price")
@RequestMapping("/v1/price")
public class PricingController {
    private final GetPriceUseCase getPriceUseCase;

    public PricingController(GetPriceUseCase getPriceUseCase) {
        this.getPriceUseCase = getPriceUseCase;
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Data requested delivered successfully"),
            @ApiResponse(responseCode = "404", description = "Bad request, price not found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = PriceNotFoundException.class))),

            @ApiResponse(responseCode = "409", description = "Wrong date format please introduce it as yyyy-MM-ddThh:mm:ss",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = DateTimeFormatException.class)))
    })
    @GetMapping
    public PriceResponse getPrice(@RequestParam int productId, @RequestParam int brandId,
                                  @RequestParam String applicationDate) throws PriceNotFoundException, DateTimeFormatException {
        var request = new PriceRequest(productId, brandId, applicationDate);
        return getPriceUseCase.getApplicablePrice(request);
    }


}

