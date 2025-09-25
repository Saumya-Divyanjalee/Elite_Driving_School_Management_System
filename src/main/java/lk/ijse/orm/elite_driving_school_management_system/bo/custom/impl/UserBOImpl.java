package lk.ijse.orm.elite_driving_school_management_system.bo.custom.impl;

import lk.ijse.orm.elite_driving_school_management_system.bo.custom.UserBO;
import lk.ijse.orm.elite_driving_school_management_system.dao.DAOFactory;
import lk.ijse.orm.elite_driving_school_management_system.dao.DAOTypes;
import lk.ijse.orm.elite_driving_school_management_system.dao.custom.UserDAO;
import lk.ijse.orm.elite_driving_school_management_system.dto.UserDTO;
import lk.ijse.orm.elite_driving_school_management_system.entity.Student;
import lk.ijse.orm.elite_driving_school_management_system.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserBOImpl implements UserBO {

 UserDAO userDAO = (UserDAO) DAOFactory.getInstance().getDAO(DAOTypes.USER);
    @Override
    public boolean saveUser(UserDTO userDTO) throws Exception {
        String hashedPassword = org.mindrot.jbcrypt.BCrypt.hashpw(userDTO.getPassword(), org.mindrot.jbcrypt.BCrypt.gensalt());

        User user = new User(
                userDTO.getUsername(),
                userDTO.getEmail(),
                hashedPassword,
                userDTO.getRole()
        );
        return userDAO.save(user);
    }

    @Override
    public boolean updateUser(UserDTO userDTO) throws Exception {
        String hashedPassword = org.mindrot.jbcrypt.BCrypt.hashpw(userDTO.getPassword(), org.mindrot.jbcrypt.BCrypt.gensalt());

        User user = new User(
                userDTO.getUserId(),
                userDTO.getUsername(),
                userDTO.getEmail(),
                hashedPassword,
                userDTO.getRole()
        );
        return userDAO.update(user);
    }

    @Override
    public boolean deleteUser(String id) throws Exception {
        return userDAO.delete(Long.valueOf(id));
    }



    @Override
    public List<UserDTO> findAllUser() throws Exception {
         return userDAO.findAll().stream().map(user ->
                 new UserDTO(
                         user.getId(),
                         user.getUsername(),
                         user.getEmail(),
                         user.getPassword(),
                         user.getRole()
                 )).collect(Collectors.toList());
    }


    @Override
    public UserDTO findByUserName(String userName) throws Exception {
        User user =  userDAO.findByUserName(userName);
                if (user != null){
                    return new UserDTO
                            (user.getId(),
                            user.getUsername(),
                            user.getEmail(),
                            user.getPassword(),
                            user.getRole());


        }
                return null;


    }
    @Override
    public ArrayList<UserDTO> getAllUsers() throws Exception {
        ArrayList<User> users = (ArrayList<User>) userDAO.findAll();
        ArrayList<UserDTO> userDTOS = new ArrayList<>();
        for (User user : users) {
            userDTOS.add(new UserDTO(user.getId(), user.getUsername(), user.getEmail(), user.getPassword(), user.getRole()));
        }
        return userDTOS;
    }

}
