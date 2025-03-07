package ru.itmo.tpo.lab1.part3.ship.state;

import ru.itmo.tpo.lab1.part3.ship.Location;

public interface StarshipState {
    boolean isMoving();
    Location getLocation();
}
