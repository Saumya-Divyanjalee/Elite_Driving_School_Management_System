package lk.ijse.orm.elite_driving_school_management_system.bo.custom.impl;

import lk.ijse.orm.elite_driving_school_management_system.bo.custom.StudentBO;
import lk.ijse.orm.elite_driving_school_management_system.dao.DAOFactory;
import lk.ijse.orm.elite_driving_school_management_system.dao.DAOTypes;
import lk.ijse.orm.elite_driving_school_management_system.dao.custom.StudentDAO;
import lk.ijse.orm.elite_driving_school_management_system.dto.StudentDTO;
import lk.ijse.orm.elite_driving_school_management_system.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentBOImpl implements StudentBO {

    StudentDAO studentDAO = (StudentDAO) DAOFactory.getInstance().getDAO(DAOTypes.STUDENT);

    @Override
    public boolean deleteStudent(Long id) throws Exception {
        return studentDAO.delete(id);

    }

    @Override
    public List<StudentDTO> getAllStudent() throws Exception {
        List<Student> entity = studentDAO.getAll();
        List<StudentDTO> studentDTO = new ArrayList<>();
        for (Student s : entity) {
            studentDTO.add(new StudentDTO(
                    s.getStudentId(),
                    s.getFirstName(),
                    s.getLastName(),
                    s.getEmail(),
                    s.getPhone(),
                    s.getAge(),
                    s.getRegDate(),
                    s.getAddress()));
        }
return  studentDTO;
    }

    @Override
    public Long getNextId() throws Exception {
        return studentDAO.getNextId();
     }

    @Override
    public boolean saveStudent(StudentDTO studentDTO) throws Exception {
         return studentDAO.save(new Student(
                 studentDTO.getStudentId(),
                 studentDTO.getFirstName(),
                 studentDTO.getLastName(),
                 studentDTO.getEmail(),
                 studentDTO.getPhone(),
                 studentDTO.getAge(),
                 studentDTO.getRegDate(),
                 studentDTO.getAddress()));
    }

    @Override
    public boolean updateStudent(StudentDTO studentDTO) throws Exception {
         return studentDAO.update(new Student(
                 studentDTO.getStudentId(),
                 studentDTO.getFirstName(),
                 studentDTO.getLastName(),
                 studentDTO.getEmail(),
                 studentDTO.getPhone(),
                 studentDTO.getAge(),
                 studentDTO.getRegDate(),
                 studentDTO.getAddress()));
    }
}
