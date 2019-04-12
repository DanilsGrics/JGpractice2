package com.javaguru.shoppinglist.database;

import com.javaguru.shoppinglist.domain.Product;

import java.util.Optional;

public interface ProductRepository {

    Long insertProduct(Product product);

    Optional<Product> findProductById(Long id);

    boolean existInRepositoryByName(String name);
}
