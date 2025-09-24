package lk.ijse.orm.elite_driving_school_management_system.entity;


import jakarta.persistence.*;
import lombok.*;
import java.util.List;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "student_table")
public class Student implements SuperEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private long studentId;

    @Column(nullable = false,name = "first_name")
    private String firstName;

    @Column(nullable = false,name ="last_name")
    private String lastName;

    @Column(unique = true, nullable = false, name = "address")
    private String address;

    @Column(nullable = false, name = "email")
    private String email;

    @Column(nullable = false,name = "phone")
    private String phone;

    @Column(nullable = false,name = "age")
    private String age;

    @Column(nullable = false, name = "regDate")
    private Date regDate;

    @Column(nullable = false, name = "nic")
    private String nic;

    @OneToMany(mappedBy = "student",
    cascade = CascadeType.ALL,
    fetch = FetchType.LAZY)
    private List<Lesson> lessons;

    @OneToMany(mappedBy = "student",
    cascade = CascadeType.ALL,
    fetch = FetchType.LAZY)
    private List<Payment> payments;

    @ManyToMany(mappedBy = "student",fetch = FetchType.LAZY)
    private List<Course> courses;




    public Student(long studentId, String firstName, String lastName, String email, String phone, String age, Date regDate, String address) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.age = age;
        this.regDate = regDate;
    }
}
