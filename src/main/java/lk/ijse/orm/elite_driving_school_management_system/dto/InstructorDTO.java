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
private String availability;


    public InstructorDTO(String text, String text1, String text2, String text3, Object selectedItem) {
        this.instructorId = Long.parseLong(text);
        this.instructorName = text1;
        this.address = text2;
        this.phone = text3;

    }
}
