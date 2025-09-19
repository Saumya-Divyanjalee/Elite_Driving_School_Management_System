package lk.ijse.orm.elite_driving_school_management_system.tm;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString


public class CourseTM {
    private long courseId;
    private String lessonId;
    private String studentId;
    private String InstructorId;
    private String courseName;
    private String timePeriod;


//    public CourseTM(int courseId, String lessonId, String studentId, String instructorId, String courseName, String timePeriod) {
//        this.courseId = Integer.parseInt(String.valueOf(courseId));
//        this.lessonId = lessonId;
//        this.studentId = studentId;
//        this.InstructorId = instructorId;
//        this.courseName = courseName;
//        this.timePeriod = timePeriod;
//    }
}
