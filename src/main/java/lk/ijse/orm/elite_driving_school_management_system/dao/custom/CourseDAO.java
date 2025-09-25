package lk.ijse.orm.elite_driving_school_management_system.dao.custom;

import lk.ijse.orm.elite_driving_school_management_system.dao.CrudDAO;
import lk.ijse.orm.elite_driving_school_management_system.entity.Course;

public interface CourseDAO extends CrudDAO<Course, String> {
    public Course findById(Long id) throws Exception;
}
