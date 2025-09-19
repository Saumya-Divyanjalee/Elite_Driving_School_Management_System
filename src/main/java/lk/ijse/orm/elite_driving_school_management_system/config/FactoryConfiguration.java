package lk.ijse.orm.elite_driving_school_management_system.config;

import lk.ijse.orm.elite_driving_school_management_system.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class FactoryConfiguration {
    private static FactoryConfiguration factoryConfiguration;
    private SessionFactory sessionFactory;



    private FactoryConfiguration() {
        Properties properties = new Properties();
        try{
            properties.load(new FileInputStream("hibernate.properties"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
