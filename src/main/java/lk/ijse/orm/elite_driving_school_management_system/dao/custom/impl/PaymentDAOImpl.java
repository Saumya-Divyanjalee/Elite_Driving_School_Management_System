package lk.ijse.orm.elite_driving_school_management_system.dao.custom.impl;

import lk.ijse.orm.elite_driving_school_management_system.config.FactoryConfiguration;
import lk.ijse.orm.elite_driving_school_management_system.dao.custom.PaymentDAO;
import lk.ijse.orm.elite_driving_school_management_system.entity.Payment;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class PaymentDAOImpl implements PaymentDAO {

    private final FactoryConfiguration fc = FactoryConfiguration.getInstance();

    //   rollback on failure prevents lock timeout
    @Override
    public boolean save(Payment entity) throws Exception {
        Transaction tx = null;
        try (Session session = fc.getSession()) {
            tx = session.beginTransaction();
            session.persist(entity);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                try { tx.rollback(); } catch (Exception rb) { rb.printStackTrace(); }
            }
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public boolean update(Payment entity) throws Exception {
        Transaction tx = null;
        try (Session session = fc.getSession()) {
            tx = session.beginTransaction();
            session.merge(entity);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                try { tx.rollback(); } catch (Exception rb) { rb.printStackTrace(); }
            }
            e.printStackTrace();
            throw e;
        }
    }

    //   String id — matches PaymentDAO interface
    @Override
    public boolean delete(String id) throws Exception {
        Transaction tx = null;
        try (Session session = fc.getSession()) {
            tx = session.beginTransaction();
            Payment p = session.get(Payment.class, Long.parseLong(id));
            if (p != null) session.remove(p);
            tx.commit();
            return p != null;
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                try { tx.rollback(); } catch (Exception rb) { rb.printStackTrace(); }
            }
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public List<Payment> findAll() throws Exception {
        try (Session session = fc.getSession()) {
            return session.createQuery("FROM Payment", Payment.class).list();
        }
    }

    //   NEW: for auto-generating next payment ID in UI
    @Override
    public Long getLastPaymentId() throws Exception {
        try (Session session = fc.getSession()) {
            Long result = session.createQuery(
                    "SELECT MAX(p.id) FROM Payment p", Long.class
            ).uniqueResult();
            return result != null ? result : 0L;
        }
    }
}