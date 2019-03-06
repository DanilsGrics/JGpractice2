package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;

public class ProductMaximalDiscountValidationRule implements ProductValidationRule {

    private final double MAXIMAL_DISCOUNT = 100;

    @Override
    public void validate(Product product) {
        checkNotNull(product);
        if (product.getDiscount() > MAXIMAL_DISCOUNT) {
            throw new ProductValidationException("Discount cannot be more than 100%");
        }
    }
}
