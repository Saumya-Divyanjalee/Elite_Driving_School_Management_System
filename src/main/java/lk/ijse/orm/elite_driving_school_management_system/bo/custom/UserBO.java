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

import java.util.ArrayList;
import java.util.List;

public interface UserBO extends SuperBO {

    boolean saveUser(UserDTO userDTO) throws Exception ;

    boolean updateUser(UserDTO userDTO) throws Exception ;

    boolean deleteUser(String id) throws Exception ;

    List<UserDTO> findAllUser() throws Exception ;

    UserDTO findByUserName(String userName) throws Exception ;

    ArrayList<UserDTO> getAllUsers() throws Exception ;
}

