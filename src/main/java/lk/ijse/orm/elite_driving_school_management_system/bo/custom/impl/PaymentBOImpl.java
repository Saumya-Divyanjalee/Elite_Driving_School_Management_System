package lk.ijse.orm.elite_driving_school_management_system.bo.custom.impl;

import lk.ijse.orm.elite_driving_school_management_system.bo.custom.PaymentBO;
import lk.ijse.orm.elite_driving_school_management_system.dao.DAOFactory;
import lk.ijse.orm.elite_driving_school_management_system.dao.DAOTypes;
import lk.ijse.orm.elite_driving_school_management_system.dao.custom.CourseDAO;
import lk.ijse.orm.elite_driving_school_management_system.dao.custom.PaymentDAO;
import lk.ijse.orm.elite_driving_school_management_system.dao.custom.StudentDAO;
import lk.ijse.orm.elite_driving_school_management_system.dao.custom.UserDAO;
import lk.ijse.orm.elite_driving_school_management_system.dto.PaymentDTO;
import lk.ijse.orm.elite_driving_school_management_system.entity.Course;
import lk.ijse.orm.elite_driving_school_management_system.entity.Payment;
import lk.ijse.orm.elite_driving_school_management_system.entity.Student;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentBOImpl implements PaymentBO {

    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getInstance().getDAO(DAOTypes.PAYMENT);
    StudentDAO studentDAO = (StudentDAO) DAOFactory.getInstance().getDAO(DAOTypes.STUDENT);
    CourseDAO  courseDAO = (CourseDAO) DAOFactory.getInstance().getDAO(DAOTypes.COURSE);
    UserDAO userDAO = (UserDAO) DAOFactory.getInstance().getDAO(DAOTypes.USER);


    @Override
    public boolean savePayment(PaymentDTO dto) {
        return false;
    }

    @Override
    public boolean updatePayment(PaymentDTO dto) throws Exception {
        return false;
    }

    @Override
    public boolean deletePayment(String id) throws Exception {
        return false;
    }

    @Override
    public List<PaymentDTO> findAllPayment() throws Exception {
        return List.of();
    }

    @Override
    public ArrayList<PaymentDTO> getAllPayments() throws Exception {
        return null;
    }

    @Override
    public List<String> getAllStudentId() throws Exception {
        return List.of();
    }

    @Override
    public List<String> getAllCourseId() throws Exception {
        return List.of();
    }

    @Override
    public List<String> getAllUserId() throws Exception {
        return List.of();
    }
}
