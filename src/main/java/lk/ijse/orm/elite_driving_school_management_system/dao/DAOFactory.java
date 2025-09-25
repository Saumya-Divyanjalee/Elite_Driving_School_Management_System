package lk.ijse.orm.elite_driving_school_management_system.dao;

import lk.ijse.orm.elite_driving_school_management_system.bo.custom.impl.InstructorBOImpl;
import lk.ijse.orm.elite_driving_school_management_system.bo.custom.impl.LessonBOImpl;
import lk.ijse.orm.elite_driving_school_management_system.dao.custom.impl.*;

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
                return (T) new InstructorDAOImpl();
            case LESSON:
                return (T) new LessonDAOImpl();
            case PAYMENT:
                return (T) new PaymentDAOImpl();
            case STUDENT:
                return (T) new CourseDAOImpl();
            case QUERY:
                return (T) new QueryDAOImpl();
            case USER:
                return (T) new UserDAOImpl();
                default:
                    return null;
        }
    }
}
