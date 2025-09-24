package lk.ijse.orm.elite_driving_school_management_system.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

import static java.time.LocalDateTime.parse;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter


@Entity
@Table(name = "lesson_table")
public class Lesson implements SuperEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,name = "lesson_id")
    private long lessonId;

    @Column(nullable = false,name = "lesson_name")
    private String lessonName;

    @Column(nullable = false,name = "start_time")
    private LocalDateTime startTime;

    @Column(nullable = false, name = "end_time")
    private LocalDateTime endTime;

    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "course_id")
    private Course course;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;


    public Lesson(long lessonId, String lessonName, String startTime, String endTime) {
        this.lessonId = lessonId;
        this.lessonName = lessonName;
        this.startTime = parse(startTime);
        this.endTime = parse(endTime);

    }
}
