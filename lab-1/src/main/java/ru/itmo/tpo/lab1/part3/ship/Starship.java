package ru.itmo.tpo.lab1.part3.ship;


import ru.itmo.tpo.lab1.part3.person.Person;
import ru.itmo.tpo.lab1.part3.person.Crew;
import ru.itmo.tpo.lab1.part3.ship.state.InFlight;
import ru.itmo.tpo.lab1.part3.ship.state.Parked;
import ru.itmo.tpo.lab1.part3.ship.state.StarshipState;
import ru.itmo.tpo.lab1.part3.util.Direction;
import ru.itmo.tpo.lab1.part3.util.NamedEntity;

public class Starship extends NamedEntity {
    private final Crew crew;
    private StarshipState state;

    private Starship(String name, Crew crew, StarshipState state) {
        super(name);
        this.crew = crew;
        this.state = state;
    }

    public void flight(Location location, Direction direction) {
        this.state = new InFlight(location, direction);
    }

    public void park(Location location) {
        if (!this.state.isMoving()) {
            throw new IllegalStateException("Starship isn't moving");
        }
        this.state = new Parked(location);
    }

    public void printState() {
        System.out.println(state);
    }

    public static Builder builder(String name) {
        return new Builder(name);
    }

    @Override
    public String toString() {
        return String.format("Звёздный корабль '%s'\nэкипаж: %s", getName(), crew);
    }

    public static class Builder {
        private final String name;
        private final Crew.Builder crewBuilder = Crew.builder();
        private StarshipState state;

        private Builder(String name) {
            this.name = name;
        }

        public Builder newCrewMember(String name) {
            crewBuilder.add(new Person(name));
            return this;
        }

        public Builder crewMember(Person person) {
            crewBuilder.add(person);
            return this;
        }

        public Builder inFlight(Location location, Direction direction) {
            state = new InFlight(location, direction);
            return this;
        }

        public Builder parked(Location location) {
            state = new Parked(location);
            return this;
        }

        public Starship build() {
            if (state == null) {
                throw new IllegalStateException("Starship doesn't have state");
            }
            return new Starship(name, crewBuilder.build(), state);
        }
    }
}
