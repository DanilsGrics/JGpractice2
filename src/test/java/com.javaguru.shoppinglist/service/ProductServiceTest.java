package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.database.InMemoryProductRepository;
import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.service.validation.ProductValidationService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    private final Long TEST_PRODUCT_ID = 3L;

    @Mock
    private InMemoryProductRepository repository;

    @Mock
    private ProductValidationService validationService;

    @Captor
    private ArgumentCaptor<Product> productArgumentCaptor;

    @InjectMocks
    private DefaultProductService victim;

    @Test
    public void shouldCreateProduct() {
        Product product = product();
        when(repository.insertProduct(product)).thenReturn(product.getId());

        Long result = victim.addProduct(product);

        verify(validationService).validate(productArgumentCaptor.capture());
        Product captorResult = productArgumentCaptor.getValue();

        assertEquals(captorResult, product);
        assertEquals(product.getId(), result);
    }

    @Test
    public void shouldFindProductById() {
        when(repository
                .findProductById(TEST_PRODUCT_ID))
                .thenReturn(Optional.ofNullable(product()));

        Product result = victim.findProductById(TEST_PRODUCT_ID);

        assertEquals(result, product());
    }

    @Test
    public void shouldThrowExceptionProductNotFound() {
        when(repository.findProductById(any())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> victim.findProductById(TEST_PRODUCT_ID))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Product not found, id: 3");
    }

    private Product product() {
        Product product = new Product();

        product.setId(TEST_PRODUCT_ID);
        return product;
    }
}


