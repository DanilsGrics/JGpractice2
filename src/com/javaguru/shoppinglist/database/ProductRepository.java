package com.javaguru.shoppinglist.database;

import com.javaguru.shoppinglist.domain.Product;
import java.util.HashMap;
import java.util.Map;

public class ProductRepository {

    private Map<Long, Product> database = new HashMap<>();
    private Long productIdSequence = 0L;

    public Product findProductById(Long id) {
        return database.get(id);
    }

    public Product insertProduct(Product product) {
        product.setId(productIdSequence);
        database.put(productIdSequence, product);
        productIdSequence++;
        return product;
    }

}
