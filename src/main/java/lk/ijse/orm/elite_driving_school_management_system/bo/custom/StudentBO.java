package lk.ijse.orm.elite_driving_school_management_system.bo.custom;

import lk.ijse.orm.elite_driving_school_management_system.bo.SuperBO;
import lk.ijse.orm.elite_driving_school_management_system.config.FactoryConfiguration;
import lk.ijse.orm.elite_driving_school_management_system.dao.custom.impl.StudentDAOImpl;
import lk.ijse.orm.elite_driving_school_management_system.dto.StudentDTO;
import lk.ijse.orm.elite_driving_school_management_system.entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface StudentBO extends SuperBO {


    boolean saveStudent(StudentDTO dto) throws Exception;
    boolean updateStudent(StudentDTO dto) throws Exception;
    boolean deleteStudent(String id) throws Exception;
    List<StudentDTO> findAll() throws Exception;
    ArrayList<StudentDTO> getAllStudent() throws SQLException, Exception;
}
