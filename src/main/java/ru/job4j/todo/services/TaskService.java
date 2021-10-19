package ru.job4j.todo.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.todo.AppUtils;
import ru.job4j.todo.persistence.Task;
import ru.job4j.todo.store.HbmTaskStore;
import ru.job4j.todo.store.rules.TaskStore;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class TaskService {

    private static final TaskService INSTANCE = new TaskService();

    private final Gson gson;
    private final TaskStore store;

    private TaskService() {
        gson = AppUtils.getGson();
        store = HbmTaskStore.getInstance();
    }

    public static TaskService getInstance() {
        return INSTANCE;
    }

    public List<Task> findAll() {
        return store.findAll();
    }

    public List<Task> findAllOpened() {
        return store.findAllOpened();
    }

    public Task getById(Integer id) {
        return store.getById(id);
    }

    public boolean save(Task value) {
        return store.save(value);
    }

    public boolean deleteById(Integer id) {
        return store.deleteById(id);
    }

    public boolean patch(Task value) {
        Task oldVal = store.getById(value.getId());
        oldVal.setDone(value.getDone());
        oldVal.setDescription(value.getDescription());
        return store.save(oldVal);
    }

    public void jsonWriteToStream(List<Task> records, OutputStream out) throws IOException {
        String json = gson.toJson(records);
        out.write(json.getBytes(StandardCharsets.UTF_8));
        out.flush();
        out.close();
    }

    public void jsonWriteToStreamSingleTask(Task task, OutputStream out) throws IOException {
        String json = gson.toJson(task);
        out.write(json.getBytes(StandardCharsets.UTF_8));
        out.flush();
        out.close();
    }

    public Task jsonReadFromStream(InputStream in) throws IOException {
        String json = new String(in.readAllBytes(), StandardCharsets.UTF_8);
        return gson.fromJson(json, Task.class);
    }
}