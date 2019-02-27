package com.javaguru.shoppinglist.uiconsole;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.service.ProductService;
import com.javaguru.shoppinglist.service.validation.ProductValidationException;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleUI {

    private final ProductService productService;

    public ConsoleUI(ProductService productService) {
        this.productService = productService;
    }

    public void start() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            try {
                System.out.println("1. Add product");
                System.out.println("2. Find product by ID");
                System.out.println("3. Exit");
                int userInput = scanner.nextInt();
                switch (userInput) {
                    case 1:
                        addProduct();
                        break;
                    case 2:
                        findProductById();
                        break;
                    case 3:
                        System.exit(0);
                }
            } catch (InputMismatchException | NumberFormatException e) {
                System.out.println("Input error!");
            } catch (ProductValidationException u) {
                System.out.println(u.getMessage());
            }
        }
    }

    private void addProduct() {

        Product product = new Product();

        product.setName(askProductName());
        product.setPrice(askProductPrice());
        product.setDiscount(askProductDiscount());
        product.setCategory(askProductCategory());
        product.setDescription(askProductDescription());

        Long id = productService.addProduct(product);
        System.out.println("Result: " + id);
    }

    private void findProductById() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter product id, please: ");
        Long id = scanner.nextLong();
        Product product = productService.findProductById(id);
        System.out.println(product);
    }

    private String askProductName() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter product name:");
        return scanner.nextLine();
    }

    private BigDecimal askProductPrice() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter product price: ");
        return scanner.nextBigDecimal();
    }

    private Integer askProductDiscount() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter product discount: ");
        return scanner.nextInt();
    }

    private String askProductCategory() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter product category: ");
        return scanner.next();
    }

    private String askProductDescription() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter product description: ");
        return scanner.next();
    }
}
