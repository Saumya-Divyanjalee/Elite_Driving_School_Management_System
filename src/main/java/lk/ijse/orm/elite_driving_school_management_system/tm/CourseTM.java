package lk.ijse.orm.elite_driving_school_management_system.tm;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString


public class CourseTM {
    private int courseId;
    private int lessonId;
    private int studentId;
    private String InstructorId;
    private String courseName;
    private String timePeriod;



}
