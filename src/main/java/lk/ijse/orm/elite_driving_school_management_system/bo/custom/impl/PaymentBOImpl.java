package lk.ijse.orm.elite_driving_school_management_system.bo.custom.impl;

import lk.ijse.orm.elite_driving_school_management_system.bo.custom.PaymentBO;
import lk.ijse.orm.elite_driving_school_management_system.dao.DAOFactory;
import lk.ijse.orm.elite_driving_school_management_system.dao.custom.CourseDAO;
import lk.ijse.orm.elite_driving_school_management_system.dao.custom.PaymentDAO;
import lk.ijse.orm.elite_driving_school_management_system.dao.custom.StudentDAO;
import lk.ijse.orm.elite_driving_school_management_system.dao.custom.UserDAO;
import lk.ijse.orm.elite_driving_school_management_system.dto.PaymentDTO;
import lk.ijse.orm.elite_driving_school_management_system.entity.Course;
import lk.ijse.orm.elite_driving_school_management_system.entity.Payment;
import lk.ijse.orm.elite_driving_school_management_system.entity.Student;
import lk.ijse.orm.elite_driving_school_management_system.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PaymentBOImpl implements PaymentBO {

    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.PAYMENT);
    StudentDAO studentDAO = (StudentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.STUDENT);
    CourseDAO  courseDAO = (CourseDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.COURSE);
    UserDAO userDAO = (UserDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.USER);


    @Override
    public boolean savePayment(PaymentDTO dto) throws Exception {
        Student student = studentDAO.findById(Long.valueOf(dto.getStudentId()));
        if (student == null) {
            throw new Exception("Student not found with ID: " + dto.getStudentId());
        }
        Course course = courseDAO.findById(Long.valueOf(dto.getCourseId()));
        if (course == null) {
            throw new Exception("Course not found with ID: " + dto.getCourseId());
        }
        Payment payment = new Payment(
                dto.getAmount(),
                dto.getDescription(),
                dto.getDate(),
                dto.getTime(),
                student,
                course,
                dto.getUserId() // Add userId
        );
        return paymentDAO.save(payment);
    }

    @Override
    public boolean updatePayment(PaymentDTO dto) throws Exception {
        Student student = studentDAO.findById(Long.valueOf(dto.getStudentId()));
        if (student == null) {
            throw new Exception("Student not found with ID: " + dto.getStudentId());
        }
        Course course = courseDAO.findById(Long.valueOf(dto.getCourseId()));
        if (course == null) {
            throw new Exception("Course not found with ID: " + dto.getCourseId());
        }
        Payment payment = new Payment(
                Long.valueOf(dto.getPaymentId()),
                dto.getAmount(),
                dto.getDescription(),
                dto.getDate(),
                dto.getTime(),
                student,
                course,
                dto.getUserId()
        );
        return paymentDAO.update(payment);

    }

    @Override
    public boolean deletePayment(String id) throws Exception {
        return paymentDAO.delete(id);
    }

    @Override
    public List<PaymentDTO> findAllPayment() throws Exception {
        return paymentDAO.findAll().stream().map(payment -> {
            PaymentDTO dto = new PaymentDTO(
                    String.valueOf(payment.getId()),
                    payment.getAmount(),
                    payment.getDescription(),
                    payment.getDate(),
                    payment.getTime(),
                    String.valueOf(payment.getStudent().getStudentID()),
                    String.valueOf(payment.getCourse().getCourseId()),
                    payment.getUserId() != null ? payment.getUserId() : "" // Add userId
            );
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public ArrayList<PaymentDTO> getAllPayments() throws Exception {
        ArrayList<Payment> payments = (ArrayList<Payment>) paymentDAO.findAll();
        ArrayList<PaymentDTO> paymentDTOS = new ArrayList<>();
        for (Payment p : payments) {
            PaymentDTO dto = new PaymentDTO(
                    String.valueOf(p.getId()),
                    p.getAmount(),
                    p.getDescription(),
                    p.getDate(),
                    p.getTime(),
                    String.valueOf(p.getStudent().getStudentID()),
                    String.valueOf(p.getCourse().getCourseId()),
                    p.getUserId() != null ? p.getUserId() : "" // Add userId
            );
            paymentDTOS.add(dto);
        }
        return paymentDTOS;
    }

    @Override
    public List<String> getAllStudentId() throws Exception {
        List<Student> students = studentDAO.findAll();
        List<String> studentIds = new ArrayList<>();
        for (Student s : students) {
            studentIds.add(String.valueOf(s.getStudentID()));
        }
        return studentIds;
    }

    @Override
    public List<String> getAllCourseId() throws Exception {
        List<Course> courses = courseDAO.findAll();
        List<String> courseIds = new ArrayList<>();
        for (Course c : courses) {
            courseIds.add(String.valueOf(c.getCourseId()));

        }
        return courseIds;
    }

    @Override
    public List<String> getAllUserId() throws Exception {
        List<User> users = userDAO.findAll();
        List<String> userIds = new ArrayList<>();
        for (User u : users) {
            userIds.add(String.valueOf(u.getId())); // Changed from getUserId() to getId()
        }
        return userIds;
    }
}