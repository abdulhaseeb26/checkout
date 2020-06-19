package com.ecommerce.challenge.service.pricing;

import com.ecommerce.challenge.core.entity.Product;
import com.ecommerce.challenge.fixture.ProductFixture;
import com.ecommerce.challenge.service.discount.DiscountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PricingServiceTest {

    @Mock
    private DiscountService discountService;

    @InjectMocks
    private PricingService pricingService;

    @Test
    public void testCalculatePrice() {

        List<Product> products = getProducts();
        Map<String, Long> productQuantityMap = getProductsQuantityMap();
        when(discountService.applyDiscount(productQuantityMap.get(products.get(0).getProductCode()), products.get(0))).thenReturn(new BigDecimal("20.0"));
        when(discountService.applyDiscount(productQuantityMap.get(products.get(1).getProductCode()), products.get(1))).thenReturn(new BigDecimal("0.0"));

        BigDecimal price = pricingService.calculatePrice(productQuantityMap, products);

        assertThat(price).isEqualTo(new BigDecimal("110.0"));
        verify(discountService, times(products.size())).applyDiscount(any(), any());
    }

    private Map<String, Long> getProductsQuantityMap() {
        Map<String, Long> productQuantityMap = new HashMap<>();

        productQuantityMap.put("a", 2L);
        productQuantityMap.put("b", 1L);

        return productQuantityMap;
    }

    private List<Product> getProducts() {
        List<Product> products = new ArrayList<>();

        products.add(ProductFixture.get("a", new BigDecimal("40.0")));
        products.add(ProductFixture.getWithoutDiscount("b", new BigDecimal("50.0")));

        return products;
    }

}
