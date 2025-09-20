package lk.ijse.orm.elite_driving_school_management_system.bo.custom;

import lk.ijse.orm.elite_driving_school_management_system.bo.SuperBO;
import lk.ijse.orm.elite_driving_school_management_system.config.FactoryConfiguration;
import lk.ijse.orm.elite_driving_school_management_system.dto.LessonDTO;
import lk.ijse.orm.elite_driving_school_management_system.entity.Lesson;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.List;

public interface LessonBO extends SuperBO {
    boolean deleteLesson(Long id) throws Exception ;
    List<LessonDTO> getAllLesson() throws Exception ;
    Long getNextIdLesson() throws ClassNotFoundException, SQLException;
    boolean saveLesson(LessonDTO lessonDTO) throws Exception ;
    boolean updateLesson(LessonDTO lessonDTO) throws Exception ;
}
