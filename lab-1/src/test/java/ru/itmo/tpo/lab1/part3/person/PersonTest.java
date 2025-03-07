package ru.itmo.tpo.lab1.part3.person;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.itmo.tpo.lab1.part3.household.Book;
import ru.itmo.tpo.lab1.part3.household.Relation;
import ru.itmo.tpo.lab1.part3.household.dish.BulkBlaster;
import ru.itmo.tpo.lab1.part3.person.action.*;
import ru.itmo.tpo.lab1.part3.ship.StarshipPlace;
import ru.itmo.tpo.lab1.part3.stimulus.Intercom;
import ru.itmo.tpo.lab1.part3.util.Subject;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Set;


public class PersonTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    private Person misha;
    private Person oneMoreMisha;
    private Person kolya;

    @BeforeEach
    void setUp() {
        misha = new Person("Misha");
        oneMoreMisha = new Person("Misha");
        kolya = new Person("Kolya");
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    public void nameTest() {
        Assertions.assertEquals("Misha", misha.getName());
        Assertions.assertEquals(oneMoreMisha, misha);
    }

    @Test
    public void actionTest() {
        var action = new Lie(StarshipPlace.EDGE, Relation.NEAR);
        misha.addAction(action);

        Assertions.assertEquals(1, misha.getActions().size());
        Assertions.assertTrue(misha.getActions().contains(new Lie(StarshipPlace.EDGE, Relation.NEAR)));
        Assertions.assertFalse(misha.doActiveAction());
        Assertions.assertNotEquals(oneMoreMisha, misha);

        oneMoreMisha.addAction(action);
        Assertions.assertEquals(oneMoreMisha, misha);

        misha.addAction(new UtilizeDish(new BulkBlaster(misha), true));
        Assertions.assertEquals(1, misha.getActions().size());
        Assertions.assertEquals(UtilizeDish.class, misha.getActiveAction().getClass());
        Assertions.assertEquals(BulkBlaster.class, ((UtilizeDish) misha.getActiveAction()).getDish().getClass());
        Assertions.assertTrue(misha.getActiveAction().isTry());
        Assertions.assertThrows(IllegalArgumentException.class, () -> misha.addAction(null));

        misha.addAction(new Discuss(kolya, Set.of(new Subject("something")), false));
        Assertions.assertEquals(1, misha.getActions().size());
        Assertions.assertEquals(Discuss.class, misha.getActiveAction().getClass());
        Assertions.assertEquals(kolya, ((Discuss) misha.getActiveAction()).getWith());
        Assertions.assertEquals(Set.of(new Subject("something")), ((Discuss) misha.getActiveAction()).getWhat());
    }

    @Test
    public void momentActionTest() {
        var momentAction = new Say("something", kolya, Say.Type.AFFIRM);
        Assertions.assertTrue(momentAction.isActive());

        misha.momentAction(momentAction);
        Assertions.assertTrue(outputContains("something"));
        Assertions.assertTrue(outputContains("Kolya"));
        Assertions.assertTrue(outputContains(Say.Type.AFFIRM.getEndOfSentence()));
        Assertions.assertTrue(outputContains(Say.Type.AFFIRM.getName()));

        Assertions.assertThrows(IllegalArgumentException.class, () -> misha.momentAction(null));
    }

    @Test
    public void endActionTest() {
        var action = new Lie(StarshipPlace.ARTUR_CABIN, Relation.IN);
        var activeAction = new Read(new Book("name", kolya, List.of()), false);
        var momentAction = new Say("text", kolya, Say.Type.ASK);
        Assertions.assertThrows(IllegalArgumentException.class, () -> misha.endAction(null));
        Assertions.assertThrows(IllegalArgumentException.class, () -> misha.endAction(action));
        Assertions.assertThrows(IllegalArgumentException.class, () -> misha.endAction(action));

        misha.addAction(action);
        Assertions.assertEquals(1, misha.getActions().size());
        misha.endAction(action);
        Assertions.assertEquals(0, misha.getActions().size());

        misha.addAction(momentAction);
        Assertions.assertThrows(IllegalArgumentException.class, () -> misha.endAction(momentAction));

        misha.addAction(activeAction);
        Assertions.assertTrue(misha.doActiveAction());
        misha.endAction(activeAction);
        Assertions.assertFalse(misha.doActiveAction());
    }

    @Test
    public void interruptTest() {
        var activeAction = new Read(new Book("name", kolya, List.of()), false);
        var interrupter = new Intercom();

        Assertions.assertThrows(IllegalArgumentException.class, () -> misha.interrupt(null));
        misha.interrupt(interrupter);
        Assertions.assertTrue(outputContains(misha.getName()));
        Assertions.assertTrue(outputContains("был прерван"));
        Assertions.assertTrue(outputContains(interrupter.getName()));
        Assertions.assertFalse(outputContains(activeAction.prettyString()));

        resetOutput();

        misha.addAction(activeAction);
        misha.interrupt(interrupter);
        Assertions.assertTrue(outputContains(activeAction.prettyString().trim()));
    }

    @Test
    public void readTest() {
        var bookName = "bookName";
        var bookSentence1 = "bookSentence1";
        var bookSentence2 = "bookSentence2";
        var book = new Book(bookName, kolya, List.of(bookSentence1, bookSentence2));
        var reading = new Read(book, false);

        Assertions.assertTrue(misha.readAll().contains("не читает"));
        misha.addAction(reading);
        Assertions.assertTrue(misha.readAll().contains("прочитал"));
        Assertions.assertTrue(misha.readAll().contains(bookName));
        Assertions.assertTrue(misha.readAll().contains(bookSentence1));
        Assertions.assertTrue(misha.readAll().contains(bookSentence2));
    }

    private boolean outputContains(String substring) {
        return outContent.toString().contains(substring);
    }

    private void resetOutput() {
        outContent.reset();
    }
}
