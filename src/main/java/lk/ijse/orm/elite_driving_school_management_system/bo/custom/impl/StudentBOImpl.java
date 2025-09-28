package lk.ijse.orm.elite_driving_school_management_system.bo.custom.impl;

import lk.ijse.orm.elite_driving_school_management_system.bo.custom.StudentBO;
import lk.ijse.orm.elite_driving_school_management_system.dao.DAOFactory;
import lk.ijse.orm.elite_driving_school_management_system.dao.custom.StudentDAO;
import lk.ijse.orm.elite_driving_school_management_system.dto.StudentDTO;
import lk.ijse.orm.elite_driving_school_management_system.entity.Student;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StudentBOImpl implements StudentBO {

    StudentDAO studentDAO = (StudentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.STUDENT);

    @Override
    public boolean saveStudent(StudentDTO dto) throws Exception {
        Student student = new Student(
                dto.getStudentName(),
                dto.getStudentEmail(),
                dto.getStudentPhone(),
                dto.getStudentAddress(),
                dto.getRegisterFee(),
                dto.getRegisterDate()
        );
        return studentDAO.save(student);
    }

    @Override
    public boolean updateStudent(StudentDTO dto) throws Exception {
        // Validate input
        if (dto == null || dto.getStudentID() <= 0) {
            return false;
        }

        Student student = new Student(
                dto.getStudentID(),
                dto.getStudentName(),
                dto.getStudentEmail(),
                dto.getStudentPhone(),
                dto.getStudentAddress(),
                dto.getRegisterFee(),
                dto.getRegisterDate()
        );
        return studentDAO.update(student);
    }

    @Override
    public boolean deleteStudent(String id) throws Exception {
        // Handle null or empty ID
        if (id == null || id.trim().isEmpty()) {
            return false;
        }

        try {
            Long studentId = Long.parseLong(id.trim());
            return studentDAO.delete(studentId);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<StudentDTO> findAll() throws Exception {
        try {
            return studentDAO.findAll().stream().map(student ->
                    new StudentDTO(
                            student.getStudentID(),
                            student.getStudentName(),
                            student.getStudentEmail(),
                            student.getStudentPhone(),
                            student.getStudentAddress(),
                            student.getRegisterFee(),
                            student.getRegisterDate()
                    )).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>(); // Return empty list instead of throwing exception
        }
    }

    @Override
    public ArrayList<StudentDTO> getAllStudent() throws SQLException, Exception {
        try {
            ArrayList<Student> students = (ArrayList<Student>) studentDAO.findAll();

            ArrayList<StudentDTO> studentDTOS = new ArrayList<>();
            for (Student s : students) {
                studentDTOS.add(new StudentDTO(
                        s.getStudentID(),
                        s.getStudentName(),
                        s.getStudentEmail(),
                        s.getStudentPhone(),
                        s.getStudentAddress(),
                        s.getRegisterFee(),
                        s.getRegisterDate()
                ));
            }
            return studentDTOS;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>(); // Return empty list instead of throwing exception
        }
    }
}