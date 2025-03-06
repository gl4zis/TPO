package ru.itmo.tpo.lab1.part3.person.action;

public interface MomentAction extends Action {
    @Override
    default boolean isActive() {
        return true;
    }

    default void print() {
        System.out.printf("\t%s%s\n", isTry() ? "попытался " : "", this);
    }
}
