package lk.ijse.orm.elite_driving_school_management_system.tm;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString


public class CourseTM {
    private long courseId;
    private String courseName;
    private String timePeriod;
    private String courseFee;


    public CourseTM(long courseId, String courseName, String timePeriod) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.timePeriod = timePeriod;

    }
}
