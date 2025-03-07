package ru.itmo.tpo.lab1.part3.ship.state;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.itmo.tpo.lab1.part3.ship.Location;
import ru.itmo.tpo.lab1.part3.util.Direction;

@Getter
@EqualsAndHashCode
@RequiredArgsConstructor
public class InFlight implements StarshipState {
    private final Location location;
    private final Direction direction;

    @Override
    public boolean isMoving() {
        return true;
    }

    @Override
    public String toString() {
        return String.format("летит %s %s", direction, location);
    }
}
