module lk.ijse.orm.elite_driving_school_management_system {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.desktop;

    requires org.hibernate.orm.core;
    requires static lombok;
    requires jakarta.persistence;
    requires java.naming;
    requires jbcrypt;
    requires java.mail;


    opens lk.ijse.orm.elite_driving_school_management_system.controller to javafx.fxml;
    opens lk.ijse.orm.elite_driving_school_management_system.entity to org.hibernate.orm.core, jakarta.persistence;
    opens lk.ijse.orm.elite_driving_school_management_system.config to org.hibernate.orm.core, jakarta.persistence;
    exports lk.ijse.orm.elite_driving_school_management_system;
}
