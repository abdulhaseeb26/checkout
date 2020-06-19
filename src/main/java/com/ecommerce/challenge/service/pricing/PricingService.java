package com.ecommerce.challenge.service.pricing;

import com.ecommerce.challenge.core.entity.Product;
import com.ecommerce.challenge.service.discount.DiscountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Service
public class PricingService {

    private final DiscountService discountService;

    public BigDecimal calculatePrice(Map<String, Long> productsQuantityMap, List<Product> products) {

        BigDecimal totalPrice = BigDecimal.ZERO;

        for (Product product : products) {

            BigDecimal price = product.getUnitPrice().multiply(BigDecimal.valueOf(productsQuantityMap.get(product.getProductCode())));
            price = price.subtract(discountService.applyDiscount(productsQuantityMap.get(product.getProductCode()), product));

            totalPrice = totalPrice.add(price);

        }
        return totalPrice;
    }
}
