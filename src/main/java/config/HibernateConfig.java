package config;

import lombok.Setter;
import lombok.experimental.UtilityClass;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateConfig {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    public HibernateConfig() {
    }

    public static SessionFactory buildSessionFactory() {
       return getConfiguration().buildSessionFactory();
    }

    private static Configuration getConfiguration() {
        Configuration configuration = new Configuration();
        configuration.configure();
        return configuration;
    }
}
