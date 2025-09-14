package lk.ijse.orm.elite_driving_school_management_system.tm;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class StudentTM {
    private String studentId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String age;
    private Date dob;
    private String courseId;
    private String lessonId;
    private String paymentId;


}
