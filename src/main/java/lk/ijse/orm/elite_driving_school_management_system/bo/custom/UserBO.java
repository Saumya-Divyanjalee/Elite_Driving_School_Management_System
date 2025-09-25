package lk.ijse.orm.elite_driving_school_management_system.bo.custom;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import lk.ijse.orm.elite_driving_school_management_system.bo.SuperBO;
import lk.ijse.orm.elite_driving_school_management_system.config.FactoryConfiguration;
import lk.ijse.orm.elite_driving_school_management_system.dto.UserDTO;
import lk.ijse.orm.elite_driving_school_management_system.entity.Student;
import lk.ijse.orm.elite_driving_school_management_system.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public interface UserBO extends SuperBO {

      boolean save(User entity) throws Exception ;

      boolean update(User entity) throws Exception ;

      boolean delete(Long id) throws Exception ;

     List<User> findAll() throws Exception ;

     User findByUserName(String userName) throws Exception ;
}

