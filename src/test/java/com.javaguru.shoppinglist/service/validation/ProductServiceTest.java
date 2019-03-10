package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.database.ProductRepository;
import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.service.ProductService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository repository;

    @Mock
    private ProductValidationService validationService;

    @Captor
    private ArgumentCaptor<Product> productArgumentCaptor;

    @InjectMocks
    private ProductService victim;

    @Test
    public void shouldCreateProduct() {
        Product product = product();
        when(repository.insertProduct(product)).thenReturn(product);

        Long result = victim.addProduct(product);

        verify(validationService).validate(productArgumentCaptor.capture());
        Product captorResult = productArgumentCaptor.getValue();

        assertEquals(captorResult, product);
        assertEquals(product.getId(), result);
    }

    @Test
    public void shouldFindProductById() {
        when(repository.findProductById(3L)).thenReturn(Optional.ofNullable(product()));

        Product result = victim.findProductById(3L);

        assertEquals(result, product());
    }

    @Test
    public void shouldThrowExceptionProductNotFound() {
        when(repository.findProductById(any())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> victim.findProductById(3L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Product not found, id: 3");
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


