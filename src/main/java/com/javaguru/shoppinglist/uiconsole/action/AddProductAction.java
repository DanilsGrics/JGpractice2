package com.javaguru.shoppinglist.uiconsole.action;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.service.ProductService;
import com.javaguru.shoppinglist.service.validation.ProductValidationException;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

@Component
public class AddProductAction implements Action {

    private static final String ACTION_NAME = "Add Product";

    private final ProductService productService;

    public AddProductAction(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void initiate() {

        Product product = new Product();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter product name:");
        String name = scanner.nextLine();

        System.out.println("Enter product price: ");
        String priceWithDotOrComma = scanner.next();
        BigDecimal price = new BigDecimal(priceWithDotOrComma.replace(',', '.'));

        System.out.println("Enter product discount: ");
        String discountWithDotOrComma = scanner.next();
        Double discount = Double.parseDouble(discountWithDotOrComma
                .replace(',', '.'));

        System.out.println("Enter product category: ");
        String category = scanner.next();

        System.out.println("Enter product description: ");
        String description = scanner.next();

        product.setName(name);
        product.setPrice(price);
        product.setDiscount(discount);
        product.setCategory(category);
        product.setDescription(description);

        try {
            Long response = productService.addProduct(product);

            System.out.println("Response: " + response);

        } catch (InputMismatchException | IllegalArgumentException exception) {
            System.out.println("Input error!");
        } catch (ProductValidationException exception) {
            System.out.println(exception.getMessage());
        }
    }

    @Override
    public String toString() {
        return ACTION_NAME;
    }
}
