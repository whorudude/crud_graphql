package org.example.crud_graphql.service;

import jakarta.annotation.PostConstruct;
import org.example.crud_graphql.model.Coffee;
import org.example.crud_graphql.model.Size;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class CoffeeService {

    private final List<Coffee> coffees = new ArrayList<>();
    private final AtomicInteger id = new AtomicInteger(0);

    public List<Coffee> findAll() {
        return coffees;
    }

    public Optional<Coffee> findByOne(Integer id) {
        return coffees.stream()
                .filter(coffee -> coffee.id() == id)
                .findFirst();
    }

    public Coffee create(String name, Size size) {
        Coffee coffee = new Coffee(id.incrementAndGet(), name, size);
        coffees.add(coffee);
        return coffee;
    }

    public Coffee update(Integer id, String name, Size size) {
        Optional<Coffee> optional = findByOne(id);
        if (optional.isPresent()) {
            Coffee updatedCoffee = new Coffee(id, name, size);
            int index = coffees.indexOf(optional.get());
            coffees.set(index, updatedCoffee);
            return updatedCoffee;
        } else {
            throw new IllegalArgumentException("Invalid coffee ID: " + id);
        }
    }

    public Coffee delete(Integer id) {
        Coffee coffee = findByOne(id)
                .orElseThrow(() -> new IllegalArgumentException("Coffee not found with ID: " + id));
        coffees.remove(coffee);
        return coffee;
    }

    @PostConstruct
    private void init() {
        coffees.add(new Coffee(id.incrementAndGet(), "Caffè Americano", Size.GRANDE));
        coffees.add(new Coffee(id.incrementAndGet(), "Caffè Latte", Size.VENTI));
        coffees.add(new Coffee(id.incrementAndGet(), "Caramel Macchiato", Size.TALL));
    }
}
