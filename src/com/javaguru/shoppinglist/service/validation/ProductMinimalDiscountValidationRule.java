package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;

public class ProductMinimalDiscountValidationRule implements ProductValidationRule {

    private final double MINIMAL_DISCOUNT = 0;

    @Override
    public void validate(Product product) {
        checkNotNull(product);
        if (product.getDiscount() < MINIMAL_DISCOUNT) {
            throw new ProductValidationException("Discount cannot be less than 0%");
        }
    }
}
