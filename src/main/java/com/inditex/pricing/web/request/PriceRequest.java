package com.inditex.pricing.web.request;

import java.time.LocalDateTime;

public class PriceRequest {
    private final int productId;
    private final int brandId;
    private final LocalDateTime applicationDate;

    public PriceRequest(int productId, int brandId, LocalDateTime applicationDate) {
        this.productId = productId;
        this.brandId = brandId;
        this.applicationDate = applicationDate;
    }

    public int getProductId() {
        return productId;
    }

    public int getBrandId() {
        return brandId;
    }

    public LocalDateTime getApplicationDate() {
        return applicationDate;
    }
}
