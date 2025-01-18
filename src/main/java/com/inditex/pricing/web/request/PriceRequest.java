package com.inditex.pricing.web.request;

public record PriceRequest(

        int productId,

        int brandId,

        String applicationDate
) {
}
