package ru.itmo.tpo.lab1.part3.person;

import ru.itmo.tpo.lab1.part3.person.action.Action;
import ru.itmo.tpo.lab1.part3.person.action.MomentAction;
import ru.itmo.tpo.lab1.part3.person.action.Read;
import ru.itmo.tpo.lab1.part3.stimulus.Stimulus;
import ru.itmo.tpo.lab1.part3.util.NamedEntity;

import java.util.ArrayList;
import java.util.List;

public class Person extends NamedEntity {
    private final List<Action> actions = new ArrayList<>();
    private Action activeAction;

    public Person(String name) {
        super(name);
    }

    public void addAction(Action action) {
        if (action instanceof MomentAction moment) {
            momentAction(moment);
        }

        if (action.isActive()) {
            if (activeAction != null) removeAction(activeAction);
            activeAction = action;
        } else {
            actions.add(action);
        }
    }

    public void momentAction(MomentAction action) {
        System.out.printf("%s ", getName());
        action.print();
    }

    public void removeAction(Action action) {
        if (action instanceof MomentAction) {
            throw new IllegalArgumentException("Moment action cannot be removed");
        }

        System.out.printf("%s перестал %s\n", getName(), activeAction);
        if (action.equals(activeAction)) {
            activeAction = null;
        } else {
            actions.remove(action);
        }
    }

    public void printActions() {
        System.out.printf("%s:\n", getName());
        actions.forEach(Action::print);
        if (activeAction != null) activeAction.print();
    }

    public void interrupt(Stimulus stimulus) {
        removeAction(activeAction);
        System.out.printf("%s был прерван %s\n", getName(), stimulus);
    }

    public void readAll() {
        if (activeAction != null && activeAction instanceof Read reading) {
            System.out.printf("%s прочитал:\n", getName());
            System.out.printf("\tИз '%s':\t%s\n", reading.getBook(), reading.getBook().readAll());
        } else {
            System.out.printf("%s не читает\n", getName());
        }
    }
}
