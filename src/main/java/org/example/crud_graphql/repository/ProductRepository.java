package org.example.crud_graphql.repository;

import org.example.crud_graphql.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
