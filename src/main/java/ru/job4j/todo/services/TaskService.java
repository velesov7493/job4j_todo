package ru.job4j.todo.services;

import ru.job4j.todo.persistence.Task;
import ru.job4j.todo.store.HbmTaskStore;
import ru.job4j.todo.store.rules.TaskStore;

import java.util.List;
import java.util.Set;

public class TaskService {

    private static final TaskService INSTANCE = new TaskService();

    private final TaskStore store;

    private TaskService() {
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

    public boolean patch(Task value, Set<Integer> categoryIds) {
        Task oldVal = store.getById(value.getId());
        oldVal.setDone(value.getDone());
        oldVal.setDescription(value.getDescription());
        if (categoryIds != null && !categoryIds.isEmpty()) {
            CategoryService cs = CategoryService.getInstance();
            oldVal.getCategories().clear();
            for (int categoryId : categoryIds) {
                oldVal.addCategory(cs.getById(categoryId));
            }
        }
        return store.save(oldVal);
    }

    public boolean saveWithCategories(Task value, String[] categoryIds) {
        if (value.getId() == 0 && !store.save(value)) {
            return false;
        }
        CategoryService cs = CategoryService.getInstance();
        value.getCategories().clear();
        for (String categoryId : categoryIds) {
            value.addCategory(cs.getById(Integer.parseInt(categoryId)));
        }
        return store.save(value);
    }
}