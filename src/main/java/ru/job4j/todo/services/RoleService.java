package ru.job4j.todo.services;

import ru.job4j.todo.persistence.Role;
import ru.job4j.todo.store.HbmRoleStore;
import ru.job4j.todo.store.rules.Store;

import java.util.List;

public class RoleService {

    private static final RoleService INSTANCE = new RoleService();

    private final Store<Integer, Role> store;

    private RoleService() {
        store = HbmRoleStore.getInstance();
    }

    public static RoleService getInstance() {
        return INSTANCE;
    }

    public List<Role> findAll() {
        return store.findAll();
    }

    public Role getById(int id) {
        return store.getById(id);
    }

    public boolean save(Role value) {
        return store.save(value);
    }

    public boolean deleteById(int id) {
        return store.deleteById(id);
    }
}
