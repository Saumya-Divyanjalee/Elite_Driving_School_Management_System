package lk.ijse.orm.elite_driving_school_management_system.dao.custom.impl;

import lk.ijse.orm.elite_driving_school_management_system.dao.custom.PaymentDAO;
import lk.ijse.orm.elite_driving_school_management_system.dao.custom.QueryDAO;
import lk.ijse.orm.elite_driving_school_management_system.entity.Payment;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class PaymentDAOImpl implements PaymentDAO {
    @Override
    public boolean delete(Long aLong) throws Exception {
        return false;
    }

    @Override
    public List<Payment> getAll() throws Exception {
        return List.of();
    }

    @Override
    public Long getNextId() throws SQLException, ClassNotFoundException {
        return 0L;
    }

    @Override
    public boolean save(Payment entity) throws Exception {
        return false;
    }

    @Override
    public boolean update(Payment entity) throws Exception {
        return false;
    }
}
