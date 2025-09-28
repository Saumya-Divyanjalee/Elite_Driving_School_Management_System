package lk.ijse.orm.elite_driving_school_management_system.dto;

import lombok.*;
import java.util.Date;


@NoArgsConstructor
@Getter
@Setter
@ToString

public class PaymentDTO {
    private String paymentId;
    private String amount;
    private String description;
    private Date date;
    private String time;
    private String studentId;
    private String courseId;
    private String userId;

    public PaymentDTO(String amount, String description, Date date, String time, String studentId, String courseId, String userId) {
        this.amount = amount;
        this.description = description;
        this.date = date;
        this.time = time;
        this.studentId = studentId;
        this.courseId = courseId;
        this.userId = userId;
    }

    public PaymentDTO(String paymentId, String amount, String description, Date date, String time, String studentId, String courseId, String userId) {
        this.paymentId = paymentId;
        this.amount = amount;
        this.description = description;
        this.date = date;
        this.time = time;
        this.studentId = studentId;
        this.courseId = courseId;
        this.userId = userId;
    }
}