package ru.itmo.tpo.lab1.part3.ship.state;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.itmo.tpo.lab1.part3.ship.Location;

@Getter
@EqualsAndHashCode
@RequiredArgsConstructor
public class Parked implements StarshipState {
    private final Location location;

    @Override
    public boolean isMoving() {
        return false;
    }

    @Override
    public String toString() {
        return String.format("на стоянке в %s", location);
    }
}
