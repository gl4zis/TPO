package ru.itmo.tpo.lab1.part3.household.dish;

import lombok.Getter;
import ru.itmo.tpo.lab1.part3.household.Item;
import ru.itmo.tpo.lab1.part3.person.Person;

@Getter
public abstract class Dish extends Item {
    private final DishType type;

    public Dish(String name, Person owner, DishType type) {
        super(name, owner);
        this.type = type;
    }
}
