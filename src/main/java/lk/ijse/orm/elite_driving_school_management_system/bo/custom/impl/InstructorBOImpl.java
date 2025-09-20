package lk.ijse.orm.elite_driving_school_management_system.bo.custom.impl;


import lk.ijse.orm.elite_driving_school_management_system.bo.custom.InstructorBO;
import lk.ijse.orm.elite_driving_school_management_system.config.FactoryConfiguration;
import lk.ijse.orm.elite_driving_school_management_system.dao.DAOFactory;
import lk.ijse.orm.elite_driving_school_management_system.dao.DAOTypes;
import lk.ijse.orm.elite_driving_school_management_system.dao.custom.InstructorDAO;
import lk.ijse.orm.elite_driving_school_management_system.dao.custom.impl.InstructorDAOImpl;
import lk.ijse.orm.elite_driving_school_management_system.dto.InstructorDTO;
import lk.ijse.orm.elite_driving_school_management_system.entity.Instructor;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InstructorBOImpl implements InstructorBO {

    InstructorDAO instructorDAO = (InstructorDAO) DAOFactory.getInstance().getDAO(DAOTypes.INSTRUCTOR);

    @Override
    public boolean deleteInstructor(Long id) throws Exception {
         return instructorDAO.delete(id);
    }

    @Override
    public List<InstructorDTO> getAllInstructor() throws Exception {
        List<Instructor> entity = instructorDAO.getAll();
        List<InstructorDTO> instructorDTOS = new ArrayList<>();
        for(Instructor i : entity){
            instructorDTOS.add(new InstructorDTO(
                    i.getInstructorId(),
                    i.getInstructorName(),
                    i.getAddress(),
                    i.getPhone(),
                    i.getEmail()
            ));
        }
        return instructorDTOS;
    }

    @Override
    public Long getNextIdInstructor() throws SQLException, ClassNotFoundException {
        return instructorDAO.getNextId();
    }

    @Override
    public boolean saveInstructor(InstructorDTO instructorDTO) throws Exception {
        return instructorDAO.save(new Instructor(
                instructorDTO.getInstructorId(),
                instructorDTO.getInstructorName(),
                instructorDTO.getAddress(),
                instructorDTO.getPhone(),
                instructorDTO.getEmail()

                ));
    }

    @Override
    public boolean updateInstructor(InstructorDTO instructorDTO) throws Exception {
        return instructorDAO.update(new Instructor(
                instructorDTO.getInstructorId(),
                instructorDTO.getInstructorName(),
                instructorDTO.getAddress(),
                instructorDTO.getPhone(),
                instructorDTO.getEmail()
        ));
    }
}
