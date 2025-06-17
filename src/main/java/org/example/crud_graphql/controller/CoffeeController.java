package org.example.crud_graphql.controller;

import org.example.crud_graphql.model.Coffee;
import org.example.crud_graphql.model.Size;
import org.example.crud_graphql.service.CoffeeService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class CoffeeController {
    private final CoffeeService coffeeService;
    public CoffeeController(CoffeeService coffeeService) {
        this.coffeeService = coffeeService;
    }

    @QueryMapping
    public List<Coffee> findAll() {
        return coffeeService.findAll();
    }

    @QueryMapping
    public Optional<Coffee> findOne(@Argument Integer id) {
        return coffeeService.findByOne(id);
    }

    @MutationMapping
    public Coffee createCoffee(@Argument String name, @Argument Size size) {
        return coffeeService.create(name, size);
    }

    @MutationMapping
    public Coffee deleteOne(@Argument Integer id) {
        return coffeeService.delete(id);
    }

    @MutationMapping
    public Coffee updateCoffee(@Argument Integer id, @Argument String name, @Argument Size size) {
        return coffeeService.update(id, name, size);
    }
}
