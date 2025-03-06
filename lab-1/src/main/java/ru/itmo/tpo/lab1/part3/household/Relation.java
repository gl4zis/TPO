package ru.itmo.tpo.lab1.part3.household;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Relation {
    ON("на"),
    IN("в"),
    UNDER("под"),
    NEAR("рядом");

    private final String name;

    @Override
    public String toString() {
        return name;
    }
}
