package lk.ijse.orm.elite_driving_school_management_system.tm;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class StudentTM {
    private long studentId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String age;
    private LocalDate regDate;
    private String address;


    public StudentTM(long studentId, String firstName, String lastName, String email, String phone, String age, Date regDate, String address) {
    }
}
