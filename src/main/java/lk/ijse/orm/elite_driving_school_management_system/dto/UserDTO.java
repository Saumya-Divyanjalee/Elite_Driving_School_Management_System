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
    private String email;
    private String password;
    private String role;
}
