package ru.job4j.todo.persistence;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tz_categories")
public class Category {

    @Id
    @SequenceGenerator(
            name = "categoriesIdSeq",
            sequenceName = "tz_categories_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "categoriesIdSeq")
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Category category = (Category) o;
        return id == category.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
