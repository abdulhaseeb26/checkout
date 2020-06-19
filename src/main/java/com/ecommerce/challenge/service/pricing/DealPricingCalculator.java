//package com.ecommerce.challenge.service.pricing;
//
//import com.ecommerce.challenge.core.entity.Product;
//import com.ecommerce.challenge.repository.ProductRepository;
//
//import java.math.BigDecimal;
//import java.util.Map;
//
//public class DealPricingCalculator extends PricingService {
//
//    @Override
//    public BigDecimal calculatePrice(Map<String, Long> productCodesCount, Product product) {
//        return (BigDecimal
//                .valueOf(productCodesCount.get(product.getProductCode()) % product.getDiscount().getMinimumQuantityRequired())
//                .multiply(product.getUnitPrice())).add((BigDecimal.valueOf(productCodesCount.get(product.getProductCode()))
//                .subtract(BigDecimal.valueOf(
//                        productCodesCount.get(product.getProductCode()) % product.getDiscount().getMinimumQuantityRequired())))
//                .divide(BigDecimal.valueOf(product.getDiscount().getMinimumQuantityRequired()))
//                .multiply(product.getDiscount().getDiscountedValue()));
//    }
//}
