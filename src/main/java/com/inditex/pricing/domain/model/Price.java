package com.inditex.pricing.domain.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;
import lombok.Data;

@Data
@Entity
@AllArgsConstructor
public class Price {
    private int brandId;
    private int productId;
    private int priceList;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private double price;

}
