package ru.job4j.todo.store;

import org.hibernate.Session;
import org.hibernate.query.Query;
import ru.job4j.todo.Security;
import ru.job4j.todo.persistence.User;
import ru.job4j.todo.store.rules.UserStore;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class HbmUserStore extends GenericStore implements UserStore {

    private static final HbmUserStore INSTANCE = new HbmUserStore();

    private HbmUserStore() { }

    public static UserStore getInstance() {
        return INSTANCE;
    }

    @Override
    public List<User> findAll() {
        Function<Session, List> f = (s) -> {
            String sql = "SELECT * FROM tz_users";
            Query q = s.createSQLQuery(sql).addEntity(User.class);
            return q.getResultList();
        };
        return select(f);
    }

    @Override
    public User getById(Integer id) {
        Function<Session, User> f = (s) -> {
            String sql = "SELECT * FROM tz_users WHERE id=?";
            Query q =
                    s.createSQLQuery(sql)
                    .setParameter(1, id)
                    .addEntity(User.class);
            return (User) q.getSingleResult();
        };
        return select(f);
    }

    @Override
    public User getByLoginAndPassword(String login, String password) {
        Function<Session, User> f = (s) -> {
            String sql = "SELECT * FROM tz_users WHERE login=? AND password=?";
            Query q =
                    s.createSQLQuery(sql)
                    .setParameter(1, login)
                    .setParameter(2, password)
                    .addEntity(User.class);
            return (User) q.getSingleResult();
        };
        return select(f);
    }

    @Override
    public boolean save(User value) {
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
            User value = new User();
            value.setId(id);
            s.delete(value);
        };
        return execute(c);
    }
}