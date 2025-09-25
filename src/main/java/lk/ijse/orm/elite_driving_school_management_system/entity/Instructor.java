package lk.ijse.orm.elite_driving_school_management_system.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter


@Entity
@Table(name = "instructor_name")
public class Instructor implements SuperEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,name = "instructor_id")
    private long instructorId;

    @Column(nullable = false,name = "instructor_name")
    private String instructorName;

    @Column(nullable = false,name = "address")
    private String address;

    @Column(nullable = false,name = "phone")
    private String phone;

    @Column(nullable = false,name = "email")
    private String email;


    @Column(nullable = false,name = "availability")
    private String availability;

    @OneToMany(mappedBy = "instructor",
    cascade = CascadeType.ALL)
    private List<Lesson> lessons;






    public Instructor(String instructorName, String email, String address, String phone, String email1, String availability) {
        this.instructorName = instructorName;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.availability = availability;

    }

    public Instructor(String instructorName, String address, String phone, String email, String availability) {
    }

    public Instructor(long instructorId, String instructorName, String address, String phone, String email, String availability) {
    }
}
