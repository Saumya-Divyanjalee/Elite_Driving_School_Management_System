package lk.ijse.orm.elite_driving_school_management_system.dto;

import lombok.*;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class UserDTO {
    private long userId;
    private String username;
    private String mobile;
    private String email;
    private String password;
    private String role;

    public UserDTO(String username, String mobile, String email, String password, String role) {
        this.username = username;
        this.mobile = mobile;
        this.email = email;
        this.password = password;
        this.role = role;
    }
}
