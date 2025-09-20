module lk.ijse.orm.elite_driving_school_management_system {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires org.hibernate.orm.core;
    requires static lombok;
    requires jakarta.persistence;
    requires java.naming;
    requires jbcrypt;
    requires java.mail;

    // FXML controllers open to JavaFX
    opens lk.ijse.orm.elite_driving_school_management_system.controller to javafx.fxml;

    // Entities open to Hibernate
    opens lk.ijse.orm.elite_driving_school_management_system.entity to org.hibernate.orm.core, jakarta.persistence;

    // Config open to Hibernate if needed
    opens lk.ijse.orm.elite_driving_school_management_system.config to org.hibernate.orm.core, jakarta.persistence;

    exports lk.ijse.orm.elite_driving_school_management_system;
}
