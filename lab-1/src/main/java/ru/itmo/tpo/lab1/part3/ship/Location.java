package ru.itmo.tpo.lab1.part3.ship;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Location {
    HORSE_HEAD_NEBULA("Туманность Конской Головы"),
    PLANET_EARTH("Планета Земля");

    private final String name;

    @Override
    public String toString() {
        return name;
    }
}
