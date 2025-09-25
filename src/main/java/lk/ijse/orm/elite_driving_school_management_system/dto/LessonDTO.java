package lk.ijse.orm.elite_driving_school_management_system.dto;

import lombok.*;

import java.time.LocalDateTime;
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



    public LessonDTO(String text, String text1, String text2, String text3) {
    }

    public LessonDTO(long lessonId, String lessonName, LocalDateTime startTime, LocalDateTime endTime, long studentID, long courseId, long instructorId) {
    }
}
