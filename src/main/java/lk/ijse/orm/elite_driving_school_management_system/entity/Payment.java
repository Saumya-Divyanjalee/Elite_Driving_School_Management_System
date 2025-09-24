package lk.ijse.orm.elite_driving_school_management_system.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "payment_table")
public class Payment implements SuperEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name ="payment_id")
    private long id;

    @Column(nullable = false,name ="amount")
    private double amount;

    @Column(nullable = false,name = "description")
    private String description;

    @Column(nullable = false,name = "time")
    private String time;

    @Column(nullable = false,name = "date")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;


    public Payment(long paymentId, double amount, String description, Date date, String time) {
        this.id = paymentId;
        this.amount = amount;
        this.description = description;
        this.date = date;
        this.time = time;
    }
}
