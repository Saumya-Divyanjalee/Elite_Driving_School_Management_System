package lk.ijse.orm.elite_driving_school_management_system.bo.custom;

import lk.ijse.orm.elite_driving_school_management_system.bo.SuperBO;
import lk.ijse.orm.elite_driving_school_management_system.dto.InstructorDTO;

import java.sql.SQLException;
import java.util.List;

public interface InstructorBO extends SuperBO {

    boolean saveInstructor(InstructorDTO instructorDTO) throws Exception ;

    boolean updateInstructor(InstructorDTO instructorDTO) throws Exception ;

    boolean deleteInstructor(Long id) throws Exception ;

    List<InstructorDTO> getAllInstructor() throws Exception ;

    Long getNextIdInstructor() throws SQLException, ClassNotFoundException ;
}
