package lk.ijse.orm.elite_driving_school_management_system.dao.custom.impl;

import lk.ijse.orm.elite_driving_school_management_system.config.FactoryConfiguration;
import lk.ijse.orm.elite_driving_school_management_system.dao.custom.CourseDAO;
import lk.ijse.orm.elite_driving_school_management_system.entity.Course;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;


public class CourseDAOImpl implements CourseDAO {

    FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();

    @Override
    public Course findById(Long id) throws Exception {
        try (Session session = factoryConfiguration.getSession()) {
            return session.get(Course.class, id);
        }
    }

    @Override
    public Long getLastCourseId() throws Exception {
        try (Session session = factoryConfiguration.getSession()) {
            Query<Long> query = session.createQuery("SELECT courseId FROM Course ORDER BY courseId DESC", Long.class);
            query.setMaxResults(1);
            List<Long> list = query.list();
            return list.isEmpty() ? null : list.get(0);
        }
    }

    @Override
    public boolean save(Course entity) throws Exception {
        Transaction tx = null;
        try (Session session = factoryConfiguration.getSession()) {
            tx = session.beginTransaction();
            // For entities with auto-generated IDs, we should use persist
            // but we need to make sure the ID is not manually set
            if (entity.getCourseId() != 0) {
                // If ID is set, treat as update
                session.merge(entity);
            } else {
                // If ID is not set, treat as new entity
                session.persist(entity);
            }
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
    public boolean update(Course entity) throws Exception {
        Transaction tx = null;
        try (Session session = factoryConfiguration.getSession()) {
            tx = session.beginTransaction();
            session.merge(entity);
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
        try (Session session = factoryConfiguration.getSession()) {
            tx = session.beginTransaction();
            Course course = session.get(Course.class, id);
            if (course != null) {
                session.remove(course);
            }
            tx.commit();
            return course != null;
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
    public List<Course> findAll() throws Exception {
        try (Session session = factoryConfiguration.getSession()) {
            return session.createQuery("FROM Course", Course.class).list();
        }
    }
}