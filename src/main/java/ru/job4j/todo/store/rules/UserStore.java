package ru.job4j.todo.store.rules;

import ru.job4j.todo.persistence.User;

public interface UserStore extends Store<Integer, User> {

    User getByLoginAndPassword(String login, String password);
}
