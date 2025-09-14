package lk.ijse.orm.elite_driving_school_management_system.dao.custom.impl;

import jakarta.persistence.Id;
import lk.ijse.orm.elite_driving_school_management_system.dao.custom.CourseDAO;
import lk.ijse.orm.elite_driving_school_management_system.entity.Course;
import lk.ijse.orm.elite_driving_school_management_system.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;
import java.util.Queue;

public class CourseDAOImpl implements CourseDAO {


    @Override
    public void delete(String pk, Session session) {

    }

    @Override
    public List<Course> findAll(Session session) {
        return List.of();
    }

    @Override
    public Optional<Course> findById(String pk, Session session) {
        return Optional.empty();
    }

    @Override
    public Optional<String> getLastPk(Session session) {
        return Optional.empty();
    }

    @Override
    public void save(Course course, Session session) {

    }

    @Override
    public void update(Course course, Session session) {

    }
}
