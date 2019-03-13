package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ProductPriceValidationRuleTest {

    private final BigDecimal UNSUCCESSFUL_PRICE_VALUE = new BigDecimal(0);
    private final BigDecimal SUCCESSFUL_PRICE_VALUE = new BigDecimal(25);

    @Spy
    @InjectMocks
    private ProductPriceValidationRule victim;

    private Product product;

    @Test
    public void shouldThrowProductValidationExceptionPriceIsIncorrect() {
        product = product(UNSUCCESSFUL_PRICE_VALUE);

        assertThatThrownBy(() -> victim.validate(product))
                .isInstanceOf(ProductValidationException.class)
                .hasMessage("Price cannot be zero or less");
        verify(victim).checkNotNull(product);
    }

    @Test
    public void shouldValidateSuccess() {
        product = product(SUCCESSFUL_PRICE_VALUE);
        victim.validate(product);
        verify(victim).checkNotNull(product);
    }

    private Product product(BigDecimal price) {
        Product product = new Product();

        product.setPrice(price);
        return product;
    }
}
