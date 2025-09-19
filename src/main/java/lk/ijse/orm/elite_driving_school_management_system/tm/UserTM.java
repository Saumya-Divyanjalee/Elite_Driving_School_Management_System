package lk.ijse.orm.elite_driving_school_management_system.tm;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserTM {
    private long userId;
    private String username;
    private String email;
    private String password;
    private String role;
}
