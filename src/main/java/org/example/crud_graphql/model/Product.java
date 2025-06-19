package org.example.crud_graphql.model;

public record Product(Integer id, String name, Double price, Integer amount, Boolean onsite, Category category) {
}
