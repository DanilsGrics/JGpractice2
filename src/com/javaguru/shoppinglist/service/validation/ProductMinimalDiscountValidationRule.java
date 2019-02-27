package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;

public class ProductMinimalDiscountValidationRule implements ProductValidationRule {

    private final double minimalDiscount = 0;

    @Override
    public void validate(Product product) {
        checkNotNull(product);
        if (product.getDiscount() < minimalDiscount) {
            throw new ProductValidationException("Discount cannot be less than 0%");
        }
    }
}
