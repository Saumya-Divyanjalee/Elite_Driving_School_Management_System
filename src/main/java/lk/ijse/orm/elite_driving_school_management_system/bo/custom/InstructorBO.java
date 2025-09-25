package lk.ijse.orm.elite_driving_school_management_system.bo.custom;

import lk.ijse.orm.elite_driving_school_management_system.bo.SuperBO;
import lk.ijse.orm.elite_driving_school_management_system.config.FactoryConfiguration;
import lk.ijse.orm.elite_driving_school_management_system.dto.InstructorDTO;
import lk.ijse.orm.elite_driving_school_management_system.entity.Instructor;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface InstructorBO extends SuperBO {

    boolean saveInstructor(InstructorDTO dto) throws Exception;
    boolean updateInstructor(InstructorDTO dto) throws Exception ;
    boolean deleteInstructor(String s) throws Exception ;
    List<InstructorDTO> findAllInstructor() throws Exception ;
    Instructor findByIdInstructor(long id) throws Exception ;
    ArrayList<InstructorDTO> getAllInstructor() throws Exception ;

}
