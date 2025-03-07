package ru.itmo.tpo.lab1.part3.person.action;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.itmo.tpo.lab1.part3.household.dish.Dish;

@Getter
@EqualsAndHashCode
@RequiredArgsConstructor
public class UtilizeDish implements Action {
    private final Dish dish;
    private final boolean isTry;
    private final boolean isActive = true;

    @Override
    public String toString() {
        return String.format("%s %s", dish.getType().getAction().getName(), dish);
    }
}
