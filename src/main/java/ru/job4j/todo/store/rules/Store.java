package ru.job4j.todo.store.rules;

import java.util.List;

public interface Store<K, V> {

    List<V> findAll();

    V getById(K id);

    boolean save(V value);

    boolean deleteById(K id);
}
