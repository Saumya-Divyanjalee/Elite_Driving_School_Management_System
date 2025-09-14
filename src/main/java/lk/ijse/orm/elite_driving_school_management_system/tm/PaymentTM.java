package lk.ijse.orm.elite_driving_school_management_system.tm;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class PaymentTM {
    private int paymentId;
    private double amount;
    private String description;
    private LocalDate date;
    private LocalTime time;
    private int studentId;
    private int courseId;
    private int userId;


}
