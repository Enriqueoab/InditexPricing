package com.inditex.pricing.web.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "Price form with the information for the product and brand in a specific date requested")
public record PriceResponse(

        @Schema(description = "Product identifier", example = "35455")
        int productId,

        @Schema(description = "Brand identifier", example = "1")
        int brandId,

        @Schema(description = "The price will be set from", example = "2020-06-15T16:00:00")
        LocalDateTime startDate,

        @Schema(description = "The price will be set until", example = "2020-12-31T23:59:59")
        LocalDateTime endDate,

        @Schema(description = "The price to be set to", example = "38.95")
        double price
) {
}
