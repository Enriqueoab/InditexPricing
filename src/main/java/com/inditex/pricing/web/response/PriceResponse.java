package com.inditex.pricing.web.response;

import java.io.Serializable;
import java.time.LocalDateTime;

public class PriceResponse implements Serializable {

    private int productId;

    private int brandId;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private double price;

    public PriceResponse(int productId, int brandId, double price, LocalDateTime startDate, LocalDateTime endDate) {
        this.productId = productId;
        this.brandId = brandId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
    }

    public int getProductId() {
        return productId;
    }

    public int getBrandId() {
        return brandId;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public double getPrice() {
        return price;
    }
}
