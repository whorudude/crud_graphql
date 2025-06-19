package org.example.crud_graphql.service;

import jakarta.annotation.PostConstruct;
import org.example.crud_graphql.model.Category;
import org.example.crud_graphql.model.Product;
import org.example.crud_graphql.model.input.CreateProductInput;
import org.example.crud_graphql.model.input.UpdateProductInput;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final List<Product> products = new ArrayList<>();
    private Integer id = 0;

    public List<Product> findAll() {
        return products;
    }

    public Optional<Product> findById(Integer id) {
        return products.stream()
                .filter(product -> product.id() == id)
                .findFirst();
    }

    public Product create(CreateProductInput input) {
        Product product = new Product(id++, input.name(), input.price(), input.amount(), input.onsite(), input.category());
        products.add(product);
        return product;
    }

    public Product update(UpdateProductInput input) {
        Optional<Product> optional = findById(id);
        if (optional.isPresent()) {
            Product updatedProduct = new Product(id, input.name(), input.price(), input.amount(), input.onsite(), input.category());
            int index = products.indexOf(optional.get());
            products.set(index, updatedProduct);
            return updatedProduct;
        } else {
            throw new IllegalArgumentException("Invalid product ID: " + id);
        }
    }

    public Product deleteById(Integer id) {
        Product product = findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with ID: " + id));
        products.remove(product);
        return product;
    }

    @PostConstruct
    private void init() {
        products.add(new Product(id++, "Wireless Mouse", 25.99, 100, true, Category.ELECTRONICS));
        products.add(new Product(id++, "Organic Green Tea", 9.50, 50, false, Category.FOOD));
        products.add(new Product(id++, "Yoga Mat", 19.99, 75, true, Category.TOYS));
    }
}
