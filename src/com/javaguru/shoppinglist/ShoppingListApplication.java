package com.javaguru.shoppinglist;

import com.javaguru.shoppinglist.database.ProductRepository;
import com.javaguru.shoppinglist.service.ProductService;
import com.javaguru.shoppinglist.service.validation.ProductMaximalDiscountValidationRule;
import com.javaguru.shoppinglist.service.validation.ProductMinimalDiscountValidationRule;
import com.javaguru.shoppinglist.service.validation.ProductMinimalPriceForDiscountValidationRule;
import com.javaguru.shoppinglist.service.validation.ProductNameValidationRule;
import com.javaguru.shoppinglist.service.validation.ProductPriceValidationRule;
import com.javaguru.shoppinglist.service.validation.ProductUniqueNameValidationRule;
import com.javaguru.shoppinglist.service.validation.ProductValidationRule;
import com.javaguru.shoppinglist.service.validation.ProductValidationService;
import com.javaguru.shoppinglist.uiconsole.ConsoleUI;

import java.util.HashSet;
import java.util.Set;

class ShoppingListApplication {

    public static void main(String[] args) {

        ProductRepository repository = new ProductRepository();

        ProductValidationRule productNameValidationRule = new ProductNameValidationRule();
        ProductValidationRule productUniqueNameValidationRule = new
                ProductUniqueNameValidationRule(repository);
        ProductValidationRule productMaximalDiscountValidationRule = new
                ProductMaximalDiscountValidationRule();
        ProductValidationRule productMinimalDiscountValidationRule = new
                ProductMinimalDiscountValidationRule();
        ProductValidationRule productMinimalPriceForDiscountValidationRule = new
                ProductMinimalPriceForDiscountValidationRule();
        ProductValidationRule productPriceValidationRule = new ProductPriceValidationRule();

        Set<ProductValidationRule> rules = new HashSet<>();
        rules.add(productNameValidationRule);
        rules.add(productUniqueNameValidationRule);
        rules.add(productMaximalDiscountValidationRule);
        rules.add(productMinimalDiscountValidationRule);
        rules.add(productMinimalPriceForDiscountValidationRule);
        rules.add(productPriceValidationRule);

        ProductValidationService validationService = new ProductValidationService(rules);

        ProductService productService = new ProductService(repository, validationService);

        ConsoleUI ui = new ConsoleUI(productService);
        ui.start();
    }
}
