package lk.ijse.orm.elite_driving_school_management_system.bo.custom.impl;

import lk.ijse.orm.elite_driving_school_management_system.bo.custom.InstructorBO;
import lk.ijse.orm.elite_driving_school_management_system.dao.DAOFactory;
import lk.ijse.orm.elite_driving_school_management_system.dao.custom.InstructorDAO;
import lk.ijse.orm.elite_driving_school_management_system.dto.InstructorDTO;
import lk.ijse.orm.elite_driving_school_management_system.entity.Instructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InstructorBOImpl implements InstructorBO {

    private final InstructorDAO instructorDAO = (InstructorDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.INSTRUCTOR);

    @Override
    public boolean saveInstructor(InstructorDTO dto) throws Exception {
        Instructor instructor = new Instructor(
                dto.getInstructorName(),
                dto.getAddress(),
                dto.getPhone(),
                dto.getEmail(),
                dto.getAvailability()
        );
        return instructorDAO.save(instructor);
    }

    @Override
    public boolean updateInstructor(InstructorDTO dto) throws Exception {
        Instructor instructor = new Instructor(
                dto.getInstructorId(),
                dto.getInstructorName(),
                dto.getAddress(),
                dto.getPhone(),
                dto.getEmail(),
                dto.getAvailability()
        );
        return instructorDAO.update(instructor);
    }

    @Override
    public boolean deleteInstructor(String id) throws Exception {
        return instructorDAO.delete(id);
    }

    @Override
    public List<InstructorDTO> findAllInstructor() throws Exception {
        return instructorDAO.findAll().stream().map(instructor ->
                new InstructorDTO(
                        instructor.getInstructorId(),
                        instructor.getInstructorName(),
                        instructor.getAddress(),
                        instructor.getPhone(),
                        instructor.getEmail(),
                        instructor.getAvailability()
                )).collect(Collectors.toList());
    }

    @Override
    public Instructor findByIdInstructor(long id) throws Exception {
        return instructorDAO.findById(id);
    }

    public ArrayList<InstructorDTO> getAllInstructor() throws Exception {
        ArrayList<Instructor> instructors = (ArrayList<Instructor>) instructorDAO.findAll();
        ArrayList<InstructorDTO> instructorDTOS = new ArrayList<>();
        for (Instructor i : instructors) {
            instructorDTOS.add(new InstructorDTO(
                    i.getInstructorId(),
                    i.getInstructorName(),
                    i.getAddress(),
                    i.getPhone(),
                    i.getEmail(),
                    i.getAvailability()
            ));
        }
        return instructorDTOS;
    }
}
