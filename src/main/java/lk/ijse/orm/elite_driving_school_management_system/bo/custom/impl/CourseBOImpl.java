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

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CourseBOImpl implements CourseBO {

    CourseDAO courseDAO = (CourseDAO) DAOFactory.getInstance().getDAO(DAOTypes.COURSE);

    @Override
    public boolean deleteCourse(Long id) throws Exception {
         return courseDAO.delete(id);
    }

    @Override
    public List<CourseDTO> getAllCourse() throws Exception {
        List<Course> entity = courseDAO.getAll();
        List<CourseDTO> courseDTOS = new ArrayList<>();
        for(Course c : entity){
            courseDTOS.add(new CourseDTO(
                    c.getCourseId(),
                    c.getCourseName(),
                    c.getTimePeriod()
            ));

        }
        return courseDTOS;
    }

    @Override
    public Long getNextIdCourse() throws SQLException, ClassNotFoundException {
        return courseDAO.getNextId();
    }

    @Override
    public boolean saveCourse(CourseDTO courseDTO) throws Exception {
        return courseDAO.save(new Course(
                courseDTO.getCourseId(),
                courseDTO.getCourseName(),
                courseDTO.getTimePeriod()
        ));
    }

    @Override
    public boolean updateCourse(CourseDTO courseDTO) throws Exception {
          return courseDAO.update(new Course(
                  courseDTO.getCourseId(),
                  courseDTO.getCourseName(),
                  courseDTO.getTimePeriod()
          ));
    }
}
