package lk.ijse.orm.elite_driving_school_management_system.dao.custom.impl;

import lk.ijse.orm.elite_driving_school_management_system.config.FactoryConfiguration;
import lk.ijse.orm.elite_driving_school_management_system.dao.custom.UserDAO;
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
        User user = session.get(User.class, id);
        if (user != null) {
            session.remove(user);
        }
        transaction.commit();
        session.close();
        return user != null;
    }

    @Override
    public List<User> findAll() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        List<User> list = session.createQuery("from User", User.class).list();
        session.close();
        return list;
    }

    @Override
    public User findByUserName(String userName) throws Exception {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            return session.createQuery("SELECT u FROM User u WHERE u.username = :userName", User.class)
                    .setParameter("userName", userName)
                    .uniqueResult();
        }
    }
}