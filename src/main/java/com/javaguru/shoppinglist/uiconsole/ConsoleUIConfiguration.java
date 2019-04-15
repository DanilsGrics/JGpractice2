package com.javaguru.shoppinglist.uiconsole;

import com.javaguru.shoppinglist.uiconsole.action.Action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
class ConsoleUIConfiguration {

    private final Action addProductAction;
    private final Action findProductByIdAction;
    private final Action exitAction;

    @Autowired
    public ConsoleUIConfiguration(Action addProductAction,
                                  Action findProductByIdAction, Action exitAction) {
        this.addProductAction = addProductAction;
        this.findProductByIdAction = findProductByIdAction;
        this.exitAction = exitAction;
    }

    @Bean
    ConsoleUI consoleUI() {
        List<Action> actions = new ArrayList<>();
        actions.add(addProductAction);
        actions.add(findProductByIdAction);
        actions.add(exitAction);
        return new ConsoleUI(actions);
    }
}
