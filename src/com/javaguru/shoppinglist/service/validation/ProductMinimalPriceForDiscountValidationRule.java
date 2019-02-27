package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;

import java.math.BigDecimal;

public class ProductMinimalPriceForDiscountValidationRule implements ProductValidationRule {

    private final BigDecimal minimalPriceForDiscount = new BigDecimal(20);

    @Override
    public void validate(Product product) {
        checkNotNull(product);
        if (product
                .getPrice()
                .compareTo(minimalPriceForDiscount) < 0 && product.getDiscount() > 0) {
            throw new ProductValidationException(
                    "Discount cannot be assigned to product which costs less than 20$");
        }
    }
}
