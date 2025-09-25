package lk.ijse.orm.elite_driving_school_management_system.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Entity
@Table(name = "students")
public class Student implements SuperEntity {
    @Id
    @Column(name = "student_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long studentID;

    @Column(nullable = false)
    private String studentName;

    @Column(nullable = false, unique = true)
    private String studentEmail;

    @Column(length = 15)
    private String studentPhone;

    @Column(nullable = false)
    private String studentAddress;

    @Column(name = "registerFee")
    private String registerFee;

    @Column(nullable = false)
    private Date registerDate;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Lesson> lessons = new java.util.ArrayList<>();

    @OneToMany(mappedBy = "student",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Payment> payments = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "student_course", // join table
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<Course> courses = new ArrayList<>();


    public Student(long studentID, String studentName, String studentEmail, String studentPhone, String studentAddress, String registerFee, java.util.Date registerDate) {
        this.studentID = studentID;
        this.studentName = studentName;
        this.studentEmail = studentEmail;
        this.studentPhone = studentPhone;
        this.studentAddress = studentAddress;
        this.registerFee = registerFee;
        this.registerDate = (Date) registerDate;

    }

    public Student(String studentName, String studentEmail, String studentPhone, String studentAddress, String registerFee, java.util.Date registerDate) {
        this.studentName = studentName;
        this.studentEmail = studentEmail;
        this.studentPhone = studentPhone;
        this.studentAddress = studentAddress;
        this.registerFee = registerFee;
        this.registerDate = (Date) registerDate;

    }
}