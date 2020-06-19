package com.ecommerce.challenge.fixture;

import com.ecommerce.challenge.core.entity.Product;

import java.math.BigDecimal;

public class ProductFixture {

    public static Product get(String productCode, BigDecimal unitPrice) {

        return Product.builder()
                .id(1)
                .unitPrice(unitPrice)
                .productCode(productCode)
                .discount(DiscountFixture.get())
                .build();
    }

    public static Product getWithoutDiscount(String productCode, BigDecimal unitPrice) {

        return Product.builder()
                .id(1)
                .unitPrice(unitPrice)
                .productCode(productCode)
                .build();
    }


}
