package lk.ijse.orm.elite_driving_school_management_system.util;

public class CustomQueries {
    public static final String STUDENTS_IN_ALL_COURSES =
            "SELECT s FROM Student s WHERE :totalCourses = " +
                    "(SELECT COUNT(c) FROM s.courses c)";


    public static final String COURSE_STUDENT_COUNT =
            "SELECT c.courseName, COUNT(s.studentId) " +
                    "FROM Course c JOIN c.students s GROUP BY c.courseName";

}
