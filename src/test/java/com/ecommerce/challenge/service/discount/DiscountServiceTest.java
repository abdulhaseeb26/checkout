package com.ecommerce.challenge.service.discount;

import com.ecommerce.challenge.core.entity.Product;
import com.ecommerce.challenge.fixture.ProductFixture;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class DiscountServiceTest {

    @InjectMocks
    private DiscountService discountService;

    @Test
    public void testApplyDiscount() {

        Product product = ProductFixture.get("a", new BigDecimal("40.0"));

        BigDecimal discount = discountService.applyDiscount(2L, product);

        assertThat(discount).isEqualTo(new BigDecimal("20.0"));

    }

    @Test
    public void testApplyDiscount_whenDiscountNoApplicable() {

        Product product = ProductFixture.getWithoutDiscount("c", new BigDecimal("40.0"));

        BigDecimal discount = discountService.applyDiscount(2L, product);

        assertThat(discount).isEqualTo(BigDecimal.ZERO);
    }

    @Test
    public void testApplyDiscount_whenQuantityLessThanMinimumQuantityRequired() {

        Product product = ProductFixture.get("a", new BigDecimal("40.0"));

        BigDecimal discount = discountService.applyDiscount(1L, product);

        assertThat(discount).isEqualTo(new BigDecimal("0.0"));
    }

}
