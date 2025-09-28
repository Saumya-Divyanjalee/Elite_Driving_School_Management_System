package lk.ijse.orm.elite_driving_school_management_system.bo.custom;

import lk.ijse.orm.elite_driving_school_management_system.bo.SuperBO;
import lk.ijse.orm.elite_driving_school_management_system.config.FactoryConfiguration;
import lk.ijse.orm.elite_driving_school_management_system.dto.LessonDTO;
import lk.ijse.orm.elite_driving_school_management_system.entity.Lesson;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface LessonBO extends SuperBO {
     boolean saveLesson(LessonDTO dto) throws Exception ;
     boolean updateLesson(LessonDTO dto) throws Exception ;
     boolean deleteLesson(String id) throws Exception ;
     List<LessonDTO> findAllLesson() throws Exception ;
     List<String> getAllInstructorIds() throws Exception;
     List<String> getAllCourseIds() throws Exception;
     List<String> getAllStudentIds() throws Exception;

     ArrayList<LessonDTO> getAllLesson() throws Exception;


}
