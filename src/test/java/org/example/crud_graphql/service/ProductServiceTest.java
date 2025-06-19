package org.example.crud_graphql.service;

import org.example.crud_graphql.model.Category;
import org.example.crud_graphql.model.entity.Product;
import org.example.crud_graphql.model.input.CreateProductInput;
import org.example.crud_graphql.model.input.ProductFilter;
import org.example.crud_graphql.model.input.UpdateProductInput;
import org.example.crud_graphql.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository repository;

    @InjectMocks
    private ProductService service;

    @Test
    void testFindAll() {
        List<Product> products = List.of(new Product());
        when(repository.findAll()).thenReturn(products);

        List<Product> result = service.findAll();
        assertEquals(products, result);
    }

    @Test
    void testFindByIdFound() {
        Product product = new Product();
        when(repository.findById(1)).thenReturn(Optional.of(product));

        Optional<Product> result = service.findById(1);
        assertTrue(result.isPresent());
        assertEquals(product, result.get());
    }

    @Test
    void testCreate() {
        CreateProductInput input = new CreateProductInput("Phone", 100.0, 5, true, Category.ELECTRONICS);
        Product toSave = new Product();
        toSave.setName("Phone");
        toSave.setPrice(100.0);
        toSave.setAmount(5);
        toSave.setOnsite(true);
        toSave.setCategory(Category.ELECTRONICS);

        Product saved = new Product();
        saved.setId(1);
        when(repository.save(any(Product.class))).thenReturn(saved);

        Product result = service.create(input);
        assertEquals(1, result.getId());
    }

    @Test
    void testUpdate() {
        Product existing = new Product(1, "Old", 50.0, 2, false, Category.BOOKS);
        when(repository.findById(1)).thenReturn(Optional.of(existing));

        UpdateProductInput input = new UpdateProductInput(1, "New", 60.0, 3, true, Category.TOYS);

        when(repository.save(any(Product.class))).thenReturn(existing);

        Product result = service.update(input);

        assertEquals("New", result.getName());
        assertEquals(60.0, result.getPrice());
        assertEquals(3, result.getAmount());
        assertEquals(true, result.getOnsite());
        assertEquals(Category.TOYS, result.getCategory());
    }

    @Test
    void testUpdateNotFound() {
        when(repository.findById(99)).thenReturn(Optional.empty());
        UpdateProductInput input = new UpdateProductInput(99, null, null, null, null, null);

        assertThrows(IllegalArgumentException.class, () -> service.update(input));
    }

    @Test
    void testDeleteById() {
        Product product = new Product();
        when(repository.findById(1)).thenReturn(Optional.of(product));

        Product deleted = service.deleteById(1);
        verify(repository).delete(product);
        assertEquals(product, deleted);
    }

    @Test
    void testDeleteByIdNotFound() {
        when(repository.findById(123)).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> service.deleteById(123));
    }

    @Test
    void testProductFilterByCategoryAndOnsite() {
        Product p1 = new Product(1, "Book", 10.0, 1, true, Category.BOOKS);
        Product p2 = new Product(2, "Toy", 20.0, 2, false, Category.TOYS);
        Product p3 = new Product(3, "Laptop", 999.0, 1, true, Category.ELECTRONICS);

        when(repository.findAll()).thenReturn(List.of(p1, p2, p3));

        ProductFilter filter = new ProductFilter(Category.ELECTRONICS, true);
        List<Product> result = service.productFilter(filter);

        assertEquals(1, result.size());
        assertEquals(p3, result.get(0));
    }
}