package com.inditex.pricing.infrastructure.repo;

import com.inditex.pricing.domain.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceRepository extends JpaRepository<Price, Integer> {
//    Optional<Price> findApplicablePrice(int productId, int brandId, LocalDateTime applicationDate);

    Optional<Price> findPriceById(int id);
}
