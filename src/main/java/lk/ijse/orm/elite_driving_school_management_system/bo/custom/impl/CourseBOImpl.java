package lk.ijse.orm.elite_driving_school_management_system.bo.custom.impl;

import lk.ijse.orm.elite_driving_school_management_system.bo.custom.CourseBO;
import lk.ijse.orm.elite_driving_school_management_system.config.FactoryConfiguration;
import lk.ijse.orm.elite_driving_school_management_system.dao.DAOFactory;
import lk.ijse.orm.elite_driving_school_management_system.dao.DAOTypes;
import lk.ijse.orm.elite_driving_school_management_system.dao.custom.CourseDAO;
import lk.ijse.orm.elite_driving_school_management_system.dto.CourseDTO;
import lk.ijse.orm.elite_driving_school_management_system.entity.Course;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CourseBOImpl implements CourseBO {

    private final CourseDAO courseDAO =
            DAOFactory.getInstance().getDAO(DAOTypes.COURSE);

    @Override
    public boolean saveCourse(CourseDTO dto) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            Course course = new Course();
            course.setCourseId(Integer.parseInt(String.valueOf(dto.getCourseId())));
            course.setCourseName(dto.getCourseName());
            course.setTimePeriod(dto.getTimePeriod());

            courseDAO.save(course, session);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean updateCourse(CourseDTO dto) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            Course course = new Course();
            course.setCourseId(Integer.parseInt(String.valueOf(dto.getCourseId())));
            course.setCourseName(dto.getCourseName());
            course.setTimePeriod(dto.getTimePeriod());

            courseDAO.update(course, session);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean deleteCourse(Integer id) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            courseDAO.delete(Long.valueOf(id), session);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public List<CourseDTO> getAllCourses() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        try {
            List<Course> courses = courseDAO.findAll(session);
            List<CourseDTO> dtos = new ArrayList<>();
            for (Course c : courses) {
                dtos.add(new CourseDTO(
                        c.getCourseId(),
                        c.getCourseName(),
                        c.getTimePeriod()
                ));
            }
            return dtos;
        } finally {
            session.close();
        }
    }

    @Override
    public Optional<CourseDTO> findCourseById(Integer id) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        try {
            Optional<Course> courseOpt = courseDAO.findById(Long.valueOf(id), session);
            return courseOpt.map(c -> new CourseDTO(
                    c.getCourseId(),
                    c.getCourseName(),
                    c.getTimePeriod()
            ));
        } finally {
            session.close();
        }
    }

    @Override
    public Optional<String> getLastCourseId() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        try {
            return courseDAO.getLastPk(session);
        } finally {
            session.close();
        }
    }
}
