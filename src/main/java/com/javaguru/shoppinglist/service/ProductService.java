package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.database.ProductRepository;
import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.service.validation.ProductValidationService;

public class ProductService {

    private ProductRepository repository;
    private ProductValidationService validationService;

    public ProductService(ProductRepository repository,
                          ProductValidationService validationService) {
        this.repository = repository;
        this.validationService = validationService;
    }

    public Long addProduct(Product product) {
        validationService.validate(product);
        Product newProduct = repository.insertProduct(product);
        return newProduct.getId();
    }

    public Product findProductById(Long id) {
        return repository
                .findProductById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found, id: " + id));
    }

}