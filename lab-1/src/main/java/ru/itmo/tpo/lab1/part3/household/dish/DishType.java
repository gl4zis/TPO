package ru.itmo.tpo.lab1.part3.household.dish;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DishType {
    FOOD(DishAction.EAT),
    DRINK(DishAction.DRINK);

    private final DishAction action;
}
