package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.database.ProductRepository;
import com.javaguru.shoppinglist.domain.Product;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductUniqueNameValidationRuleTest {

    @Mock
    private ProductRepository repository;

    @Spy
    @InjectMocks
    private ProductUniqueNameValidationRule victim;

    private Product product = product();

    @Test
    public void shouldThrowException() {
        when(repository.existInRepositoryByName(product.getName()))
                .thenReturn(true);

        assertThatThrownBy(() -> victim.validate(product))
                .isInstanceOf(ProductValidationException.class)
                .hasMessage("Such name already exists in repository");

        verify(victim).checkNotNull(product);
    }

    @Test
    public void shouldValidateSuccess() {
        when(repository.existInRepositoryByName(product.getName()))
                .thenReturn(false);

        victim.validate(product);

        verify(victim).checkNotNull(product);
    }

    private Product product() {
        Product product = new Product();
        product.setName("TEST_NAME");
        product.setPrice(new BigDecimal(25));
        product.setDiscount(10D);
        product.setCategory("VEGETABLES");
        product.setDescription("TEST_DESCRIPTION");
        product.setId(3L);
        return product;
    }
}
