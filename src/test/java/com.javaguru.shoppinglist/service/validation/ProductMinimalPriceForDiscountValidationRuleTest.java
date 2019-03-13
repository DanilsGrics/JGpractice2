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

    private final BigDecimal UNSUCCESSFUL_PRICE_VALUE = new BigDecimal(5);
    private final BigDecimal SUCCESSFUL_PRICE_VALUE = new BigDecimal(25);
    private final Double TEST_PRODUCT_DISCOUNT = 25D;

    @Spy
    @InjectMocks
    private ProductMinimalPriceForDiscountValidationRule victim;

    private Product product;

    @Test
    public void shouldThrowProductValidationExceptionPriceIsTooLow() {
        product = product(UNSUCCESSFUL_PRICE_VALUE);

        assertThatThrownBy(() -> victim.validate(product))
                .isInstanceOf(ProductValidationException.class)
                .hasMessage("Discount cannot be assigned to product which costs less than 20$");
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
        product.setDiscount(TEST_PRODUCT_DISCOUNT);
        return product;
    }
}
