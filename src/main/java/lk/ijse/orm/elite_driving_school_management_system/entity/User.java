package lk.ijse.orm.elite_driving_school_management_system.entity;

import jakarta.persistence.*;
import lk.ijse.orm.elite_driving_school_management_system.dao.custom.UserDAO;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class User implements SuperEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "user_id")
    private Long id;

    @Column(nullable = false, unique = true,name = "user_name")
    private String username;

    @Column(nullable = false,name = "mobile")
    private String mobile;

    @Column(nullable = false, unique = true,name = "email")
    private String email;

    @Column(nullable = false,name = "password")
    private String password;

    @Column(nullable = false)
    private String role;

    public User(String username, String email, String hashedPassword, String role) {
        this.username = username;
        this.email = email;
        this.password = hashedPassword;
        this.role = role;

    }

    public User(String username, String mobile, String email, String hashedPassword, String role) {
        this.username = username;
        this.mobile = mobile;
        this.email = email;
        this.password = hashedPassword;
        this.role = role;

    }
}
