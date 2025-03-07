package ru.itmo.tpo.lab1.part3.person.action;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.itmo.tpo.lab1.part3.person.Person;

@Getter
@RequiredArgsConstructor
public class Say implements MomentAction {
    private final String text;
    private final Person to;
    private final Type type;

    @Override
    public String toString() {
        return String.format("%s: %s %s%s", type.name, text, to.getName(), type.endOfSentence);
    }

    @Getter
    @RequiredArgsConstructor
    public enum Type {
        ASK("спросил", "?"),
        ANSWER(" ответил","."),
        AFFIRM("сказал", "."),
        EXCLAIM("воскликнул","!");

        private final String name;
        private final String endOfSentence;
    }
}
