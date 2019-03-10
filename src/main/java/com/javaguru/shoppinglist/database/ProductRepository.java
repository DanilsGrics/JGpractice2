package com.javaguru.shoppinglist.database;

import com.javaguru.shoppinglist.domain.Product;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ProductRepository {

    private Map<Long, Product> database = new HashMap<>();
    private Long productIdSequence = 0L;

    public Optional<Product> findProductById(Long id) {
        return Optional.ofNullable(database.get(id));
    }

    public Product insertProduct(Product product) {
        product.setId(productIdSequence);
        database.put(productIdSequence, product);
        productIdSequence++;
        return product;
    }

    public boolean existInRepositoryByName(String name) {
        return database
                .values()
                .stream()
                .anyMatch(product -> product.getName().equalsIgnoreCase(name));
    }
}