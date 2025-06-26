package org.example.crud_graphql.model.input;

import org.example.crud_graphql.model.Category;

public record ProductFilter(Category category, Boolean onsite) {}

