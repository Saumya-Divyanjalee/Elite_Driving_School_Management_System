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
    CourseDAO  courseDAO  = (CourseDAO)  DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.COURSE);
    UserDAO    userDAO    = (UserDAO)    DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.USER);

    //   courseId comes as "C001" format — strip the "C" before parsing
    @Override
    public boolean savePayment(PaymentDTO dto) throws Exception {
        Student student = studentDAO.findById(Long.parseLong(dto.getStudentId()));
        if (student == null) throw new Exception("Student not found: " + dto.getStudentId());

        //  Strip "C" prefix if present (e.g. "C001" → 1)
        String rawCourseId = dto.getCourseId().replace("C", "").trim();
        Course course = courseDAO.findById(Long.parseLong(rawCourseId));
        if (course == null) throw new Exception("Course not found: " + dto.getCourseId());

        Payment payment = new Payment(
                dto.getAmount(),
                dto.getDescription(),
                dto.getDate(),
                dto.getTime(),
                student,
                course,
                dto.getUserId()
        );
        return paymentDAO.save(payment);
    }

    //  same courseId fix for update
    @Override
    public boolean updatePayment(PaymentDTO dto) throws Exception {
        Student student = studentDAO.findById(Long.parseLong(dto.getStudentId()));
        if (student == null) throw new Exception("Student not found: " + dto.getStudentId());

        String rawCourseId = dto.getCourseId().replace("C", "").trim();
        Course course = courseDAO.findById(Long.parseLong(rawCourseId));
        if (course == null) throw new Exception("Course not found: " + dto.getCourseId());

        Payment payment = new Payment(
                Long.parseLong(dto.getPaymentId()),
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

    //  format courseId back to "C001" when loading into UI
    @Override
    public List<PaymentDTO> findAllPayment() throws Exception {
        return paymentDAO.findAll().stream().map(p -> new PaymentDTO(
                String.valueOf(p.getId()),
                p.getAmount(),
                p.getDescription(),
                p.getDate(),
                p.getTime(),
                String.valueOf(p.getStudent().getStudentID()),
                String.format("C%03d", p.getCourse().getCourseId()),
                p.getUserId() != null ? p.getUserId() : ""
        )).collect(Collectors.toList());
    }

    @Override
    public ArrayList<PaymentDTO> getAllPayments() throws Exception {
        ArrayList<Payment> payments = (ArrayList<Payment>) paymentDAO.findAll();
        ArrayList<PaymentDTO> dtos = new ArrayList<>();
        for (Payment p : payments) {
            dtos.add(new PaymentDTO(
                    String.valueOf(p.getId()),
                    p.getAmount(),
                    p.getDescription(),
                    p.getDate(),
                    p.getTime(),
                    String.valueOf(p.getStudent().getStudentID()),
                    String.format("C%03d", p.getCourse().getCourseId()),
                    p.getUserId() != null ? p.getUserId() : ""
            ));
        }
        return dtos;
    }

    @Override
    public List<String> getAllStudentId() throws Exception {
        List<Student> students = studentDAO.findAll();
        List<String> ids = new ArrayList<>();
        for (Student s : students) {
            ids.add(String.valueOf(s.getStudentID()));
        }
        return ids;
    }

    //   format course IDs as "C001" so they match what save/update expect
    @Override
    public List<String> getAllCourseId() throws Exception {
        List<Course> courses = courseDAO.findAll();
        List<String> ids = new ArrayList<>();
        for (Course c : courses) {
            ids.add(String.format("C%03d", c.getCourseId()));
        }
        return ids;
    }

    @Override
    public List<String> getAllUserId() throws Exception {
        List<User> users = userDAO.findAll();
        List<String> ids = new ArrayList<>();
        for (User u : users) {
            ids.add(String.valueOf(u.getId()));
        }
        return ids;
    }

    //  auto-generate next payment ID for the UI label
    @Override
    public Long getNextPaymentId() throws Exception {
        Long lastId = paymentDAO.getLastPaymentId();
        return lastId + 1;
    }
}