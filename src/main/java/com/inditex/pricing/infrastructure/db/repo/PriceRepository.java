package com.inditex.pricing.infrastructure.db.repo;

import com.inditex.pricing.infrastructure.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface PriceRepository extends JpaRepository<Price, Integer> {


    @Query("""
            SELECT p FROM Price p
            WHERE p.productId = :productId
            AND p.brandId = :brandId
            AND :applyDate BETWEEN p.startDate AND p.endDate
            ORDER BY p.priority DESC
            LIMIT 1
            """)
    Price findFirstByProductIdAndBrandIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc(
            int productId,
            int brandId,
            LocalDateTime applyDate);
}
