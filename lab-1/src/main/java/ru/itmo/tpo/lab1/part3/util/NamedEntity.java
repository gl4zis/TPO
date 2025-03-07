package ru.itmo.tpo.lab1.part3.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public abstract class NamedEntity {
    private final String name;

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NamedEntity named = (NamedEntity) o;
        return named.getName().equals(getName());
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
