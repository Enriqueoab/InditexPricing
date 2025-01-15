package com.inditex.pricing.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;
import lombok.Data;

@Data
@Entity
@AllArgsConstructor
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int brandId;
    private int productId;
    private int priceList;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private boolean priority;
    private double price;
    private String curr; //TODO: Make it an enum

}
