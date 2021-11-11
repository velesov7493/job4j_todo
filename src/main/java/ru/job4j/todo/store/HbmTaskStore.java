package ru.job4j.todo.store;

import org.hibernate.Session;
import org.hibernate.query.Query;
import ru.job4j.todo.persistence.Task;
import ru.job4j.todo.store.rules.TaskStore;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class HbmTaskStore extends GenericStore implements TaskStore {

    private static final HbmTaskStore INSTANCE = new HbmTaskStore();

    private HbmTaskStore() { }

    public static TaskStore getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Task> findAllOpened() {
        Function<Session, List> f = (s) -> {
            String hql =
                    "SELECT DISTINCT t FROM Task t "
                    + "JOIN FETCH t.categories WHERE t.done=0";
            Query q = s.createQuery(hql);
            return q.getResultList();
        };
        return select(f);
    }

    @Override
    public List<Task> findAll() {
        Function<Session, List> f = (s) -> {
            String hql =
                    "SELECT DISTINCT t FROM Task t "
                    + "JOIN FETCH t.categories";
            Query q = s.createQuery(hql);
            return q.getResultList();
        };
        return select(f);
    }

    @Override
    public Task getById(Integer id) {
        Function<Session, Task> f = (s) -> {
            String hql =
                    "SELECT t FROM Task t "
                    + "JOIN FETCH t.categories "
                    + "WHERE t.id=:tid";
            Query q =
                    s.createQuery(hql)
                    .setParameter("tid", id);
            return (Task) q.getSingleResult();
        };
        return select(f);
    }

    @Override
    public boolean save(Task value) {
        Consumer<Session> c = (s) -> {
            if (value.getId() == 0) {
                s.save(value);
            } else {
                s.update(value);
            }
        };
        return execute(c);
    }

    @Override
    public boolean deleteById(Integer id) {
        Consumer<Session> c = (s) -> {
            Task task = new Task();
            task.setId(id);
            s.delete(task);
        };
        return execute(c);
    }
}