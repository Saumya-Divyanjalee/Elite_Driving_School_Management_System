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

    public enum DAOTypes {
        COURSE,
        INSTRUCTOR,
        LESSON,
        PAYMENT,
        STUDENT,
        QUERY,
        USER
    }


    public SuperDAO getDAO(DAOTypes daoType) {
        switch (daoType) {
            case COURSE:
                return new CourseDAOImpl();
            case INSTRUCTOR:
                return new InstructorDAOImpl();
            case LESSON:
                return new LessonDAOImpl();
            case PAYMENT:
                return new PaymentDAOImpl();
            case STUDENT:
                return new StudentDAOImpl();
            case QUERY:
                return new QueryDAOImpl();
            case USER:
                return new UserDAOImpl();
            default:
                return null;
        }
    }
}
