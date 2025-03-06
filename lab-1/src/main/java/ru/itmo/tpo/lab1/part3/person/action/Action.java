package ru.itmo.tpo.lab1.part3.person.action;

public interface Action {
    default boolean isActive() {
        return false;
    }

    default boolean isTry() {
        return false;
    }

    default void print() {
        System.out.printf("\t%s%s\n", isTry() ? "пытается " : "", this);
    }
}
