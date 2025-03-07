package ru.itmo.tpo.lab1.part3.starship;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.itmo.tpo.lab1.part3.person.Crew;
import ru.itmo.tpo.lab1.part3.person.Person;
import ru.itmo.tpo.lab1.part3.ship.Location;
import ru.itmo.tpo.lab1.part3.ship.Starship;
import ru.itmo.tpo.lab1.part3.ship.state.InFlight;
import ru.itmo.tpo.lab1.part3.util.Direction;


public class StarshipTest {

    @Test
    public void starshipBaseTest() {
        var person = new Person("person");
        var starship = Starship.builder("starship")
                .crewMember(person)
                .newCrewMember("newPerson")
                .parked(Location.PLANET_EARTH)
                .build();

        Assertions.assertEquals("starship", starship.getName());
        Assertions.assertEquals(Location.PLANET_EARTH, starship.getState().getLocation());
        Assertions.assertFalse(starship.getState().isMoving());
        Assertions.assertEquals(
                Crew.builder()
                        .add(person)
                        .add(new Person("newPerson"))
                        .build(),
                starship.getCrew()
        );
    }

    @Test
    public void starshipBuilderError() {
        Assertions.assertThrows(IllegalStateException.class, () -> Starship.builder("starship").build());
    }

    @Test
    public void starshipChangeStateTest() {
        var person = new Person("person");
        var starship = Starship.builder("starship")
                .crewMember(person)
                .parked(Location.PLANET_EARTH)
                .build();

        Assertions.assertThrows(IllegalStateException.class, () -> starship.park(Location.HORSE_HEAD_NEBULA));

        starship.flight(Location.HORSE_HEAD_NEBULA, Direction.TO);

        Assertions.assertTrue(starship.getState().isMoving());
        Assertions.assertEquals(Location.HORSE_HEAD_NEBULA, starship.getState().getLocation());
        Assertions.assertEquals(Direction.TO, ((InFlight) starship.getState()).getDirection());

        var starship2 = Starship.builder("starship")
                .inFlight(Location.HORSE_HEAD_NEBULA, Direction.FROM)
                .build();

        Assertions.assertTrue(starship2.getState().isMoving());
        Assertions.assertEquals(Location.HORSE_HEAD_NEBULA, starship2.getState().getLocation());
        Assertions.assertEquals(Direction.FROM, ((InFlight) starship2.getState()).getDirection());

        starship2.park(Location.PLANET_EARTH);
        Assertions.assertFalse(starship2.getState().isMoving());
        Assertions.assertEquals(Location.PLANET_EARTH, starship2.getState().getLocation());
    }
}
