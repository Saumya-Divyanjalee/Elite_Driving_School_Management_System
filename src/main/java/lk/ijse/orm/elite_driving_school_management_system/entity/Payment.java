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
    private String amount;

    @Column(nullable = false,name = "description")
    private String description;

    @Column(nullable = false,name = "time")
    private String time;

    @Column(nullable = false,name = "date")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "student_id",nullable = false)
    private Student student;

    @OneToOne
    @JoinColumn(name ="course_id",unique = true,nullable = false)
    private Course course;


    public Payment(long paymentId, String amount, String description, Date date, String time) {
        this.id = paymentId;
        this.amount = amount;
        this.description = description;
        this.date = date;
        this.time = time;
    }

    public Payment(String amount, String description, Date date, String time, Student student, Course course) {
        this.amount = amount;
        this.description = description;
        this.date = date;
        this.student = student;
        this.course = course;

    }

    public Payment(long paymentId, String amount, String description, Date date, String time, Student student, Course course) {
        this.id = paymentId;
        this.amount = amount;
        this.description = description;
        this.date = date;
        this.student = student;
        this.course = course;


    }
}