package lk.ijse.orm.elite_driving_school_management_system.dto;

public class CourseDTO {
    private String courseId; // Changed from long to String
    private String courseName;
    private String timePeriod;
    private String courseFee;

    public CourseDTO() {
    }

    public CourseDTO(String courseId, String courseName, String timePeriod, String courseFee) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.timePeriod = timePeriod;
        this.courseFee = courseFee;
    }

    // Getters and Setters
    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTimePeriod() {
        return timePeriod;
    }

    public void setTimePeriod(String timePeriod) {
        this.timePeriod = timePeriod;
    }

    public String getCourseFee() {
        return courseFee;
    }

    public void setCourseFee(String courseFee) {
        this.courseFee = courseFee;
    }

    @Override
    public String toString() {
        return "CourseDTO{" +
                "courseId='" + courseId + '\'' +
                ", courseName='" + courseName + '\'' +
                ", timePeriod='" + timePeriod + '\'' +
                ", courseFee='" + courseFee + '\'' +
                '}';
    }
}
