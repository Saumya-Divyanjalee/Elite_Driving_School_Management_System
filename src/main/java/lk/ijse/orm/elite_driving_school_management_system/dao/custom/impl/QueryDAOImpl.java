package lk.ijse.orm.elite_driving_school_management_system.dao.custom.impl;

import lk.ijse.orm.elite_driving_school_management_system.config.FactoryConfiguration;
import lk.ijse.orm.elite_driving_school_management_system.dao.custom.QueryDAO;
import lk.ijse.orm.elite_driving_school_management_system.util.CustomQueries;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class QueryDAOImpl implements QueryDAO {

    @Override
    public List<Object[]> getCourseStudentCount() {
        Transaction transaction = null;
        List<Object[]> list = null;

        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            transaction = session.beginTransaction();

            Query<Object[]> query = session.createQuery(CustomQueries.COURSE_STUDENT_COUNT, Object[].class);
            list = query.getResultList();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Object> getStudentsInAllCourses(int totalCourses) {
        Transaction transaction = null;
        List<Object> list = null;

        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            transaction = session.beginTransaction();

            Query<Object> query = session.createQuery(CustomQueries.STUDENTS_IN_ALL_COURSES, Object.class);
            query.setParameter("totalCourses", totalCourses);

            list = query.getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
        return list;
    }
}
