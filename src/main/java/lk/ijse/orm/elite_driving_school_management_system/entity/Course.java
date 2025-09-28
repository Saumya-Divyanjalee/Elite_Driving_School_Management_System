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

    @Column(nullable = false, name ="course_fee")
    private String courseFee;

    @OneToMany(mappedBy = "course",
            cascade = CascadeType.ALL)
    private List<Lesson>lessons;

    @ManyToMany
    @JoinTable(
            name = "associate",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private List<Student>student;

    // Constructor for new courses (without ID, let database generate it)
    public Course(String courseName, String timePeriod, String courseFee) {
        this.courseName = courseName;
        this.timePeriod = timePeriod;
        this.courseFee = courseFee;
    }

    // Constructor for updating existing courses (with ID)
    public Course(long courseId, String courseName, String timePeriod, String courseFee) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.timePeriod = timePeriod;
        this.courseFee = courseFee;
    }
}