package com.javaguru.shoppinglist;

import java.util.HashMap;
import java.util.Map;

public class DefaultProductService implements ProductService {

    private Map<Long, Product> database = new HashMap<>();
    private Long productIdSequence = 0L;

    public Product findBy(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id must be not null");
        }
        return database.get(id);
    }

    @Override
    public Long create(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Cannot be null");
        }

        if (product.getPrice().doubleValue() <= 0) {
            throw new IllegalArgumentException("Price cannot be zero or less");
        }

        if (product.getDiscount() > 100) {
            throw new IllegalArgumentException("Discount cannot be more than 100%");
        }

        if (product.getName().length() < 3 || product.getName().length() > 32) {
            throw new IllegalArgumentException("Name must contain 3 - 32 symbols");
        }

        product.setId(productIdSequence);

        database.put(productIdSequence, product);
        return productIdSequence++;
    }

}
