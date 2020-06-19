package com.ecommerce.challenge.repository;

import com.ecommerce.challenge.core.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findByProductCodeIn(Collection<String> productCodes);
}
