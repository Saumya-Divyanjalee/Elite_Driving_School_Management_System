package lk.ijse.orm.elite_driving_school_management_system.entity;

import jakarta.persistence.*;
import lombok.*;
import  java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter


@Entity
@Table(name ="course_table")
public class Course implements SuperEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,unique = true,name = "course_id")
    private long courseId;

    @Column(nullable = false, name = "course_name")
    private String courseName;

    @Column(nullable = false, name ="time_period")
    private String timePeriod;

    @OneToMany(mappedBy = "course",
            cascade = CascadeType.ALL)
    private List<Lesson>lessons;

    @ManyToMany
    @JoinTable(
            name = "associate",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private List<Student>students;

}
