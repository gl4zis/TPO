package ru.itmo.tpo.lab1.part3.household;

import lombok.Getter;
import ru.itmo.tpo.lab1.part3.person.Person;
import ru.itmo.tpo.lab1.part3.util.NamedEntity;

import java.util.Objects;

@Getter
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(owner, item.owner) &&
                Objects.equals(getName(), item.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), owner);
    }
}
