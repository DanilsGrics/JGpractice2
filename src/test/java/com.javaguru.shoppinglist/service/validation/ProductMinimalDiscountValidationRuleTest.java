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

    private final Double UNSUCCESSFUL_DISCOUNT_VALUE = -3D;
    private final Double SUCCESSFUL_DISCOUNT_VALUE = 10D;
    private final BigDecimal TEST_PRODUCT_PRICE = new BigDecimal(25);

    @Spy
    @InjectMocks
    private ProductMinimalDiscountValidationRule victim;

    private Product product;

    @Test
    public void shouldThrowProductValidationExceptionDiscountIsTooLow() {
        product = product(UNSUCCESSFUL_DISCOUNT_VALUE);

        assertThatThrownBy(() -> victim.validate(product))
                .isInstanceOf(ProductValidationException.class)
                .hasMessage("Discount cannot be less than 0%");
        verify(victim).checkNotNull(product);
    }

    @Test
    public void shouldValidateSuccess() {
        product = product(SUCCESSFUL_DISCOUNT_VALUE);

        victim.validate(product);
        verify(victim).checkNotNull(product);
    }

    private Product product(Double discount) {
        Product product = new Product();

        product.setPrice(TEST_PRODUCT_PRICE);
        product.setDiscount(discount);
        return product;
    }
}
