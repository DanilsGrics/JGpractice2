package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ProductNameValidationRuleTest {

    @Spy
    private ProductNameValidationRule victim;

    private Product product;

    @Test
    public void shouldThrowProductValidationExceptionNameIsNotLongEnough() {
        product = product("qq");

        assertThatThrownBy(() -> victim.validate(product))
                .isInstanceOf(ProductValidationException.class)
                .hasMessage("Name must contain 3 - 32 symbols");
        verify(victim).checkNotNull(product);
    }

    @Test
    public void shouldThrowProductValidationExceptionNameIsTooLong() {
        product = product("qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq");

        assertThatThrownBy(() -> victim.validate(product))
                .isInstanceOf(ProductValidationException.class)
                .hasMessage("Name must contain 3 - 32 symbols");
        verify(victim).checkNotNull(product);
    }

    @Test
    public void shouldValidateSuccess() {
        product = product("good name");
        victim.validate(product);
        verify(victim).checkNotNull(product);
    }

    private Product product(String name) {
        Product product = new Product();
        product.setName(name);
        return product;
    }

}

