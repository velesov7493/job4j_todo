package ru.job4j.todo.persistence;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tz_roles")
public class Role {

    @Id
    @SequenceGenerator(name = "rolesIdSeq", sequenceName = "tz_roles_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rolesIdSeq")
    private int id;
    private String name;

    public Role() { }

    public static Role of(String aName) {
        Role r = new Role();
        r.setName(aName);
        return r;
    }

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
        Role role = (Role) o;
        return
                id == role.id
                && Objects.equals(name, role.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
