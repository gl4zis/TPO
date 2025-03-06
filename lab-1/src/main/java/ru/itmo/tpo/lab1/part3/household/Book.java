package ru.itmo.tpo.lab1.part3.household;

import ru.itmo.tpo.lab1.part3.person.Person;

import java.util.List;

public class Book extends Item {
    private final List<String> sentences;

    public Book(String name, Person owner, List<String> sentences) {
        super(name, owner);
        this.sentences = sentences;
    }

    public int sentencesCount() {
        return sentences.size();
    }

    public String readSentence(int index) {
        if (index < 0 || index >= sentences.size()) {
            throw new IllegalArgumentException("Invalid index of book sentence");
        }

        return sentences.get(index);
    }

    public String readAll() {
        return String.join(". ", sentences);
    }

    @Override
    public String toString() {
        return String.format("Книга %s", super.toString());
    }
}
