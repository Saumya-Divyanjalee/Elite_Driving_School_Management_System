module lk.ijse.orm.elite_driving_school_management_system {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires org.hibernate.orm.core;
    requires static lombok;
    requires jakarta.persistence;
    requires java.naming;
    requires jbcrypt;


    opens lk.ijse.orm.elite_driving_school_management_system to javafx.fxml;
    opens lk.ijse.orm.elite_driving_school_management_system.controller to javafx.base;
    opens lk.ijse.orm.elite_driving_school_management_system.config to jakarta.persistence;

    exports lk.ijse.orm.elite_driving_school_management_system;
}