package ru.job4j.todo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class AppUtils {

    private static final StandardServiceRegistry REGISTRY =
            new StandardServiceRegistryBuilder().configure().build();
    private static final SessionFactory SF =
            new MetadataSources(REGISTRY).buildMetadata().buildSessionFactory();
    private static Gson gson;

    public static SessionFactory getSessionFactory() {
        return SF;
    }

    public static void releaseSessionFactory() {
        SF.close();
        StandardServiceRegistryBuilder.destroy(REGISTRY);
    }

    public static Gson getGson() {
        if (gson == null) {
            gson =
                    new GsonBuilder()
                    .setDateFormat("dd.MM.yyyy")
                    .create();
        }
        return gson;
    }
}
