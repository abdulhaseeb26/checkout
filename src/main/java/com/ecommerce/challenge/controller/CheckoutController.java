package com.ecommerce.challenge.controller;

import com.ecommerce.challenge.dto.PriceDto;
import com.ecommerce.challenge.service.checkout.CheckoutService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
public class CheckoutController {

    private final CheckoutService catalogService;

    @PostMapping(value = "/checkout")
    public PriceDto checkout(@RequestBody List<String> productCodes) {

        log.info("checkout processing starting...");
        return catalogService.checkout(productCodes);

    }

    @PostMapping(value = "checkout")
    public Long checkout(@RequestBody PricingRules pricingRules) {
        return 0l; //unique identifier for id or something
    }

    @PostMapping(value = "/checkout/{id}/product/")
    public List<ProductResponseObject> scan(@RequestBody String productCode, @PathVariable String id) {
        return new ArrayList<>();
    }

    @GetMapping(value = "checkout/{id}/price")
    public PriceDto getTotalPrice(@PathVariable String id) {
        return new PriceDto();
    }

    //Scan
    //Get total price
    //Initialize the pricing rules
}
