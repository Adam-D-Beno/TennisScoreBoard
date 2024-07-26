package config;

import lombok.Getter;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateConfig {
    private static volatile HibernateConfig instance;
    @Getter
    private static final SessionFactory sessionFactory;

    static {
        sessionFactory = buildSessionFactory();
    }

    private HibernateConfig() {
    }

    public static HibernateConfig getInstance() {
        if (instance == null) {
            synchronized (HibernateConfig.class) {
                if (instance == null) {
                    instance = new HibernateConfig();
                }
            }
        }
        return instance;
    }

    private static SessionFactory buildSessionFactory() {
        try(SessionFactory sessionFactory = getConfiguration().buildSessionFactory()) {
            return sessionFactory;
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
