package lk.ijse.orm.elite_driving_school_management_system.bo.custom.impl;

import lk.ijse.orm.elite_driving_school_management_system.bo.custom.CourseBO;
import lk.ijse.orm.elite_driving_school_management_system.dao.DAOFactory;
import lk.ijse.orm.elite_driving_school_management_system.dao.custom.CourseDAO;
import lk.ijse.orm.elite_driving_school_management_system.dto.CourseDTO;
import lk.ijse.orm.elite_driving_school_management_system.entity.Course;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CourseBOImpl implements CourseBO {

    CourseDAO courseDAO = (CourseDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.COURSE);

    @Override
    public boolean deleteCourse(String id) throws Exception {
         return courseDAO.delete(id);
    }

    @Override
    public List<CourseDTO> getAllCourse() throws Exception {
     ArrayList<Course> courses = (ArrayList<Course>) courseDAO.findAll();
     ArrayList<CourseDTO> list = new ArrayList<>();
     for (Course c : courses) {
         list.add(new CourseDTO(
                 c.getCourseId(),
                 c.getCourseName(),
                 c.getTimePeriod(),
                 c.getCourseFee()));

     }
     return list;
    }

    @Override
    public Long getNextIdCourse() throws SQLException, ClassNotFoundException {
        return  null;
    }

    @Override
    public boolean saveCourse(CourseDTO courseDTO) throws Exception {
        Course course = new Course(
                courseDTO.getCourseId(),
                courseDTO.getCourseName(),
                courseDTO.getTimePeriod(),
                courseDTO.getCourseFee()
        );
        return courseDAO.save(course);
    }

    @Override
    public boolean updateCourse(CourseDTO courseDTO) throws Exception {
           Course course = new Course(
                   courseDTO.getCourseId(),
                   courseDTO.getCourseName(),
                   courseDTO.getTimePeriod(),
                   courseDTO.getCourseFee()

           );
           return courseDAO.update(course);
    }

    @Override
    public List<CourseDTO> findAll() throws Exception {
         return courseDAO.findAll().stream().map(course ->
                 new CourseDTO(
                         course.getCourseId(),
                         course.getCourseName(),
                         course.getTimePeriod(),
                         course.getCourseFee()
                 )).collect(Collectors.toList());
    }
}
