package lk.ijse.orm.elite_driving_school_management_system.dto;

import lombok.*;
import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class PaymentDTO {
    private long paymentId;
    private String amount;
    private String description;
    private Date date;
    private String time;
    private String studentId;
    private String courseId;
    private String userId;





    public PaymentDTO(String amount, String description, java.sql.Date date, String time, String studentId, String courseId, String userId) {
        this.amount = amount;
        this.description = description;
        this.date = date;
        this.time = time;
        this.studentId = studentId;
        this.courseId = courseId;
        this.userId = userId;
    }


    public PaymentDTO(String amount, String description, String time, java.sql.Date date, String studentId, Object courseIdObj, String userId, Object extra) {
        this.amount = amount;
        this.description = description;
        this.time = time;
        this.date = date;
        this.studentId = studentId;
        this.courseId = String.valueOf(courseIdObj);
        this.userId = userId;
    }


    public PaymentDTO(long paymentId, String amount, String description, Date date, String time, long studentId, long courseId) {
        this.paymentId = paymentId;
        this.amount = amount;
        this.description = description;
        this.date = date;
        this.time = time;
        this.studentId = String.valueOf(studentId);
        this.courseId = String.valueOf(courseId);
    }
}