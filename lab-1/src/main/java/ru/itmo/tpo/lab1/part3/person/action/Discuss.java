package ru.itmo.tpo.lab1.part3.person.action;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.itmo.tpo.lab1.part3.person.Person;
import ru.itmo.tpo.lab1.part3.util.Subject;

import java.util.Set;
import java.util.stream.Collectors;

@EqualsAndHashCode
@RequiredArgsConstructor
public class Discuss implements Action {
    private final Person with;
    private final Set<Subject> what;
    @Getter
    private final boolean isTry;
    @Getter
    private final boolean isActive = true;

    @Override
    public String toString() {
        return String.format("обсуждает с %s %s", with, what.stream().map(Subject::getName).collect(Collectors.joining(", ")));
    }
}
