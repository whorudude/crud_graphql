package org.example.crud_graphql.controller;

import org.example.crud_graphql.model.entity.Product;
import org.example.crud_graphql.model.input.CreateProductInput;
import org.example.crud_graphql.model.input.ProductFilter;
import org.example.crud_graphql.model.input.UpdateProductInput;
import org.example.crud_graphql.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @QueryMapping
    public List<Product> products() {
        return productService.findAll();
    }

    @QueryMapping
    public Optional<Product> product(@Argument Integer id) {
        return productService.findById(id);
    }

    @QueryMapping
    public List<Product> filterProducts(@Argument ProductFilter filter) {
        return productService.productFilter(filter);
    }

    @MutationMapping
    public Product createProduct(@Argument CreateProductInput input) {
        return productService.create(input);
    }

    @MutationMapping
    public Product deleteProduct(@Argument Integer id) {
        return productService.deleteById(id);
    }

    @MutationMapping
    public Product updateProduct(@Argument UpdateProductInput input) {
        return productService.update(input);
    }
}

