package lk.ijse.orm.elite_driving_school_management_system.tm;

import lombok.*;


import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class PaymentTM {
    private long paymentId;
    private double amount;
    private String description;
    private Date date;
    private String time;
    private String studentId;
    private String courseId;
    private String userId;


    public PaymentTM(long paymentId, String amount, String description, Date date, String time, String studentId, String courseId, String userId) {
        this.paymentId = paymentId;
        this.amount = Double.parseDouble(amount);
        this.description = description;
        this.date = date;
        this.time = time;
        this.studentId = studentId;
        this.courseId = courseId;
        this.userId = userId;
    }
}