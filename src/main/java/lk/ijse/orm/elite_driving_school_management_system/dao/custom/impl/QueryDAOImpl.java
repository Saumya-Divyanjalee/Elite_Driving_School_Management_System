package lk.ijse.orm.elite_driving_school_management_system.dao.custom.impl;

import lk.ijse.orm.elite_driving_school_management_system.config.FactoryConfiguration;
import lk.ijse.orm.elite_driving_school_management_system.dao.custom.QueryDAO;
import lk.ijse.orm.elite_driving_school_management_system.entity.Student;
import org.hibernate.Session;

import java.util.List;

public class QueryDAOImpl implements QueryDAO {

    @Override
    public List<Student> getStudentsInAllCourses() {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {

            String hql = "SELECT DISTINCT s " +
                    "FROM Student s " +
                    "WHERE NOT EXISTS (" +
                    "   SELECT c.courseId FROM Course c " +
                    "   WHERE c.courseId NOT IN (" +
                    "       SELECT sc.courseId FROM s.courses sc" +
                    "   )" +
                    ")";

            return session.createQuery(hql, Student.class)
                    .getResultList();
        }
    }
}
