package ru.job4j.todo.store;

import org.hibernate.Session;
import org.hibernate.query.Query;
import ru.job4j.todo.persistence.Role;
import ru.job4j.todo.store.rules.Store;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class HbmRoleStore extends GenericStore implements Store<Integer, Role> {

    private static final HbmRoleStore INSTANCE = new HbmRoleStore();

    private HbmRoleStore() { }

    public static Store<Integer, Role> getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Role> findAll() {
        Function<Session, List> f = (s) -> {
            String sql = "SELECT * FROM tz_roles";
            Query q = s.createSQLQuery(sql).addEntity(Role.class);
            return q.getResultList();
        };
        return select(f);
    }

    @Override
    public Role getById(Integer id) {
        Function<Session, Role> f = (s) -> {
            String sql = "SELECT * FROM tz_roles WHERE id=?";
            Query q =
                    s.createSQLQuery(sql)
                    .setParameter(1, id)
                    .addEntity(Role.class);
            return (Role) q.getSingleResult();
        };
        return select(f);
    }

    @Override
    public boolean save(Role value) {
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
            Role value = new Role();
            value.setId(id);
            s.delete(value);
        };
        return execute(c);
    }
}