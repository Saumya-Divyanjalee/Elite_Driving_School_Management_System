module lk.ijse.orm.elite_driving_school_management_system {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    requires org.hibernate.orm.core;
    requires static lombok;
    requires jakarta.persistence;
    requires java.naming;
    requires jbcrypt;



    opens lk.ijse.orm.elite_driving_school_management_system.controller to javafx.fxml;


    opens lk.ijse.orm.elite_driving_school_management_system.entity to org.hibernate.orm.core, jakarta.persistence;
    opens lk.ijse.orm.elite_driving_school_management_system.config to org.hibernate.orm.core, jakarta.persistence;


    opens lk.ijse.orm.elite_driving_school_management_system.tm to javafx.base;


    exports lk.ijse.orm.elite_driving_school_management_system;
}