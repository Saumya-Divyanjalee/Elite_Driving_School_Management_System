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
    private long studentID;
    private String studentName;
    private String studentEmail;
    private String studentPhone;
    private String studentAddress;
    private String registerFee;
    private Date registerDate;




}