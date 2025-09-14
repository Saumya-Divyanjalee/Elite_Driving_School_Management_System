package lk.ijse.orm.elite_driving_school_management_system.util;

import lk.ijse.orm.elite_driving_school_management_system.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class FactoryConfiguration {
    private static FactoryConfiguration factoryConfiguration;
    private SessionFactory sessionFactory;

    private FactoryConfiguration() {
        Configuration configuration = new Configuration()

                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(Lesson.class)
                .addAnnotatedClass(Payment.class);

        sessionFactory = configuration.buildSessionFactory();
    }
    public static FactoryConfiguration getInstance() {
         return (factoryConfiguration == null)? factoryConfiguration = new FactoryConfiguration()
                 : factoryConfiguration;
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }



}
