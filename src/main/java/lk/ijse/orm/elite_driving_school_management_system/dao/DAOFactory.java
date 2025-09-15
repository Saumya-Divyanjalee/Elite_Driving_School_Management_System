package lk.ijse.orm.elite_driving_school_management_system.dao;

import lk.ijse.orm.elite_driving_school_management_system.dao.custom.impl.CourseDAOImpl;

public class DAOFactory  {
    private static DAOFactory daoFactory;
    private DAOFactory() {}
    public static DAOFactory getInstance() {
       return (daoFactory == null) ? new DAOFactory() : daoFactory;
    }


    public <T extends SuperDAO>T getDAO(DAOTypes daoType) {
        switch (daoType) {
            case COURSE:
                return (T) new CourseDAOImpl();
                case INSTRUCTOR:
        }
    }
}
