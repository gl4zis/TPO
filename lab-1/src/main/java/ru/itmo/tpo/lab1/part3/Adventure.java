package ru.itmo.tpo.lab1.part3;

import ru.itmo.tpo.lab1.part3.household.Book;
import ru.itmo.tpo.lab1.part3.household.Relation;
import ru.itmo.tpo.lab1.part3.household.dish.BulkBlaster;
import ru.itmo.tpo.lab1.part3.person.Person;
import ru.itmo.tpo.lab1.part3.person.action.*;
import ru.itmo.tpo.lab1.part3.person.action.Discuss;
import ru.itmo.tpo.lab1.part3.person.action.Flip;
import ru.itmo.tpo.lab1.part3.person.action.Read;
import ru.itmo.tpo.lab1.part3.ship.Location;
import ru.itmo.tpo.lab1.part3.ship.Starship;
import ru.itmo.tpo.lab1.part3.ship.StarshipPlace;
import ru.itmo.tpo.lab1.part3.stimulus.Intercom;
import ru.itmo.tpo.lab1.part3.util.Direction;
import ru.itmo.tpo.lab1.part3.util.Subject;

import java.util.List;
import java.util.Set;

public class Adventure {
    private static final List<String> GUIDEBOOK_SENTENCES = List.of(
            "История любой галактической цивилизации имеет тенденцию к развитию в три отчетливо выраженных и " +
                    "различимых этапа: выживание, познание и мудрость, известных также, как этапы Как, Почему и Где",
            "Например, первый этап характеризуется вопросом «Как нам чего-нибудь поесть?», второй — вопросом " +
                    "«Почему мы едим?», и третий — вопросом «Где бы нам пообедать?»"
    );

    public static void main(String[] args) {
        var zaford = new Person("Зафорд");
        var ford = new Person("Форд");
        var trillian = new Person("Триллиан");
        var arthur = new Person("Артур");

        var starship = Starship.builder("Золотое Сердце")
                .crewMember(zaford)
                .crewMember(ford)
                .crewMember(trillian)
                .crewMember(arthur)
                .inFlight(Location.HORSE_HEAD_NEBULA, Direction.FROM)
                .build();
        System.out.println(starship);
        starship.printState();

        zaford.addAction(new Settle(StarshipPlace.PALM_ON_BRIDGE, Relation.UNDER));
        zaford.addAction(new UtilizeDish(new BulkBlaster(zaford), true));
        ford.printActions();

        var life = new Subject("жизнь");
        var lifeConsequences = new Subject("последствия жизни");
        var sittingInEdge = new Sit(StarshipPlace.EDGE, Relation.IN);

        ford.addAction(sittingInEdge);
        ford.addAction(new Discuss(trillian, Set.of(life, lifeConsequences), false));
        ford.printActions();

        trillian.addAction(sittingInEdge);
        trillian.addAction(new Discuss(ford, Set.of(life, lifeConsequences), false));
        trillian.printActions();

        var guidebook = new Book("Путеводитель для автостопщиков", ford, GUIDEBOOK_SENTENCES);

        arthur.addAction(new Lie(StarshipPlace.ARTUR_CABIN, Relation.IN));
        arthur.addAction(new Flip(guidebook));
        arthur.printActions();

        arthur.addAction(new Think(Set.of(new Subject("пора начинать знакомиться с местными обычаями"))));
        arthur.addAction(new Read(guidebook, false));
        arthur.printActions();
        arthur.readAll();
        arthur.interrupt(new Intercom());

        zaford.momentAction(new Say("Эй, землянин, ты есть хочешь", arthur, Say.Type.ASK));
        arthur.momentAction(new Say("Да, я бы, пожалуй, перекусил", zaford, Say.Type.ANSWER));
        zaford.momentAction(new Say("Вот и славно, Потерпи немного, дружок, мы пообедаем в ресторане «Конец Вселенной»", arthur, Say.Type.AFFIRM));
    }
}
