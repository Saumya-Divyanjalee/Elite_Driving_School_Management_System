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
    private Date date;
    private String instructorId;
    private String studentId;
    private String courseId;

    // Constructor for creating new lessons with date as String
    public LessonDTO(String lessonName, String startTime, String endTime, String dateStr, String instructorId, String courseId, String studentId) {
        this.lessonName = lessonName;
        this.startTime = startTime;
        this.endTime = endTime;
        // Parse the date string properly
        try {
            this.date = java.sql.Date.valueOf(dateStr);
        } catch (Exception e) {
            // Handle invalid date format
            this.date = new java.sql.Date(System.currentTimeMillis());
        }
        this.instructorId = instructorId;
        this.courseId = courseId;
        this.studentId = studentId;
    }

    // Constructor for updating existing lessons with date as String
    public LessonDTO(long lessonId, String lessonName, String startTime, String endTime, String dateStr, String instructorId, String courseId, String studentId) {
        this.lessonId = lessonId;
        this.lessonName = lessonName;
        this.startTime = startTime;
        this.endTime = endTime;
        // Parse the date string properly
        try {
            this.date = java.sql.Date.valueOf(dateStr);
        } catch (Exception e) {
            // Handle invalid date format
            this.date = new java.sql.Date(System.currentTimeMillis());
        }
        this.instructorId = instructorId;
        this.courseId = courseId;
        this.studentId = studentId;
    }

    public LessonDTO(String lessonName, String startTime, String endTime, String instructorId) {
        this.lessonName = lessonName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.instructorId = instructorId;
    }

    public LessonDTO(long lessonId, String lessonName, LocalDateTime startTime, LocalDateTime endTime, long studentId, long courseId, long instructorId) {
        this.lessonId = lessonId;
        this.lessonName = lessonName;
        this.startTime = startTime.toString();
        this.endTime = endTime.toString();
        this.studentId = String.valueOf(studentId);
        this.courseId = String.valueOf(courseId);
        this.instructorId = String.valueOf(instructorId);
    }

    public LessonDTO(long lessonId, String lessonName, LocalDateTime startTime, LocalDateTime endTime, java.sql.Date date, long studentID, long courseId, long instructorId) {
        this.lessonId = lessonId;
        this.lessonName = lessonName;
        this.startTime = startTime.toString();
        this.endTime = endTime.toString();
        this.date = date;
        this.studentId = String.valueOf(studentID);
        this.courseId = String.valueOf(courseId);
        this.instructorId = String.valueOf(instructorId);
    }

    public LessonDTO(long l, String text, String text1, String text2, String string, Object selectedItem, Object selectedItem1, Object selectedItem2) {
        this.lessonId = l;
        this.lessonName = text;
        this.startTime = text1;
        this.endTime = text2;
        // Parse the date string properly
        try {
            this.date = java.sql.Date.valueOf(string);
        } catch (Exception e) {
            // Handle invalid date format
            this.date = new java.sql.Date(System.currentTimeMillis());
        }
        this.instructorId = selectedItem != null ? selectedItem.toString() : null;
        this.courseId = selectedItem1 != null ? selectedItem1.toString() : null;
        this.studentId = selectedItem2 != null ? selectedItem2.toString() : null;
    }

    public LessonDTO(String text, String text1, String text2, String string, Object selectedItem, Object selectedItem1, Object selectedItem2) {
        this.lessonName = text;
        this.startTime = text1;
        this.endTime = text2;
        // Parse the date string properly
        try {
            this.date = java.sql.Date.valueOf(string);
        } catch (Exception e) {
            // Handle invalid date format
            this.date = new java.sql.Date(System.currentTimeMillis());
        }
        this.instructorId = selectedItem != null ? selectedItem.toString() : null;
        this.courseId = selectedItem1 != null ? selectedItem1.toString() : null;
        this.studentId = selectedItem2 != null ? selectedItem2.toString() : null;
    }
}