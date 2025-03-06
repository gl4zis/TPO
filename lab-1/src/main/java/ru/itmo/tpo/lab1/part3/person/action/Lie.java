package ru.itmo.tpo.lab1.part3.person.action;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import ru.itmo.tpo.lab1.part3.household.Relation;
import ru.itmo.tpo.lab1.part3.ship.StarshipPlace;

@EqualsAndHashCode
@RequiredArgsConstructor
public class Lie implements Action {
    private final StarshipPlace into;
    private final Relation relation;

    @Override
    public String toString() {
        return String.format("лежит %s %s", relation, into);
    }
}
