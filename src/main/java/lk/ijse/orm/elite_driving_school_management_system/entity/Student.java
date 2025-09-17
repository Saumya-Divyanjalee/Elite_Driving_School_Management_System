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
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private long studentId;

    @Column(nullable = false,name = "first_name")
    private String firstName;

    @Column(nullable = false,name ="last_name")
    private String lastName;

    @Column(nullable = false, name = "address")
    private String address;

    @Column(nullable = false, name = "email")
    private String email;

    @Column(nullable = false,name = "phone")
    private String phone;

    @Column(nullable = false,name = "age")
    private String age;

    @Column(nullable = false, name = "dob")
    private Date dob;

    @OneToMany(mappedBy = "students",
    cascade = CascadeType.ALL)
    private List<Lesson> lessons;

    @OneToMany(mappedBy = "student",
    cascade = CascadeType.ALL)
    private List<Payment> payments;

    @ManyToMany(mappedBy = "students")
    private List<Course> courses;

}
