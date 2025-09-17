package lk.ijse.orm.elite_driving_school_management_system.bo;

import lk.ijse.orm.elite_driving_school_management_system.bo.custom.impl.CourseBOImpl;

public class BOFactory {
    private static BOFactory boFactory;
    BOFactory() {}

    public static BOFactory getInstance() {
        return boFactory == null ?
                boFactory = new BOFactory() :boFactory;
    }

    public <T extends SuperBO> T getBO(BoTypes boType) {
        switch (boType){
            case COURSE :
                return (T) new CourseBOImpl();

        }
    }


}
