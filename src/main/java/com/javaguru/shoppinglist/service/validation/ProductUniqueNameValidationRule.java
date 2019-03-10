package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.database.ProductRepository;
import com.javaguru.shoppinglist.domain.Product;

public class ProductUniqueNameValidationRule implements ProductValidationRule {

    private final ProductRepository repository;

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
