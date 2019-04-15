package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.domain.Product;

public interface ProductService {

    Long addProduct(Product product);

    Product findProductById(Long id);
}
