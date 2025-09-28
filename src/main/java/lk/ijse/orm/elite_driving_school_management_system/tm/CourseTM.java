package lk.ijse.orm.elite_driving_school_management_system.tm;

public class CourseTM {
    private String courseId;
    private String courseName;
    private String timePeriod;
    private String courseFee;

    // Additional fields that seem to be used in the controller
    private String lessonId;
    private String instructorId;
    private String studentId;

    public CourseTM() {
    }

    public CourseTM(String courseId, String courseName, String timePeriod, String courseFee) {
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

    public String getLessonId() {
        return lessonId;
    }

    public void setLessonId(String lessonId) {
        this.lessonId = lessonId;
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

    @Override
    public String toString() {
        return "CourseTM{" +
                "courseId='" + courseId + '\'' +
                ", courseName='" + courseName + '\'' +
                ", timePeriod='" + timePeriod + '\'' +
                ", courseFee='" + courseFee + '\'' +
                ", lessonId='" + lessonId + '\'' +
                ", instructorId='" + instructorId + '\'' +
                ", studentId='" + studentId + '\'' +
                '}';
    }
}