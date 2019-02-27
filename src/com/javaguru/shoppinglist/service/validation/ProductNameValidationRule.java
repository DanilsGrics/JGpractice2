package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;

public class ProductNameValidationRule implements ProductValidationRule {

    private final int minimalNameLength = 3;
    private final int maximalNameLength = 32;

    @Override
    public void validate(Product product) {
        checkNotNull(product);
        if (product.getName().length() < minimalNameLength || product.getName().length() > maximalNameLength) {
            throw new ProductValidationException("Name must contain 3 - 32 symbols");
        }
    }
}
