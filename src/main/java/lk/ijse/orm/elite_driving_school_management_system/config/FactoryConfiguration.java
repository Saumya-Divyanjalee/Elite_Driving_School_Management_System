package lk.ijse.orm.elite_driving_school_management_system.config;


import lk.ijse.orm.elite_driving_school_management_system.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.Properties;

public class FactoryConfiguration {

    private static FactoryConfiguration instance;
    private final SessionFactory sessionFactory;

    private FactoryConfiguration() {
        Configuration configuration = new Configuration();
        try {
            Properties properties = new Properties();
            properties.load(getClass().getResourceAsStream("lk/ijse/orm/elite_driving_school_management_system/hibernate.properties"));
            configuration.setProperties(properties);
        } catch (IOException e) {
            e.printStackTrace();
        }


        configuration.addAnnotatedClass(Course.class);
        configuration.addAnnotatedClass(Lesson.class);
        configuration.addAnnotatedClass(Payment.class);
        configuration.addAnnotatedClass(Student.class);
        configuration.addAnnotatedClass(Instructor.class);
        configuration.addAnnotatedClass(User.class);

        sessionFactory = configuration.buildSessionFactory();
    }

    public static FactoryConfiguration getInstance() {
        return (instance == null) ?
                instance = new FactoryConfiguration() : instance;
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }
}



