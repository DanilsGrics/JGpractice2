package com.javaguru.shoppinglist;

import com.javaguru.shoppinglist.config.SpringApplicationConfig;
import com.javaguru.shoppinglist.uiconsole.ConsoleUI;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class ShoppingListApplication {

    public static void main(String[] args) {

        ApplicationContext context
                = new AnnotationConfigApplicationContext(SpringApplicationConfig.class);

        ConsoleUI condoleApplication = context.getBean(ConsoleUI.class);
        condoleApplication.start();
    }
}
