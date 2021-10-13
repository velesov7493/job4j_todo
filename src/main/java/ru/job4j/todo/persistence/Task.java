package ru.job4j.todo.persistence;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tz_tasks")
public class Task {

    @Id
    @SequenceGenerator(name = "tasksIdSeq", sequenceName = "tz_tasks_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tasksIdSeq")
    private int id;
    private short done;
    private String description;
    @Column(insertable = false)
    private Date created;

    public Task() { }

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
}