package ru.itmo.tpo.lab1.part3.person.action;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.itmo.tpo.lab1.part3.household.Book;

@EqualsAndHashCode
@RequiredArgsConstructor
public class Flip implements Action {
    private final Book book;
    @Getter
    private final boolean isActive = true;

    @Override
    public String toString() {
        return String.format("листает %s", book);
    }
}
