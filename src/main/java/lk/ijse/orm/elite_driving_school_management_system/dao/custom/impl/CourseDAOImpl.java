package lk.ijse.orm.elite_driving_school_management_system.dao.custom.impl;

import jakarta.persistence.Table;
import lk.ijse.orm.elite_driving_school_management_system.config.FactoryConfiguration;
import lk.ijse.orm.elite_driving_school_management_system.dao.custom.CourseDAO;
import lk.ijse.orm.elite_driving_school_management_system.entity.Course;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;


public class CourseDAOImpl implements CourseDAO {
    @Override
    public boolean delete(Long aLong) throws Exception {
         Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

    }

    @Override
    public List<Course> findAll() throws Exception {
        return List.of();
    }

    @Override
    public Course findById(Long aLong) throws Exception {
        return null;
    }

    @Override
    public boolean save(Course entity) throws Exception {
        return false;
    }

    @Override
    public boolean update(Course entity) throws Exception {
        return false;
    }
}
