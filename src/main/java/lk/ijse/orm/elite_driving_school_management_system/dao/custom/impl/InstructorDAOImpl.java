package lk.ijse.orm.elite_driving_school_management_system.dao.custom.impl;

import lk.ijse.orm.elite_driving_school_management_system.config.FactoryConfiguration;
import lk.ijse.orm.elite_driving_school_management_system.dao.custom.InstructorDAO;
import lk.ijse.orm.elite_driving_school_management_system.entity.Course;
import lk.ijse.orm.elite_driving_school_management_system.entity.Instructor;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class InstructorDAOImpl implements InstructorDAO {

 private final FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();

    @Override
    public boolean save(Instructor entity) {
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
    public boolean update(Instructor entity) throws Exception {
        Session session = factoryConfiguration.getSession();
        Transaction tx = session.beginTransaction();
        session.merge(entity);
        tx.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String s) throws Exception {
        Session session = factoryConfiguration.getSession();
        Transaction tx = session.beginTransaction();
        Instructor instructor = session.get(Instructor.class, s);
        if (instructor != null) {
            session.remove(instructor);
        }
        tx.commit();
        session.close();
        return instructor != null;
    }


    @Override
    public List<Instructor> findAll() throws Exception {
        Session session = factoryConfiguration.getSession();
        List<Instructor> list = session.createQuery("FROM Instructor", Instructor.class).list();
        session.close();
        return list;
    }
    public Instructor findById(long id) throws Exception {
        try (Session session = FactoryConfiguration.getInstance().getSession().getSessionFactory().openSession()) {
            return session.get(Instructor.class, id);
        }
    }

}

