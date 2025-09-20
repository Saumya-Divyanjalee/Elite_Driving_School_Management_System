package lk.ijse.orm.elite_driving_school_management_system.dto;

import lombok.*;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class InstructorDTO {

private long instructorId;
private String instructorName;
private String address;
private String phone;
private String email;
private String studentId;
private String lessonId;

    public InstructorDTO(long instructorId, String instructorName, String address, String phone, String email) {
    }

    public InstructorDTO(String text, String text1, String text2, String text3, String text4) {
    }
}
