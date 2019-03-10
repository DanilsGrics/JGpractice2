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
public class ProductMinimalDiscountValidationRuleTest {

    @Spy
    @InjectMocks
    private ProductMinimalDiscountValidationRule victim;

    private Product product;

    @Test
    public void shouldThrowProductValidationExceptionDiscountIsTooLow() {
        product = product(-3D);

        assertThatThrownBy(() -> victim.validate(product))
                .isInstanceOf(ProductValidationException.class)
                .hasMessage("Discount cannot be less than 0%");
        verify(victim).checkNotNull(product);
    }

    @Test
    public void shouldValidateSuccess() {
        product = product(10D);
        victim.validate(product);
        verify(victim).checkNotNull(product);
    }

    private Product product(Double discount) {
        Product product = new Product();
        product.setPrice(new BigDecimal(25));
        product.setDiscount(discount);
        return product;
    }
}
