package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.database.ProductRepository;
import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.service.validation.ProductValidationException;
import com.javaguru.shoppinglist.service.validation.ProductValidationService;

import java.math.BigDecimal;
import java.util.Scanner;

public class ProductService {


    private ProductRepository repository = new ProductRepository();
    private ProductValidationService validationService = new ProductValidationService();

    public Long addProduct(Product product) {
        validationService.validate(product);
        Product newProduct = repository.insertProduct(product);
        return newProduct.getId();
    }

    public Product findProductById(Long id) {
        return repository.findProductById(id);
    }

}
