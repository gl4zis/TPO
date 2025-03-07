package ru.itmo.tpo.lab1.part3.household.dish;

import lombok.Getter;
import ru.itmo.tpo.lab1.part3.household.Item;
import ru.itmo.tpo.lab1.part3.person.Person;

import java.util.Objects;

@Getter
public abstract class Dish extends Item {
    private final DishType type;

    public Dish(String name, Person owner, DishType type) {
        super(name, owner);
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dish dish = (Dish) o;
        return Objects.equals(dish.getName(), getName()) &&
                Objects.equals(dish.getType(), getType()) &&
                Objects.equals(dish.getOwner(), getOwner());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), type);
    }
}
