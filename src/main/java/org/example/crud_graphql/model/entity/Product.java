package org.example.crud_graphql.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.crud_graphql.model.Category;

@Setter
@Getter
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private Double price;
    private Integer amount;
    private Boolean onsite;

    @Enumerated(EnumType.STRING)
    private Category category;

    public Product() {
    }

    public Product(Integer id, String name, Double price, Integer amount, Boolean onsite, Category category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.amount = amount;
        this.onsite = onsite;
        this.category = category;
    }
}
