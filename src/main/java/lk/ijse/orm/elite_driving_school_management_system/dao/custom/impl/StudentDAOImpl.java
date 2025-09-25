package lk.ijse.orm.elite_driving_school_management_system.dao.custom.impl;

import lk.ijse.orm.elite_driving_school_management_system.config.FactoryConfiguration;
import lk.ijse.orm.elite_driving_school_management_system.dao.custom.StudentDAO;
import lk.ijse.orm.elite_driving_school_management_system.entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class StudentDAOImpl implements StudentDAO {

    @Override
    public boolean save(Student student) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction tx = session.beginTransaction();
        session.persist(student);
        tx.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Student student) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction tx = session.beginTransaction();
        session.merge(student);
        tx.commit();
        session.close();
        return true;
     }

    @Override
    public boolean delete(String id) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction tx = session.beginTransaction();
        Student student = session.get(Student.class, id);
        if (student != null) {
            session.remove(student);
        }
        tx.commit();
        session.close();
        return student != null;
    }

    @Override
    public List<Student> findAll() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        List<Student> list = session.createQuery("FROM Student", Student.class).list();
        session.close();
        return list;
    }

    public Student findById(Long id) throws Exception {
        try (Session session = FactoryConfiguration.getInstance().getSession().getSessionFactory().openSession()) {
            return session.get(Student.class, id);
        }
    }
}
