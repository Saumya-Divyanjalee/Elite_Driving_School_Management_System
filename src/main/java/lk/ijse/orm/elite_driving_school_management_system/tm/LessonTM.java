package lk.ijse.orm.elite_driving_school_management_system.tm;

import java.util.Date;

public class LessonTM {
    private long lessonId;
    private String lessonName;
    private String startTime;
    private String endTime;
    private Date date;
    private String instructorId;
    private String studentId;
    private String courseId;

    public LessonTM() {
    }

    public LessonTM(long lessonId, String lessonName, String startTime, String endTime, Date date, String instructorId, String studentId, String courseId) {
        this.lessonId = lessonId;
        this.lessonName = lessonName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.date = date;
        this.instructorId = instructorId;
        this.studentId = studentId;
        this.courseId = courseId;
    }

    public long getLessonId() {
        return lessonId;
    }

    public void setLessonId(long lessonId) {
        this.lessonId = lessonId;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(String instructorId) {
        this.instructorId = instructorId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    @Override
    public String toString() {
        return "LessonTM{" +
                "lessonId=" + lessonId +
                ", lessonName='" + lessonName + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", date=" + date +
                ", instructorId='" + instructorId + '\'' +
                ", studentId='" + studentId + '\'' +
                ", courseId='" + courseId + '\'' +
                '}';
    }
}