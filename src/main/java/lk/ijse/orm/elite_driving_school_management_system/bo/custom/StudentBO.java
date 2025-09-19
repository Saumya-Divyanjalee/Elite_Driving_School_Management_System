package lk.ijse.orm.elite_driving_school_management_system.bo.custom;

import lk.ijse.orm.elite_driving_school_management_system.bo.SuperBO;
import lk.ijse.orm.elite_driving_school_management_system.config.FactoryConfiguration;
import lk.ijse.orm.elite_driving_school_management_system.dao.custom.impl.StudentDAOImpl;
import lk.ijse.orm.elite_driving_school_management_system.dto.StudentDTO;
import lk.ijse.orm.elite_driving_school_management_system.entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public interface StudentBO extends SuperBO {


    boolean saveStudent(StudentDTO studentDTO) throws Exception;
    boolean updateStudent(StudentDTO studentDTO) throws Exception ;
    boolean deleteStudent(Long id) throws Exception ;
    List<StudentDTO> getAllStudent() throws Exception ;
    Long getNextId() throws Exception;
}
