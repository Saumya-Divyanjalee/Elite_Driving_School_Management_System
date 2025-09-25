package lk.ijse.orm.elite_driving_school_management_system.bo.custom;

import lk.ijse.orm.elite_driving_school_management_system.bo.SuperBO;
import lk.ijse.orm.elite_driving_school_management_system.config.FactoryConfiguration;
import lk.ijse.orm.elite_driving_school_management_system.dto.CourseDTO;
import lk.ijse.orm.elite_driving_school_management_system.entity.Course;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface CourseBO extends SuperBO {

    boolean deleteCourse(Long id) throws Exception ;

    List<CourseDTO> getAllCourse() throws Exception ;

    Long getNextIdCourse() throws SQLException, ClassNotFoundException ;

    boolean saveCourse(CourseDTO courseDTO) throws Exception ;

    boolean updateCourse(CourseDTO courseDTO) throws Exception ;

    List<CourseDTO> findAll() throws Exception;

}
