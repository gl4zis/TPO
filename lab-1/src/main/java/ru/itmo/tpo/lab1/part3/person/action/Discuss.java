package ru.itmo.tpo.lab1.part3.person.action;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.itmo.tpo.lab1.part3.person.Person;
import ru.itmo.tpo.lab1.part3.util.Subject;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public class Discuss implements Action {
    private final Person with;
    private final Set<Subject> what;
    private final boolean isTry;
    private final boolean isActive = true;

    @Override
    public String toString() {
        return String.format("обсуждает с %s %s", with, what.stream().map(Subject::getName).collect(Collectors.joining(", ")));
    }
}
