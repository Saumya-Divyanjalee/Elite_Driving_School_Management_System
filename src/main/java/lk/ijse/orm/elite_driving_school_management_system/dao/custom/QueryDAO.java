package lk.ijse.orm.elite_driving_school_management_system.dao.custom;

import java.util.List;

public interface QueryDAO {
    List<Object> getStudentsInAllCourses(int totalCourses);

    List<Object[]> getCourseStudentCount();
}
