package com.inditex.pricing.infrastructure.web.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Price form to request the price for a product and brand in a specific date")
public record PriceRequest(

        @Schema(description = "Product identifier", example = "35455")
        int productId,

        @Schema(description = "Brand identifier", example = "1")
        int brandId,

        @Schema(description = "Date to check the application price for", example = "2020-06-15T16:30:00")
        String applicationDate
) {
}
