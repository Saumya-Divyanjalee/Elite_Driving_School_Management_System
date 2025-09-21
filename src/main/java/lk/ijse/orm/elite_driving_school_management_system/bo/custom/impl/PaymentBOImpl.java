package lk.ijse.orm.elite_driving_school_management_system.bo.custom.impl;

import lk.ijse.orm.elite_driving_school_management_system.bo.custom.PaymentBO;
import lk.ijse.orm.elite_driving_school_management_system.dao.DAOFactory;
import lk.ijse.orm.elite_driving_school_management_system.dao.DAOTypes;
import lk.ijse.orm.elite_driving_school_management_system.dao.custom.PaymentDAO;
import lk.ijse.orm.elite_driving_school_management_system.dto.PaymentDTO;
import lk.ijse.orm.elite_driving_school_management_system.entity.Payment;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentBOImpl implements PaymentBO {

    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getInstance().getDAO(DAOTypes.PAYMENT);

    @Override
    public boolean deletePayment(Long id) throws Exception {
        return paymentDAO.delete(id);
    }

    @Override
    public List<PaymentDTO> getAllPayment() throws Exception {
        List<Payment> entity = paymentDAO.getAll();
        List<PaymentDTO> dto = new ArrayList<>();
        for (Payment p : entity) {
            dto.add(new PaymentDTO(
                    p.getId(),
                    p.getAmount(),
                    p.getDescription(),
                    p.getDate(),
                    p.getTime()
            ));
        }
        return dto;
    }

    @Override
    public Long getNextIdPayment() throws SQLException, ClassNotFoundException {
        return paymentDAO.getNextId();
    }

    @Override
    public boolean savePayment(PaymentDTO paymentDTO) throws Exception {
        return paymentDAO.save(new Payment(
                paymentDTO.getPaymentId(),
                paymentDTO.getAmount(),
                paymentDTO.getDescription(),
                paymentDTO.getDate(),
                paymentDTO.getTime()
        ));
    }

    @Override
    public boolean updatePayment(PaymentDTO paymentDTO) throws Exception {
        return paymentDAO.update(new Payment(
                paymentDTO.getPaymentId(),
                paymentDTO.getAmount(),
                paymentDTO.getDescription(),
                paymentDTO.getDate(),
                paymentDTO.getTime()
        ));
    }
}
