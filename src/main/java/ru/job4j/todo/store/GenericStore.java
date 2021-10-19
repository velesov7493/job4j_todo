package ru.job4j.todo.store;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.todo.AppUtils;

import java.util.function.Consumer;
import java.util.function.Function;

class GenericStore {

    private static final Logger LOG = LoggerFactory.getLogger(GenericStore.class.getName());
    private static final SessionFactory SF = AppUtils.getSessionFactory();

    protected <T> T select(Function<Session, T> command) {
        T result = null;
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

    protected boolean execute(Consumer<Session> dmlCommand) {
        Session session = SF.openSession();
        Transaction tx = session.beginTransaction();
        boolean error = false;
        try {
            dmlCommand.accept(session);
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
}