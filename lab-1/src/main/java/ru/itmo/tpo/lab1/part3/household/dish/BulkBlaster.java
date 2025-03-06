package ru.itmo.tpo.lab1.part3.household.dish;

import ru.itmo.tpo.lab1.part3.person.Person;

public class BulkBlaster extends Dish {
    public BulkBlaster(Person owner) {
        super("пангалактический бульк-бластер", owner, DishType.DRINK);
    }
}
