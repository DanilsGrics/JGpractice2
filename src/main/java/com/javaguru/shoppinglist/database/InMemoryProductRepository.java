package com.javaguru.shoppinglist.database;

import com.javaguru.shoppinglist.domain.Product;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
@Profile("inmemoryrepository")
public class InMemoryProductRepository implements ProductRepository {

    private Map<Long, Product> database = new HashMap<>();
    private Long productIdSequence = 0L;

    public Optional<Product> findProductById(Long id) {
        return Optional.ofNullable(database.get(id));
    }

    public Long insertProduct(Product product) {
        product.setId(productIdSequence++);
        database.put(product.getId(), product);
        return product.getId();
    }

    public boolean existInRepositoryByName(String name) {
        return database
                .values()
                .stream()
                .anyMatch(product -> product.getName().equalsIgnoreCase(name));
    }
}