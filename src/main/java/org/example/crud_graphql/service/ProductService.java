package org.example.crud_graphql.service;

import org.example.crud_graphql.model.entity.Product;
import org.example.crud_graphql.model.input.CreateProductInput;
import org.example.crud_graphql.model.input.ProductFilter;
import org.example.crud_graphql.model.input.UpdateProductInput;
import org.example.crud_graphql.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository repository;

    @Autowired
    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<Product> findAll() {
        return repository.findAll();
    }

    public Optional<Product> findById(Integer id) {
        return repository.findById(id);
    }

    public Product create(CreateProductInput input) {
        Product product = new Product();
        product.setName(input.name());
        product.setPrice(input.price());
        product.setAmount(input.amount());
        product.setOnsite(input.onsite());
        product.setCategory(input.category());
        return repository.save(product);
    }

    public Product update(UpdateProductInput input) {
        Product product = repository.findById(input.id())
                .orElseThrow(() -> new IllegalArgumentException("Invalid product ID: " + input.id()));

        if (input.name() != null) {
            product.setName(input.name());
        }
        if (input.price() != null) {
            product.setPrice(input.price());
        }
        if (input.amount() != null) {
            product.setAmount(input.amount());
        }
        if (input.onsite() != null) {
            product.setOnsite(input.onsite());
        }
        if (input.category() != null) {
            product.setCategory(input.category());
        }

        return repository.save(product);
    }

    public Product deleteById(Integer id) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with ID: " + id));

        repository.delete(product);
        return product;
    }

    public List<Product> productFilter(ProductFilter filter) {
        return repository.findAll().stream()
                .filter(p -> filter.category() == null || p.getCategory() == filter.category())
                .filter(p -> filter.onsite() == null || p.getOnsite().equals(filter.onsite()))
                .toList();
    }
}

