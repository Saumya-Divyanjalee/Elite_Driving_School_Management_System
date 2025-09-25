package lk.ijse.orm.elite_driving_school_management_system.dao.custom.impl;

import lk.ijse.orm.elite_driving_school_management_system.config.FactoryConfiguration;
import lk.ijse.orm.elite_driving_school_management_system.dao.custom.CourseDAO;
import lk.ijse.orm.elite_driving_school_management_system.entity.Course;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.List;


public class CourseDAOImpl implements CourseDAO {

 FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();


    @Override
    public Course findById(Long id) throws Exception {
        try (Session session = FactoryConfiguration.getInstance().getSession().getSessionFactory().openSession()) {
            return session.get(Course.class, id);
        }
    }



    @Override
    public boolean save(Course entity) throws Exception {
        try (Session session = factoryConfiguration.getSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(entity);
            tx.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Course entity) throws Exception {
        Session session = factoryConfiguration.getSession();
        Transaction tx = session.beginTransaction();
        session.merge(entity);
        tx.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String id) throws Exception {
        Session session = factoryConfiguration.getSession();
        Transaction tx = session.beginTransaction();
        Course course = session.get(Course.class, id);
        if (course != null) {
            session.remove(course);
        }
        tx.commit();
        session.close();
        return course != null;
    }

    @Override
    public List<Course> findAll() throws Exception {
        Session session = factoryConfiguration.getSession();
        List<Course> list = session.createQuery("FROM Course", Course.class).list();
        session.close();
        return list;
    }
}
