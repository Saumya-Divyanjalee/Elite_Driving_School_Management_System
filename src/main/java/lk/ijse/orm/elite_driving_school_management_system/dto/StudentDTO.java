package lk.ijse.orm.elite_driving_school_management_system.dto;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class StudentDTO {
    private long studentID;
    private String studentName;
    private String studentEmail;
    private String studentPhone;
    private String studentAddress;
    private String registerFee;
    private Date registerDate;


    public StudentDTO(String text, String text1, String text2, String text3, String text4, java.sql.Date date) {
        this.studentID = Long.parseLong(text);
        this.studentName = text1;
        this.studentEmail = text2;
        this.studentPhone = text3;
        this.studentAddress = text4;
        this.registerFee = text4;
        this.registerDate = date;

    }
}