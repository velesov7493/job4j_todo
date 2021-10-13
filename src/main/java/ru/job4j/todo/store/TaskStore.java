package ru.job4j.todo.store;

import ru.job4j.todo.persistence.Task;

import java.util.List;

public interface TaskStore extends Store<Integer, Task> {

    List<Task> findAllOpened();
}
