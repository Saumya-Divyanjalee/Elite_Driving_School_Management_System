package lk.ijse.orm.elite_driving_school_management_system.dao.custom;

import lk.ijse.orm.elite_driving_school_management_system.config.FactoryConfiguration;
import lk.ijse.orm.elite_driving_school_management_system.dao.CrudDAO;
import lk.ijse.orm.elite_driving_school_management_system.entity.Instructor;
import org.hibernate.Session;

public interface InstructorDAO extends CrudDAO<Instructor, String> {
    Instructor findById(long id) throws Exception ;

}
