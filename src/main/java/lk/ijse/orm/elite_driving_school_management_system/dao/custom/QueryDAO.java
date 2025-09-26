package lk.ijse.orm.elite_driving_school_management_system.dao.custom;

import lk.ijse.orm.elite_driving_school_management_system.dao.SuperDAO;
import lk.ijse.orm.elite_driving_school_management_system.entity.Student;

import java.util.List;

public interface QueryDAO extends SuperDAO {


    List<Student> getStudentsInAllCourses();
}
