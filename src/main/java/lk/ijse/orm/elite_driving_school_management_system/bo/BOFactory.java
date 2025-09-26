package lk.ijse.orm.elite_driving_school_management_system.bo;

import lk.ijse.orm.elite_driving_school_management_system.bo.custom.impl.*;
import lk.ijse.orm.elite_driving_school_management_system.entity.Lesson;

public class BOFactory {
    private static BOFactory boFactory;
    BOFactory() {}

    public static BOFactory getInstance() {
        return boFactory == null ?
                boFactory = new BOFactory() :boFactory;
    }

    public enum BoTypes {
        COURSE,
        INSTRUCTOR,
        LESSON,
        PAYMENT,
        STUDENT,
        USER,

    }

    public SuperBO getBO(BoTypes boType) {
        switch (boType){
            case COURSE :
                return new CourseBOImpl();
            case INSTRUCTOR :
                return new InstructorBOImpl();
            case LESSON:
                return new LessonBOImpl();
            case PAYMENT:
                return new PaymentBOImpl();
            case STUDENT:
                return new StudentBOImpl();
            case USER:
                return new UserBOImpl();
            default:
                return null;

        }
    }


}
