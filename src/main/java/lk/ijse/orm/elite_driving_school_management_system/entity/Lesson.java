package lk.ijse.orm.elite_driving_school_management_system.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

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

    @Column(nullable = false, name = "date")
    private Date date;

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
        this.startTime = parseTimeFromString(startTime);
        this.endTime = parseTimeFromString(endTime);
    }

    public Lesson(long lessonId, String lessonName, String startTime, String endTime, Student student, Course course, Instructor instructor) {
        this.lessonId = lessonId;
        this.lessonName = lessonName;
        this.startTime = parseTimeFromString(startTime);
        this.endTime = parseTimeFromString(endTime);
        this.student = student;
        this.course = course;
        this.instructor = instructor;
    }

    public Lesson(String lessonName, String startTime, String endTime, Student student, Course course, Instructor instructor) {
        this.lessonName = lessonName;
        this.startTime = parseTimeFromString(startTime);
        this.endTime = parseTimeFromString(endTime);
        this.student = student;
        this.course = course;
        this.instructor = instructor;
    }

    public Lesson(String lessonName, String startTime, String endTime, java.util.Date date, Student student, Course course, Instructor instructor) {
        this.lessonName = lessonName;
        this.startTime = parseTimeFromString(startTime, date);
        this.endTime = parseTimeFromString(endTime, date);
        this.date = (Date) date;
        this.student = student;
        this.course = course;
        this.instructor = instructor;
    }

    public Lesson(long lessonId, String lessonName, String startTime, String endTime, java.util.Date date, Student student, Course course, Instructor instructor) {
        this.lessonId = lessonId;
        this.lessonName = lessonName;
        this.startTime = parseTimeFromString(startTime, date);
        this.endTime = parseTimeFromString(endTime, date);
        this.date = (Date) date;
        this.student = student;
        this.course = course;
        this.instructor = instructor;
    }

    /**
     * Parse a time string (HH:mm format) into a LocalDateTime using today's date
     */
    private LocalDateTime parseTimeFromString(String timeString) {
        if (timeString == null || timeString.isEmpty()) {
            return LocalDateTime.now();
        }
        try {
            LocalTime localTime = LocalTime.parse(timeString, DateTimeFormatter.ofPattern("H:mm"));
            return LocalDateTime.of(LocalDate.now(), localTime);
        } catch (Exception e1) {
            try {
                LocalTime localTime = LocalTime.parse(timeString, DateTimeFormatter.ofPattern("HH:mm"));
                return LocalDateTime.of(LocalDate.now(), localTime);
            } catch (Exception e2) {
                // If all parsing fails, return current time
                return LocalDateTime.now();
            }
        }
    }

    /**
     * Parse a time string (HH:mm format) into a LocalDateTime using the provided date
     */
    private LocalDateTime parseTimeFromString(String timeString, java.util.Date date) {
        if (timeString == null || timeString.isEmpty() || date == null) {
            return LocalDateTime.now();
        }
        try {
            LocalDate localDate;
            // Handle both java.sql.Date and java.util.Date
            if (date instanceof java.sql.Date) {
                localDate = ((java.sql.Date) date).toLocalDate();
            } else {
                localDate = date.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
            }
            LocalTime localTime = LocalTime.parse(timeString, DateTimeFormatter.ofPattern("H:mm"));
            return LocalDateTime.of(localDate, localTime);
        } catch (Exception e1) {
            try {
                LocalDate localDate;
                // Handle both java.sql.Date and java.util.Date
                if (date instanceof java.sql.Date) {
                    localDate = ((java.sql.Date) date).toLocalDate();
                } else {
                    localDate = date.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
                }
                LocalTime localTime = LocalTime.parse(timeString, DateTimeFormatter.ofPattern("HH:mm"));
                return LocalDateTime.of(localDate, localTime);
            } catch (Exception e2) {
                // If all parsing fails, return current time
                return LocalDateTime.now();
            }
        }
    }
}