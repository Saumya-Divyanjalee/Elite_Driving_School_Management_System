package lk.ijse.orm.elite_driving_school_management_system.dao.custom.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import lk.ijse.orm.elite_driving_school_management_system.config.FactoryConfiguration;
import lk.ijse.orm.elite_driving_school_management_system.dao.custom.UserDAO;
import lk.ijse.orm.elite_driving_school_management_system.entity.Student;
import lk.ijse.orm.elite_driving_school_management_system.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDAOImpl implements UserDAO {


    @Override
    public boolean save(User entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.persist(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(User entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.merge(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(Long id) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Student student = session.get(Student.class, id);
        if (student != null) {
            session.remove(student);
        }
        transaction.commit();
        session.close();
        return student != null;

    }

    @Override
    public List<User> findAll() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        List<User> list = session.createQuery("from User").list();
        session.close();
        return list;
    }


    @Override
    public User findByUserName(String userName) throws Exception {
        EntityManager entityManager = FactoryConfiguration.getInstance().getSession();
        try{
             Query query = (Query) entityManager.createQuery("SELECT u FROM User u WHERE u.userName = :userName");
             query.setParameter("userName", userName);
             return (User) query.getSingleResult();

        }catch(NoResultException e){
            return null;
        }finally {
            entityManager.close();
        }
    }
}
