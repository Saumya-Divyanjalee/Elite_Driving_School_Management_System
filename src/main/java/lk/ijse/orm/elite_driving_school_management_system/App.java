package lk.ijse.orm.elite_driving_school_management_system;

import lk.ijse.orm.elite_driving_school_management_system.config.FactoryConfiguration;
import org.hibernate.Session;

public class App {

    public static void main(String[] args) {
        Session session = FactoryConfiguration.getInstance().getSession();
        System.out.println("Session opened: " + session.isOpen());
        session.close();
    }
}
