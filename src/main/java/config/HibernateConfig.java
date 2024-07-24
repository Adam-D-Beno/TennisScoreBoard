package config;

import lombok.Setter;
import lombok.experimental.UtilityClass;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateConfig {

    public HibernateConfig() {
    }

    public static SessionFactory buildSessionFactory() {
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
