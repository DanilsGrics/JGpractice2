package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.database.ProductInMemoryRepository;
import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.service.validation.ProductValidationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductService {

    private ProductInMemoryRepository repository;
    private ProductValidationService validationService;

    @Autowired
    public ProductService(ProductInMemoryRepository repository,
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