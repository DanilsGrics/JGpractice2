package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.database.InMemoryProductRepository;
import com.javaguru.shoppinglist.domain.Product;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductUniqueNameValidationRuleTest {

    private final String TEST_PRODUCT_NAME = "TEST_NAME";

    @Mock
    private InMemoryProductRepository repository;

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

        product.setName(TEST_PRODUCT_NAME);
        return product;
    }
}
