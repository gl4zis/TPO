package ru.itmo.tpo.lab1.part3.util;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Direction {
    FROM("от"),
    TO("к");

    private final String name;

    @Override
    public String toString() {
        return name;
    }
}
