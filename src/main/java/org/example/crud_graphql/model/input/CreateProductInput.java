package org.example.crud_graphql.model.input;

import org.example.crud_graphql.model.Category;

public record CreateProductInput(String name, Double price, Integer amount, Boolean onsite, Category category) {
}
