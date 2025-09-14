package lk.ijse.orm.elite_driving_school_management_system.dto;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class LessonDTO {
    private int lessonId;
    private String lessonName;
    private Date startTime;
    private Date endTime;
    private int instructorId;
    private int studentId;
    private int courseId;
}
