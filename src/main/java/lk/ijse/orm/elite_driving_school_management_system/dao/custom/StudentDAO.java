package lk.ijse.orm.elite_driving_school_management_system.dao.custom;

import lk.ijse.orm.elite_driving_school_management_system.config.FactoryConfiguration;
import lk.ijse.orm.elite_driving_school_management_system.dao.CrudDAO;
import lk.ijse.orm.elite_driving_school_management_system.entity.Student;
import org.hibernate.Session;

public interface StudentDAO extends CrudDAO<Student, Long> {

    Student findById(Long id) throws Exception ;

}

