package ru.itmo.tpo.lab1.part3.person.action;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.itmo.tpo.lab1.part3.household.Book;

@Getter
@EqualsAndHashCode
@RequiredArgsConstructor
public class Read implements Action {
    private final Book book;
    private final boolean isTry;
    @Getter
    private final boolean isActive = true;

    @Override
    public String toString() {
        return String.format("читает %s", book);
    }
}
