package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;

import java.math.BigDecimal;

public class ProductPriceValidationRule implements ProductValidationRule {

    private final BigDecimal zeroPrice = new BigDecimal(0);

    @Override
    public void validate(Product product) {
        checkNotNull(product);
        if (product.getPrice().compareTo(zeroPrice) <= 0) {
            throw new ProductValidationException("Price cannot be zero or less");
        }
    }
}
