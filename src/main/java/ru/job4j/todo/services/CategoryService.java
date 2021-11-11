package ru.job4j.todo.services;

import ru.job4j.todo.persistence.Category;
import ru.job4j.todo.store.HbmCategoryStore;
import ru.job4j.todo.store.rules.Store;

import java.util.List;

public class CategoryService {

    private static final CategoryService INSTANCE = new CategoryService();

    private final Store<Integer, Category> store;

    private CategoryService() {
        store = HbmCategoryStore.getInstance();
    }

    public static CategoryService getInstance() {
        return INSTANCE;
    }

    public List<Category> findAll() {
        return store.findAll();
    }

    public Category getById(Integer id) {
        return store.getById(id);
    }

    public boolean save(Category value) {
        return store.save(value);
    }

    public boolean deleteById(Integer id) {
        return store.deleteById(id);
    }
}
