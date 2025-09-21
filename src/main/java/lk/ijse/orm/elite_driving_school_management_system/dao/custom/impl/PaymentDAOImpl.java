package lk.ijse.orm.elite_driving_school_management_system.dao.custom.impl;

import lk.ijse.orm.elite_driving_school_management_system.config.FactoryConfiguration;
import lk.ijse.orm.elite_driving_school_management_system.dao.custom.PaymentDAO;
import lk.ijse.orm.elite_driving_school_management_system.dao.custom.QueryDAO;
import lk.ijse.orm.elite_driving_school_management_system.entity.Payment;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class PaymentDAOImpl implements PaymentDAO {

    @Override
    public boolean delete(Long id) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            Payment payment = session.get(Payment.class, id);
            if (payment != null) {
                session.delete(payment);
                transaction.commit();
                return true;
            } else {
                transaction.rollback();
                return false;
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public List<Payment> getAll() throws Exception {
        try(Session session = FactoryConfiguration.getInstance().getSession()){
            return session.createQuery("FROM Payment", Payment.class).list();
        }
    }

    @Override
    public Long getNextId() throws SQLException, ClassNotFoundException {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            Long maxId = (Long) session.createQuery("SELECT MAX(p.id) FROM Payment p").uniqueResult();
            return (maxId == null) ? 1L : maxId + 1;
        }
    }


    @Override
    public boolean save(Payment payment) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.persist(payment);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }


    @Override
    public boolean update(Payment payment) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.merge(payment);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

}
