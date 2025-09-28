package lk.ijse.orm.elite_driving_school_management_system.dao.custom.impl;

import lk.ijse.orm.elite_driving_school_management_system.config.FactoryConfiguration;
import lk.ijse.orm.elite_driving_school_management_system.dao.custom.StudentDAO;
import lk.ijse.orm.elite_driving_school_management_system.entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {

    @Override
    public boolean save(Student student) throws Exception {
        Transaction tx = null;
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            tx = session.beginTransaction();
            session.persist(student);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                try {
                    tx.rollback();
                } catch (Exception rollbackException) {
                    // Log the rollback exception but don't throw it
                    rollbackException.printStackTrace();
                }
            }
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Student student) throws Exception {
        Transaction tx = null;
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            tx = session.beginTransaction();
            session.merge(student);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                try {
                    tx.rollback();
                } catch (Exception rollbackException) {
                    // Log the rollback exception but don't throw it
                    rollbackException.printStackTrace();
                }
            }
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Long id) throws Exception {
        Transaction tx = null;
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            tx = session.beginTransaction();
            Student student = session.get(Student.class, id);
            if (student != null) {
                session.remove(student);
            }
            tx.commit();
            return student != null;
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                try {
                    tx.rollback();
                } catch (Exception rollbackException) {
                    // Log the rollback exception but don't throw it
                    rollbackException.printStackTrace();
                }
            }
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Student> findAll() throws Exception {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            return session.createQuery("FROM Student", Student.class).list();
        }
    }

    @Override
    public Student findById(Long id) throws Exception {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            return session.get(Student.class, id);
        }
    }


}