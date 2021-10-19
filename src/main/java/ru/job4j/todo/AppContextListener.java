package ru.job4j.todo;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class AppContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        AppUtils.getSessionFactory();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        AppUtils.releaseSessionFactory();
    }
}
