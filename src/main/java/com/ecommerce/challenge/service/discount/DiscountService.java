package com.ecommerce.challenge.service.discount;

import com.ecommerce.challenge.core.entity.Product;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class DiscountService {

    //3 for 100
    //40 unit price
    //qty ordered is 7
    public BigDecimal applyDiscount(Long quantity, Product product) {

        if (product.getDiscount() != null) {

            //Could have made more simpler,
            //Also the business logic could be in Entities in a domain driven fashion for extensibility
            int discountedUnits = quantity.intValue() / product.getDiscount().getMinimumQuantityRequired();

            BigDecimal actualUnitPrice = product.getUnitPrice()
                    .multiply(BigDecimal.valueOf(product.getDiscount().getMinimumQuantityRequired()));
            BigDecimal discountedUnitPrice = product.getDiscount().getDiscountedValue();

            return actualUnitPrice
                    .subtract(discountedUnitPrice)
                    .multiply(BigDecimal.valueOf(discountedUnits));
        }

        return BigDecimal.ZERO;
    }
}
