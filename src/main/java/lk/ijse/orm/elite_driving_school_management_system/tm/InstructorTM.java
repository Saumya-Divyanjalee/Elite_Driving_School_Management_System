package lk.ijse.orm.elite_driving_school_management_system.tm;

import lombok.*;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class InstructorTM {

private int instructorId;
private String instructorName;
private String address;
private String phone;
private String email;
private int studentId;
private int lessonId;
}
