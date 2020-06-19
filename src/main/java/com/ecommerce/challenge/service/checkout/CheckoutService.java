package com.ecommerce.challenge.service.checkout;

import com.ecommerce.challenge.core.entity.Product;
import com.ecommerce.challenge.dto.PriceDto;
import com.ecommerce.challenge.error.ValidationErrorType;
import com.ecommerce.challenge.repository.ProductRepository;
import com.ecommerce.challenge.service.exception.ServiceException;
import com.ecommerce.challenge.service.pricing.PricingService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class CheckoutService {

    private final PricingService pricingService;
    private final ProductRepository productRepository;

    public PriceDto checkout(List<String> productCodes) {

        if (CollectionUtils.isEmpty(productCodes)) {
            log.error("Invalid data for cart products.");
            throw new ServiceException(ValidationErrorType.INVALID_DATA);
        }

        //Could have used one data structure like Map<Product, Long>
        // which could help to avoid extra data structure being used
        Map<String, Long> productByQuantity = getProductsByQuantity(productCodes);
        List<Product> products = productRepository.findByProductCodeIn(new HashSet<>(productCodes));

        if (products.size() != productByQuantity.keySet().size()) {
            // because of one product is in cart which is not in database then if we dont throw this exception
            // then that product will be counted as free product
            throw new ServiceException(ValidationErrorType.PRODUCT_NOT_FOUND_IN_DATABASE);
        }
        if (CollectionUtils.isEmpty(products)) {
            throw new ServiceException(ValidationErrorType.UNABLE_TO_FIND_PRODUCTS);
        }

        BigDecimal totalPrice = pricingService.calculatePrice(productByQuantity, products);

        return toDto(totalPrice);
    }

    private Map<String, Long> getProductsByQuantity(List<String> productCodes) {
        return productCodes.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    private PriceDto toDto(BigDecimal price) {
        return PriceDto.builder().price(price).build();
    }
}
