package ru.job4j.todo.persistence;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tz_users")
public class User {

    @Id
    @SequenceGenerator(name = "usersIdSeq", sequenceName = "tz_users_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usersIdSeq")
    private int id;
    @ManyToOne
    @JoinColumn(name = "id_role")
    private Role role;
    @Column(unique = true)
    private String login;
    private String name;
    private String password;

    public User() { }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return
                id == user.id
                && Objects.equals(role, user.role)
                && Objects.equals(login, user.login)
                && Objects.equals(name, user.name)
                && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, role, login, name, password);
    }
}
