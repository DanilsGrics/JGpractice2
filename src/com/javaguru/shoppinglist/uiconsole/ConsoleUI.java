package com.javaguru.shoppinglist.uiconsole;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.service.ProductService;
import com.javaguru.shoppinglist.service.validation.ProductValidationException;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleUI {

    private ProductService productService = new ProductService();

    public void start() {
        for (; ; ) {
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
            } catch (InputMismatchException | NumberFormatException | ProductValidationException e) {
                if (e instanceof ProductValidationException) {
                    System.out.println(e.getMessage());
                } else System.out.println("Input error!");
            }
        }
    }

    private void addProduct() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter product name:");
        String name = scanner.nextLine();
        System.out.println("Enter product price: ");
        String price = scanner.nextLine();
        System.out.println("Enter product discount: ");
        Integer discount = scanner.nextInt();
        System.out.println("Enter product category: ");
        String category = scanner.next();
        System.out.println("Enter product description: ");
        String description = scanner.next();

        Product product = new Product();

        product.setName(name);
        product.setPrice(new BigDecimal(price));
        product.setDiscount(discount);
        product.setCategory(category);
        product.setDescription(description);

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

}
