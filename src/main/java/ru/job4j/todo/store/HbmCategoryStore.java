package ru.job4j.todo.store;

import org.hibernate.Session;
import org.hibernate.query.Query;
import ru.job4j.todo.persistence.Category;
import ru.job4j.todo.store.rules.Store;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class HbmCategoryStore extends GenericStore implements Store<Integer, Category> {

    private static final HbmCategoryStore INSTANCE = new HbmCategoryStore();

    private HbmCategoryStore() { }

    public static Store<Integer, Category> getInstance() {
        return INSTANCE;
    }

    @Override
    public List<Category> findAll() {
        Function<Session, List> f = (s) -> {
            String sql = "SELECT * FROM tz_categories";
            Query q = s.createSQLQuery(sql).addEntity(Category.class);
            return q.getResultList();
        };
        return select(f);
    }

    @Override
    public Category getById(Integer id) {
        Function<Session, Category> f = (s) -> {
            String sql = "SELECT * FROM tz_categories WHERE id=?";
            Query q =
                    s.createSQLQuery(sql)
                    .setParameter(1, id)
                    .addEntity(Category.class);
            return (Category) q.getSingleResult();
        };
        return select(f);
    }

    @Override
    public boolean save(Category value) {
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
            Category category = new Category();
            category.setId(id);
            s.delete(category);
        };
        return execute(c);
    }
}
