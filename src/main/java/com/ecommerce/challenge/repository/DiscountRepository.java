package com.ecommerce.challenge.repository;

import com.ecommerce.challenge.core.entity.Discount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountRepository extends JpaRepository<Discount, Integer> {
}
