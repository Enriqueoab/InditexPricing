package com.inditex.pricing.domain.model;

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

    private String curr; //TODO: Make it an enum


    public int getBrandId() {
        return brandId;
    }

    public int getProductId() {
        return productId;
    }

    public int getPriceList() {
        return priceList;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public boolean isPriority() {
        return priority;
    }

    public double getPrice() {
        return price;
    }

    public String getCurr() {
        return curr;
    }
}
