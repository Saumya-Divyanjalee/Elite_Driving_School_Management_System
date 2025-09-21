package lk.ijse.orm.elite_driving_school_management_system.bo.custom;

import lk.ijse.orm.elite_driving_school_management_system.bo.SuperBO;
import lk.ijse.orm.elite_driving_school_management_system.config.FactoryConfiguration;
import lk.ijse.orm.elite_driving_school_management_system.dto.PaymentDTO;
import lk.ijse.orm.elite_driving_school_management_system.entity.Payment;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.List;

public interface PaymentBO extends SuperBO {

     boolean deletePayment(Long id) throws Exception ;

     List<PaymentDTO> getAllPayment() throws Exception ;

     Long getNextIdPayment() throws SQLException, ClassNotFoundException;

     boolean savePayment(PaymentDTO paymentDTO) throws Exception ;

     boolean updatePayment(PaymentDTO paymentDTO) throws Exception ;
}
