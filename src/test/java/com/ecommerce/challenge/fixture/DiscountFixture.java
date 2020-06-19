package com.ecommerce.challenge.fixture;

import com.ecommerce.challenge.core.entity.Discount;

import java.math.BigDecimal;

public class DiscountFixture {

    public static Discount get() {
        return Discount.builder()
                .id(1)
                .minimumQuantityRequired(2)
                .discountedValue(new BigDecimal(60))
                .build();
    }
}
