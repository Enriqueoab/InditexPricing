package com.inditex.pricing.infrastructure.db.repo;

import com.inditex.pricing.domain.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;

public interface PriceRepository extends JpaRepository<Price, Integer> {

    Price findFirstByProductIdAndBrandIdAndStartDateBeforeAndEndDateAfterOrderByPriorityDesc(
            int productId,
            int brandId,
            LocalDateTime applyDate,
            LocalDateTime sameApplyDate);
}
