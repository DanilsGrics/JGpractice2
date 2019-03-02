package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;

public class ProductNameValidationRule implements ProductValidationRule {

    private final int MINIMAL_NAME_LENGTH = 3;
    private final int MAXIMAL_NAME_LENGTH = 32;

    @Override
    public void validate(Product product) {
        checkNotNull(product);
        if (product
                .getName().length() < MINIMAL_NAME_LENGTH ||
                product.getName().length() > MAXIMAL_NAME_LENGTH) {
            throw new ProductValidationException("Name must contain 3 - 32 symbols");
        }
    }
}
