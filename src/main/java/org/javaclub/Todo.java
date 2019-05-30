package org.javaclub;

import java.util.StringJoiner;
import javax.persistence.Entity;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
public class Todo extends PanacheEntity {
    public String title;
    public boolean isDone;

    @Override
    public String toString() {
        return new StringJoiner(", ", Todo.class.getSimpleName() + "[", "]")
                .add("title='" + title + "'")
                .add("isDone=" + isDone)
                .add("id=" + id)
                .toString();
    }
}
