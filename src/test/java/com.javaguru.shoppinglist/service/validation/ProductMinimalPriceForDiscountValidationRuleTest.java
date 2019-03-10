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
public class ProductMinimalPriceForDiscountValidationRuleTest {

    @Spy
    @InjectMocks
    private ProductMinimalPriceForDiscountValidationRule victim;

    private Product product;


    @Test
    public void shouldThrowProductValidationExceptionPriceIsTooLow() {
        product = product(new BigDecimal(5));

        assertThatThrownBy(() -> victim.validate(product))
                .isInstanceOf(ProductValidationException.class)
                .hasMessage("Discount cannot be assigned to product which costs less than 20$");
        verify(victim).checkNotNull(product);
    }

    @Test
    public void shouldValidateSuccess() {
        product = product(new BigDecimal(25));
        victim.validate(product);
        verify(victim).checkNotNull(product);
    }

    private Product product(BigDecimal price) {
        Product product = new Product();
        product.setPrice(price);
        product.setDiscount(5D);
        return product;
    }
}
