package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.database.ProductRepository;
import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.service.validation.ProductValidationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class DefaultProductService implements ProductService {

    private ProductRepository repository;
    private ProductValidationService validationService;

    @Autowired
    public DefaultProductService(ProductRepository repository,
                                 ProductValidationService validationService) {
        this.repository = repository;
        this.validationService = validationService;
    }

    @Transactional
    public Long addProduct(Product product) {
        validationService.validate(product);
        return repository.insertProduct(product);
    }

    public Product findProductById(Long id) {
        return repository
                .findProductById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found, id: " + id));
    }

}