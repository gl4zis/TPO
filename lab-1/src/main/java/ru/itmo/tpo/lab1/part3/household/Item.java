package ru.itmo.tpo.lab1.part3.household;

import ru.itmo.tpo.lab1.part3.person.Person;
import ru.itmo.tpo.lab1.part3.util.NamedEntity;

public abstract class Item extends NamedEntity {
    private final Person owner;

    public Item(String name, Person owner) {
        super(name);
        this.owner = owner;
    }

    @Override
    public String toString() {
        return String.format("%s, владелец - %s", getName(), owner);
    }
}
