package lk.ijse.orm.elite_driving_school_management_system.dto;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class LessonDTO {
    private long lessonId;
    private String lessonName;
    private String startTime;
    private String endTime;
    private String instructorId;
    private String studentId;
    private String courseId;
}
