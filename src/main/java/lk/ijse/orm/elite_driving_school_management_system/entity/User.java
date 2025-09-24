package lk.ijse.orm.elite_driving_school_management_system.entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "user_id")
    private Long id;

    @Column(nullable = false, unique = true,name = "user_name")
    private String username;

    @Column(nullable = false, unique = true,name = "email")
    private String email;

    @Column(nullable = false,name = "password")
    private String password;

    @Column(nullable = false)
    private String role;
}
