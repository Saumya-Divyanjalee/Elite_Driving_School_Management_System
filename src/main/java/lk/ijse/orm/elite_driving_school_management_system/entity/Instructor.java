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

    @OneToMany(mappedBy = "instructor",
    cascade = CascadeType.ALL)
    private List<Lesson> lessons;





}
