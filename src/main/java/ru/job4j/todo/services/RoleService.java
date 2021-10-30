package ru.job4j.todo.services;

import com.google.gson.Gson;
import ru.job4j.todo.AppUtils;
import ru.job4j.todo.persistence.Role;
import ru.job4j.todo.store.HbmRoleStore;
import ru.job4j.todo.store.rules.Store;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class RoleService {

    private static final RoleService INSTANCE = new RoleService();

    private final Store<Integer, Role> store;
    private final Gson gson;

    private RoleService() {
        gson = AppUtils.getGson();
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

    public void jsonWriteToStream(List<Role> records, OutputStream out) throws IOException {
        String json = gson.toJson(records);
        out.write(json.getBytes(StandardCharsets.UTF_8));
        out.flush();
        out.close();
    }

    public void jsonWriteToStreamSingleRole(Role role, OutputStream out) throws IOException {
        String json = gson.toJson(role);
        out.write(json.getBytes(StandardCharsets.UTF_8));
        out.flush();
        out.close();
    }

    public Role jsonReadFromStream(InputStream in) throws IOException {
        String json = new String(in.readAllBytes(), StandardCharsets.UTF_8);
        return gson.fromJson(json, Role.class);
    }
}
