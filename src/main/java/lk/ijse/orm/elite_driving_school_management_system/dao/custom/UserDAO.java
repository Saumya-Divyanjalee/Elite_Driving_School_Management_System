package lk.ijse.orm.elite_driving_school_management_system.dao.custom;

import lk.ijse.orm.elite_driving_school_management_system.config.FactoryConfiguration;
import lk.ijse.orm.elite_driving_school_management_system.dao.CrudDAO;
import lk.ijse.orm.elite_driving_school_management_system.dto.UserDTO;
import lk.ijse.orm.elite_driving_school_management_system.entity.Student;
import lk.ijse.orm.elite_driving_school_management_system.entity.User;
import org.hibernate.Session;

public interface UserDAO extends CrudDAO<User,Long> {

    User findByUserName(String userName) throws Exception;
}
