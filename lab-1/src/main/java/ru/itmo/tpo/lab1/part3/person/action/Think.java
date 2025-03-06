package ru.itmo.tpo.lab1.part3.person.action;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import ru.itmo.tpo.lab1.part3.util.Subject;

import java.util.Set;
import java.util.stream.Collectors;

@EqualsAndHashCode
@RequiredArgsConstructor
public class Think implements Action {
    private final Set<Subject> what;

    @Override
    public String toString() {
        return String.format("думает, что %s", what.stream().map(Subject::getName).collect(Collectors.joining(", ")));
    }
}
