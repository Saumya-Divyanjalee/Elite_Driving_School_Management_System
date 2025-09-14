package lk.ijse.orm.elite_driving_school_management_system.dto;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class StudentDTO {
    private int studentId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String age;
    private Date dob;
    private int courseId;
    private int lessonId;
    private int paymentId;


}
