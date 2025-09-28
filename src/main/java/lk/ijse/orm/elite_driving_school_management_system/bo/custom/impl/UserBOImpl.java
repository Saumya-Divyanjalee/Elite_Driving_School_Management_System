package lk.ijse.orm.elite_driving_school_management_system.bo.custom.impl;

import lk.ijse.orm.elite_driving_school_management_system.bo.custom.UserBO;
import lk.ijse.orm.elite_driving_school_management_system.dao.DAOFactory;
import lk.ijse.orm.elite_driving_school_management_system.dao.custom.UserDAO;
import lk.ijse.orm.elite_driving_school_management_system.dto.UserDTO;
import lk.ijse.orm.elite_driving_school_management_system.entity.User;
import lk.ijse.orm.elite_driving_school_management_system.util.PasswordEncryptor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserBOImpl implements UserBO {

    UserDAO userDAO = (UserDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.USER);

    @Override
    public boolean saveUser(UserDTO userDTO) throws Exception {
        // Hash the password before saving
        String hashedPassword = PasswordEncryptor.hashPassword(userDTO.getPassword());

        User user = new User(
                userDTO.getUsername(),
                userDTO.getMobile(),
                userDTO.getEmail(),
                hashedPassword,
                userDTO.getRole()
        );
        return userDAO.save(user);
    }

    @Override
    public boolean updateUser(UserDTO userDTO) throws Exception {
        // Hash the password before updating
        String hashedPassword = PasswordEncryptor.hashPassword(userDTO.getPassword());

        User user = new User(
                userDTO.getUserId(),
                userDTO.getUsername(),
                userDTO.getMobile(),
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
                        user.getMobile(),
                        user.getEmail(),
                        user.getPassword(),
                        user.getRole()
                )).collect(Collectors.toList());
    }

    @Override
    public UserDTO findByUserName(String userName) throws Exception {
        User user = userDAO.findByUserName(userName);
        if (user != null) {
            return new UserDTO(
                    user.getId(),
                    user.getUsername(),
                    user.getMobile(),
                    user.getEmail(),
                    user.getPassword(),
                    user.getRole()
            );
        }
        return null;
    }

    @Override
    public ArrayList<UserDTO> getAllUsers() throws Exception {
        ArrayList<User> users = (ArrayList<User>) userDAO.findAll();
        ArrayList<UserDTO> userDTOS = new ArrayList<>();
        for (User user : users) {
            userDTOS.add(new UserDTO(
                    user.getId(),
                    user.getUsername(),
                    user.getMobile(),
                    user.getEmail(),
                    user.getPassword(),
                    user.getRole()
            ));
        }
        return userDTOS;
    }
}