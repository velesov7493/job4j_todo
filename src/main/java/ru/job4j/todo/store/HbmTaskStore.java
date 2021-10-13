package ru.job4j.todo.store;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.todo.HibernateUtils;
import ru.job4j.todo.persistence.Task;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class HbmTaskStore implements TaskStore {

    private static final Logger LOG = LoggerFactory.getLogger(HbmTaskStore.class.getName());
    private static final SessionFactory SF = HibernateUtils.getSessionFactory();
    private static final HbmTaskStore INSTANCE = new HbmTaskStore();

    private HbmTaskStore() { }

    public static TaskStore getInstance() {
        return INSTANCE;
    }

    private List select(Function<Session, List> command) {
        List result = null;
        Session session = SF.openSession();
        Transaction tx = session.beginTransaction();
        boolean error = false;
        try {
            result = command.apply(session);
            session.flush();
        } catch (Exception ex) {
            error = true;
            LOG.error("Ошибка выполнения запроса", ex);
        } finally {
            if (error) {
                tx.rollback();
            } else {
                tx.commit();
            }
            session.close();
        }
        return result;
    }

    private boolean execute(Consumer<Session> s) {
        Session session = SF.openSession();
        Transaction tx = session.beginTransaction();
        boolean error = false;
        try {
            s.accept(session);
            session.flush();
        } catch (Exception ex) {
            error = true;
            LOG.error("Ошибка выполнения запроса", ex);
        } finally {
            if (error) {
                tx.rollback();
            } else {
                tx.commit();
            }
            session.close();
        }
        return !error;
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
        Task result = null;
        Function<Session, List> f = (s) -> {
            String sql = "SELECT * FROM tz_tasks WHERE id=?";
            Query q =
                    s.createSQLQuery(sql)
                    .setParameter(1, id)
                    .addEntity(Task.class);
            return q.getResultList();
        };
        List<Task> records = select(f);
        if (records != null && !records.isEmpty()) {
            result = records.get(0);
        }
        return result;
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