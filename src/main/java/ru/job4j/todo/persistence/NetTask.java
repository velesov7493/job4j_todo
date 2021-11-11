package ru.job4j.todo.persistence;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class NetTask {

    private int id;
    private short done;
    private String description;
    private Date created;
    private Set<Integer> categoryIds;

    public NetTask() {
        categoryIds = new HashSet<>();
        created = new Date();
    }

    public Task copyTask() {
        Task t = new Task();
        t.setId(id);
        t.setDone(done);
        t.setDescription(description);
        t.setCreated(created);
        return t;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public short getDone() {
        return done;
    }

    public void setDone(short done) {
        this.done = done;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Set<Integer> getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(Set<Integer> categoryIds) {
        this.categoryIds = categoryIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        NetTask task = (NetTask) o;
        return
                id == task.id
                && done == task.done
                && Objects.equals(description, task.description)
                && Objects.equals(created, task.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, done, description, created);
    }
}