package ru.itmo.tpo.lab1.part3.person;

import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class Crew {
    private final Set<Person> people;

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public String toString() {
        return people.stream().map(Person::getName).collect(Collectors.joining(", "));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Crew crew = (Crew) o;
        return Objects.equals(people, crew.people);
    }

    @Override
    public int hashCode() {
        return Objects.hash(people.toArray());
    }

    public static class Builder {
        private final Set<Person> people;

        private Builder() {
            people = new HashSet<>();
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
