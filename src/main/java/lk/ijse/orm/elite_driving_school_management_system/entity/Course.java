package lk.ijse.orm.elite_driving_school_management_system.entity;

import jakarta.persistence.*;
import lombok.*;
import  java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

@Entity
@Table(name ="course_table")
public class Course implements SuperEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String courseId;
    private String courseName;
    private String timePeriod;

    @OneToMany(
            mappedBy = "course",
            cascade = CascadeType.ALL
    )
    private List<Lesson>lesson;


}
