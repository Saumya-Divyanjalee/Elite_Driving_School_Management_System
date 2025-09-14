package lk.ijse.orm.elite_driving_school_management_system.dto;

import lombok.*;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class InstructorDTO {

private String instructorId;
private String instructorName;
private String address;
private String phone;
private String email;
private String studentId;
private String lessonId;
}
