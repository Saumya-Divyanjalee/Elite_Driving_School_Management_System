package lk.ijse.orm.elite_driving_school_management_system.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString


public class CourseDTO {
    private int courseId;
    private String lessonId;
    private String studentId;
    private String InstructorId;
    private String courseName;
    private String timePeriod;


    public CourseDTO(int courseId, String courseName, String timePeriod) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.timePeriod = timePeriod;
    }

    public CourseDTO(String courseId, String lessonId, String studentId, String instructorId, String courseName, String timePeriod) {
        this.courseId = Integer.parseInt(courseId);
        this.lessonId = lessonId;
        this.studentId = studentId;
        this.InstructorId = instructorId;
        this.courseName = courseName;
        this.timePeriod = timePeriod;
    }
}
