package lk.ijse.orm.elite_driving_school_management_system.bo.custom.impl;

import lk.ijse.orm.elite_driving_school_management_system.bo.custom.QueryBO;
import lk.ijse.orm.elite_driving_school_management_system.dao.DAOFactory;
import lk.ijse.orm.elite_driving_school_management_system.dao.custom.QueryDAO;
import lk.ijse.orm.elite_driving_school_management_system.entity.Student;

import java.util.List;

public class QueryBOImpl implements QueryBO {

    QueryDAO queryDAO= (QueryDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.QUERY);

    @Override
    public List<Student> findStudentsInAllCourses() {
        return queryDAO.getStudentsInAllCourses();
    }
}
