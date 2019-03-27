package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ProductPriceValidationRule implements ProductValidationRule {

    private final BigDecimal ZERO_PRICE = new BigDecimal(0);

    @Override
    public void validate(Product product) {
        checkNotNull(product);
        if (product.getPrice().compareTo(ZERO_PRICE) <= 0) {
            throw new ProductValidationException("Price cannot be zero or less");
        }
    }
}
