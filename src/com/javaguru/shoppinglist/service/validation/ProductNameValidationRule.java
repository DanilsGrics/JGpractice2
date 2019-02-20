package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;

public class ProductNameValidationRule implements ProductValidationRule {

    @Override
    public void validate(Product product) {
        checkNotNull(product);
        if (product.getName().length() < 3 || product.getName().length() > 32) {
            throw new ProductValidationException("Name must contain 3 - 32 symbols");
        }
    }
}