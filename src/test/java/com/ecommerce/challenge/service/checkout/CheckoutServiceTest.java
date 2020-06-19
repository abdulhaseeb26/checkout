package com.ecommerce.challenge.service.checkout;

import com.ecommerce.challenge.core.entity.Product;
import com.ecommerce.challenge.dto.PriceDto;
import com.ecommerce.challenge.fixture.ProductFixture;
import com.ecommerce.challenge.repository.ProductRepository;
import com.ecommerce.challenge.service.pricing.PricingService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyCollection;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CheckoutServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private PricingService pricingService;

    @InjectMocks
    private CheckoutService checkoutService;

    @Test
    public void testCheckout() {

        List<String> productCodes = Arrays.asList("a", "a", "b");
        when(productRepository.findByProductCodeIn(anyCollection())).thenReturn(getProductList());
        when(pricingService.calculatePrice(anyMap(), anyList())).thenReturn(new BigDecimal("130.0"));

        PriceDto priceDto = checkoutService.checkout(productCodes);

        assertThat(priceDto.getPrice()).isEqualTo(new BigDecimal("130.0"));
        verify(productRepository, times(1)).findByProductCodeIn(anyCollection());
        verify(pricingService, times(1)).calculatePrice(anyMap(), anyList());
    }

    private List<Product> getProductList() {

        List<Product> products = new ArrayList<>();
        products.add(ProductFixture.get("a", new BigDecimal(40)));
        products.add(ProductFixture.get("b", new BigDecimal(50)));

        return products;

    }
}

