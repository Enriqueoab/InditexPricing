package com.inditex.pricing.application.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class PriceResponse {
    private int productId;
    private int brandId;
    private int priceList;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private double price;

//    public PriceResponse(int productId, int brandId, int priceList, LocalDateTime startDate, LocalDateTime endDate, double price) {
//        this.productId = productId;
//        this.brandId = brandId;
//        this.priceList = priceList;
//        this.startDate = startDate;
//        this.endDate = endDate;
//        this.price = price;
//    }

}
