package lk.ijse.orm.elite_driving_school_management_system.dao.custom.impl;


import lk.ijse.orm.elite_driving_school_management_system.dao.custom.CourseDAO;
import lk.ijse.orm.elite_driving_school_management_system.entity.Course;
import org.hibernate.Session;
import java.util.List;
import java.util.Optional;


public class CourseDAOImpl implements CourseDAO {


    @Override
    public void delete(String pk, Session session) {
        session.detach(session.load(Course.class, pk));

    }

    @Override
    public List<Course> findAll(Session session) {
        return session.createQuery("from Course").list();
    }

    @Override
    public Optional<Course> findById(String pk, Session session) {
         try{
             return Optional.of(session.get(Course.class, pk));
         }catch (Exception e){
             return Optional.empty();
         }
    }

    @Override
    public Optional<String> getLastPk(Session session) {
         List<Course> list = session.createQuery("select courseId from course order by courseId desc").list();
         return list.isEmpty()?Optional.empty():Optional.of(list.get(0).getCourseId());
    }

    @Override
    public void save(Course course, Session session) {
        session.save(course);

    }

    @Override
    public void update(Course course, Session session) {
        session.update(course);

    }
}
