package ru.itmo.tpo.lab1.part3.household.dish;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DishAction {
    EAT("ест"),
    DRINK("пьёт");

    private final String name;
}
