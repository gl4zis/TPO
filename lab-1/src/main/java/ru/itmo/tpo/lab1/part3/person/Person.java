package ru.itmo.tpo.lab1.part3.person;

import lombok.Getter;
import ru.itmo.tpo.lab1.part3.person.action.Action;
import ru.itmo.tpo.lab1.part3.person.action.MomentAction;
import ru.itmo.tpo.lab1.part3.person.action.Read;
import ru.itmo.tpo.lab1.part3.stimulus.Stimulus;
import ru.itmo.tpo.lab1.part3.util.NamedEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Getter
public class Person extends NamedEntity {
    private final List<Action> actions = new ArrayList<>();
    private Action activeAction;

    public Person(String name) {
        super(name);
    }

    public boolean doActiveAction() {
        return activeAction != null;
    }

    public void addAction(Action action) {
        if (action == null) {
            throw new IllegalArgumentException("Action is null");
        }
        if (action instanceof MomentAction moment) {
            momentAction(moment);
            return;
        }

        if (action.isActive()) {
            if (activeAction != null) {
                endAction(activeAction);
            }
            activeAction = action;
        } else {
            actions.add(action);
        }
    }

    public void momentAction(MomentAction action) {
        if (action == null) {
            throw new IllegalArgumentException("Action is null");
        }
        System.out.printf("%s %s", getName(), action.prettyString());
    }

    public void endAction(Action action) {
        if (action == null) {
            throw new IllegalArgumentException("Action is null");
        }
        if (action instanceof MomentAction) {
            throw new IllegalArgumentException("Moment action cannot be removed");
        }
        if (!actions.contains(action) && !action.equals(activeAction)) {
            throw new IllegalArgumentException("Person don't do this action cannot be removed");
        }

        System.out.printf("%s перестал %s\n", getName(), action);
        if (action.equals(activeAction)) {
            activeAction = null;
        } else {
            actions.remove(action);
        }
    }

    public String getActionsAsString() {
        var builder = new StringBuilder(String.format("%s:\n", getName()));
        actions.forEach(a -> builder.append(a.prettyString()));
        if (activeAction != null) builder.append(activeAction.prettyString());
        return builder.toString();
    }

    public void interrupt(Stimulus stimulus) {
        if (stimulus == null) {
            throw new IllegalArgumentException("Stimulus is null");
        }
        System.out.printf("%s был прерван %s\n", getName(), stimulus);

        if (activeAction != null) {
            endAction(activeAction);
        }
    }

    public String readAll() {
        if (activeAction != null && activeAction instanceof Read reading) {
            return String.format("%s прочитал:\n\tИз '%s':\t%s\n",
                    getName(), reading.getBook(), reading.getBook().readAll());
        } else {
            return String.format("%s не читает\n", getName());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(getName(), person.getName()) &&
                Objects.equals(activeAction, person.activeAction) &&
                Objects.equals(actions, person.actions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                getName(),
                Arrays.hashCode(actions.toArray()),
                activeAction != null ? activeAction.hashCode() : 0
        );
    }
}
