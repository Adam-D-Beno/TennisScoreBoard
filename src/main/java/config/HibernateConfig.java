package config;

import lombok.Setter;
import lombok.experimental.UtilityClass;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@UtilityClass
public class HibernateConfig {


    public Session getCurrentSession() {
        return buildSessionFactory().getCurrentSession();
    }

    private SessionFactory buildSessionFactory() {
        try(SessionFactory sessionFactory = getConfiguration().buildSessionFactory()) {
            return sessionFactory;
        } catch (HibernateException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    private Configuration getConfiguration() {
        Configuration configuration = new Configuration();
        configuration.configure();
        return configuration;
    }
}
