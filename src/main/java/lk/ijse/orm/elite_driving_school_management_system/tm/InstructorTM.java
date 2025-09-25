package lk.ijse.orm.elite_driving_school_management_system.tm;

import lombok.*;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class InstructorTM {

private long instructorId;
private String instructorName;
private String address;
private String phone;
private String email;
private String availability;



    public InstructorTM(long instructorId, String instructorName, String address, String phone, String email) {
    }
}
