package lk.ijse.orm.elite_driving_school_management_system.dao.custom.impl;

import lk.ijse.orm.elite_driving_school_management_system.config.FactoryConfiguration;
import lk.ijse.orm.elite_driving_school_management_system.dao.custom.CourseDAO;
import lk.ijse.orm.elite_driving_school_management_system.entity.Course;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CourseDAOImpl implements CourseDAO {

    @Override
    public void delete(Integer id, Session session) {
        Course course = session.get(Course.class, id);
        if(course!=null){
            session.delete(course);
        }

    }

    @Override
    public List<Course> findAll(Session session) {
         Query<Course> query = session.createQuery("FROM Course", Course.class);
         return new ArrayList<>(query.list());
    }

    @Override
    public Optional<Course> findById(Integer id, Session session) {
         Course course = session.get(Course.class, id);
         return Optional.ofNullable(course);
    }

    @Override
    public Optional<String> getLastPk(Session session) {
       Query<Integer> query = session.createQuery("SELECT c.courseId FROM Course c ORDER BY c.courseId DESC ",Integer.class
       ).setMaxResults(1);

       List<Integer> list = query.list();
       if(list.isEmpty()){
           return Optional.empty();
       }
       return Optional.of(String.valueOf(list.get(0)));
    }

    @Override
    public void save(Course course, Session session) {
        session.persist(course);

    }

    @Override
    public void update(Course course, Session session) {
        session.merge(course);

    }
}
