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
    private String courseFee;




    public CourseDTO(String text, String text1, String text2, String text3) {
        this.courseId = Long.parseLong(text);
        this.courseName = text1;
        this.courseFee = text2;
        this.timePeriod = text3;
        this.courseFee = text3;
    }
}
