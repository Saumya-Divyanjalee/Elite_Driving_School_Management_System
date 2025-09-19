package lk.ijse.orm.elite_driving_school_management_system.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString


public class CourseDTO {
    private long courseId;
    private String courseName;
    private String timePeriod;
    private String lessonId;
    private String studentId;
    private String InstructorId;



}
