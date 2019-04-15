package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.database.ProductRepository;
import com.javaguru.shoppinglist.domain.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductUniqueNameValidationRule implements ProductValidationRule {

    private final ProductRepository repository;

    @Autowired
    public ProductUniqueNameValidationRule(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public void validate(Product product) {
        checkNotNull(product);

        if (repository.existInRepositoryByName(product.getName())) {
            throw new ProductValidationException("Such name already exists in repository");
        }
    }
}
