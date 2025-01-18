package com.inditex.pricing.web.response;

import java.time.LocalDateTime;

public record PriceResponse(
        int productId,

        int brandId,

        LocalDateTime startDate,

        LocalDateTime endDate,

        double price
) {
}
