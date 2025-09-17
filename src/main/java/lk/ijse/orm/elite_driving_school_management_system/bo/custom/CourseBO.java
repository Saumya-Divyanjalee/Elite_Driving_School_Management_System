package lk.ijse.orm.elite_driving_school_management_system.bo.custom;

import lk.ijse.orm.elite_driving_school_management_system.bo.SuperBO;
import lk.ijse.orm.elite_driving_school_management_system.dto.CourseDTO;
import lk.ijse.orm.elite_driving_school_management_system.entity.Course;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface CourseBO extends SuperBO {

     boolean saveCourse(CourseDTO dto) throws Exception;
     boolean updateCourse(CourseDTO dto) throws Exception;
     boolean deleteCourse(Integer id) throws Exception;
     List<CourseDTO> getAllCourses() throws Exception;
     Optional<CourseDTO> findCourseById(Integer id) throws Exception;
     Optional<String> getLastCourseId() throws Exception;

}
