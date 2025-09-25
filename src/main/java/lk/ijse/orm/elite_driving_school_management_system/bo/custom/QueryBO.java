package lk.ijse.orm.elite_driving_school_management_system.bo.custom;

import lk.ijse.orm.elite_driving_school_management_system.entity.Student;

import java.util.List;

public interface QueryBO {
     List<Student> findStudentsInAllCourses();
}
