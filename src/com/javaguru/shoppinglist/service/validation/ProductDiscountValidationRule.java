package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;

public class ProductDiscountValidationRule implements ProductValidationRule {

    static int maximalDiscount = 100;

    @Override
    public void validate(Product product) {
        checkNotNull(product);
        if (product.getDiscount() > maximalDiscount) {
            throw new ProductValidationException("Discount cannot be more than 100%");
        }
    }
}
