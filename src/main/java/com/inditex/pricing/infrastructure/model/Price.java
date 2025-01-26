package com.inditex.pricing.infrastructure.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Price {

    private int brandId;

    private int productId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int priceList;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private boolean priority;

    private double price;

    private String curr;

    public Price(int brandId, int productId, int priceList, LocalDateTime startDate, LocalDateTime endDate,
                 boolean priority, double price, String curr) {
        this.brandId = brandId;
        this.productId = productId;
        this.priceList = priceList;
        this.startDate = startDate;
        this.endDate = endDate;
        this.priority = priority;
        this.price = price;
        this.curr = curr;
    }

    public Price() {

    }

    public int getBrandId() {
        return this.brandId;
    }

    public int getProductId() {
        return this.productId;
    }

    public int getPriceList() {
        return this.priceList;
    }

    public LocalDateTime getStartDate() {
        return this.startDate;
    }

    public LocalDateTime getEndDate() {
        return this.endDate;
    }

    public boolean isPriority() {
        return this.priority;
    }

    public double getPrice() {
        return this.price;
    }

    public String getCurr() {
        return this.curr;
    }
}
