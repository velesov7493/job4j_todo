package ru.job4j.todo.services;

import ru.job4j.todo.Security;
import ru.job4j.todo.persistence.User;
import ru.job4j.todo.store.HbmUserStore;
import ru.job4j.todo.store.rules.UserStore;

import java.util.List;

public class UserService {

    private static final UserService INSTANCE = new UserService();

    private final UserStore store;

    private UserService() {
        store = HbmUserStore.getInstance();
    }

    public static UserService getInstance() {
        return INSTANCE;
    }

    public List<User> findAll() {
        return store.findAll();
    }

    public User getById(int id) {
        return store.getById(id);
    }

    public User login(String login, String password) {
        return store.getByLoginAndPassword(login, Security.getSHA1(password));
    }

    public boolean save(User value) {
        if (value.getId() == 0) {
            value.setPassword(Security.getSHA1(value.getPassword()));
        }
        if (value.getRole() == null && store.save(value)) {
            RoleService rs = RoleService.getInstance();
            value.setRole(rs.getById(-1));
        }
        return store.save(value);
    }

    public boolean deleteById(int id) {
        return store.deleteById(id);
    }
}
