package lk.ijse.orm.elite_driving_school_management_system.dto;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class StudentDTO {
    private long studentId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String age;
    private Date regDate;
    private String address;




    public StudentDTO(long id, String firstName, String lastName, String email, String phone, String age, String regDate, String address) {
    }
}
