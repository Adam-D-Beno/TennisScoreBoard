package config;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateConfig {
    private static volatile HibernateConfig instance;

    private static SessionFactory sessionFactory;


    private HibernateConfig() {
    }

    public static HibernateConfig getInstance() {
        if (instance == null) {
            synchronized (HibernateConfig.class) {
                if (instance == null) {
                    instance = new HibernateConfig();
                    sessionFactory = buildSessionFactory();
                }
            }
        }
        return instance;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    private static SessionFactory buildSessionFactory() {
        try {
            return getConfiguration().buildSessionFactory();
        } catch (HibernateException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    private static Configuration getConfiguration() {
        Configuration configuration = new Configuration();
        configuration.configure();
        return configuration;
    }
}
