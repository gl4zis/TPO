package ru.itmo.tpo.lab1.part3.person;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Crew {
    private final List<Person> people;

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public String toString() {
        return people.stream().map(Person::getName).collect(Collectors.joining(", "));
    }

    public static class Builder {
        private final List<Person> people;

        private Builder() {
            people = new ArrayList<>();
        }

        public Builder add(Person person) {
            people.add(person);
            return this;
        }

        public Crew build() {
            return new Crew(people);
        }
    }
}
