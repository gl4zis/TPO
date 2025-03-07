package ru.itmo.tpo.lab1.part3.person.action;

public interface MomentAction extends Action {
    @Override
    default boolean isActive() {
        return true;
    }
}
