package lk.ijse.orm.elite_driving_school_management_system.bo.custom;

import lk.ijse.orm.elite_driving_school_management_system.bo.SuperBO;
import lk.ijse.orm.elite_driving_school_management_system.config.FactoryConfiguration;
import lk.ijse.orm.elite_driving_school_management_system.dto.PaymentDTO;
import lk.ijse.orm.elite_driving_school_management_system.entity.Payment;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface PaymentBO extends SuperBO {

      boolean savePayment(PaymentDTO dto) throws Exception;

      boolean updatePayment(PaymentDTO dto) throws Exception ;

      boolean deletePayment(String id) throws Exception;

      List<PaymentDTO> findAllPayment() throws Exception ;

      ArrayList<PaymentDTO> getAllPayments() throws Exception ;

      List<String> getAllStudentId() throws Exception ;
      List<String> getAllCourseId() throws Exception ;
      List<String> getAllUserId() throws Exception ;


}