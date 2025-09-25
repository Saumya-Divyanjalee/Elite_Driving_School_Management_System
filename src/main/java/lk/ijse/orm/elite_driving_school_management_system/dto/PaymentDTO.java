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


    public PaymentDTO(long id, String amount, String description, Date date, String time) {
        this.paymentId = id;
        this.amount = amount;
        this.description = description;
        this.date = date;
        this.time = time;


    }
}