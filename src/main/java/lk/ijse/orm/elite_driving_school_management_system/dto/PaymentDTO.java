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




    public PaymentDTO(String text, String text1, java.sql.Date date, String text2, Object selectedItem, String selectedItem1, Object selectedItem2) {
    }

    public PaymentDTO(String text, String text1, String text2, java.sql.Date date, String text3, Object value, String value1, Object value2) {
    }
}