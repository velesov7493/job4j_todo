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
            String sql = "SELECT * FROM tz_tasks WHERE done=0";
            Query q = s.createSQLQuery(sql).addEntity(Task.class);
            return q.getResultList();
        };
        return select(f);
    }

    @Override
    public List<Task> findAll() {
        Function<Session, List> f = (s) -> {
            String sql = "SELECT * FROM tz_tasks";
            Query q = s.createSQLQuery(sql).addEntity(Task.class);
            return q.getResultList();
        };
        return select(f);
    }

    @Override
    public Task getById(Integer id) {
        Function<Session, Task> f = (s) -> {
            String sql = "SELECT * FROM tz_tasks WHERE id=?";
            Query q =
                    s.createSQLQuery(sql)
                    .setParameter(1, id)
                    .addEntity(Task.class);
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