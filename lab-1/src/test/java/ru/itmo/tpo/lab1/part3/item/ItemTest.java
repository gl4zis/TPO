package ru.itmo.tpo.lab1.part3.item;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.itmo.tpo.lab1.part3.household.Book;
import ru.itmo.tpo.lab1.part3.person.Person;

import java.util.List;

public class ItemTest {

    @Test
    public void bookTest() {
        var bookName = "book";
        var sentence = "sentence";
        var owner = new Person("BookOwner");
        var book = new Book(bookName, owner, List.of(sentence, sentence, sentence));

        Assertions.assertEquals(owner, book.getOwner());
        Assertions.assertEquals(3, book.sentencesCount());
        Assertions.assertThrows(IllegalArgumentException.class, () -> book.readSentence(-1));
        Assertions.assertThrows(IllegalArgumentException.class, () -> book.readSentence(7));

        Assertions.assertEquals(sentence, book.readSentence(1));
        Assertions.assertEquals(String.format("'%s. %s. %s.'", sentence, sentence, sentence), book.readAll());
    }
}
