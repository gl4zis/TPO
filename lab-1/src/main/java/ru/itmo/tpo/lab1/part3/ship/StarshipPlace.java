package ru.itmo.tpo.lab1.part3.ship;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum StarshipPlace {
    PALM_ON_BRIDGE("Пальма на мостике"),
    EDGE("Уголок"),
    ARTUR_CABIN("Каюта артура");

    private final String name;

    @Override
    public String toString() {
        return name;
    }
}
