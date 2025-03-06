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
}
