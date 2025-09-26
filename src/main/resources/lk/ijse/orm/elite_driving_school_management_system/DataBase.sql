-- we don't know how to generate root <with-no-name> (class Root) :(

grant select on performance_schema.* to 'mysql.session'@localhost;

grant trigger on sys.* to 'mysql.sys'@localhost;

grant audit_abort_exempt, firewall_exempt, select, system_user on *.* to 'mysql.infoschema'@localhost;

grant audit_abort_exempt, authentication_policy_admin, backup_admin, clone_admin, connection_admin, firewall_exempt, persist_ro_variables_admin, session_variables_admin, shutdown, super, system_user, system_variables_admin on *.* to 'mysql.session'@localhost;

grant audit_abort_exempt, firewall_exempt, system_user on *.* to 'mysql.sys'@localhost;

grant alter, alter routine, application_password_admin, audit_abort_exempt, audit_admin, authentication_policy_admin, backup_admin, binlog_admin, binlog_encryption_admin, clone_admin, connection_admin, create, create role, create routine, create tablespace, create temporary tables, create user, create view, delete, drop, drop role, encryption_key_admin, event, execute, file, firewall_exempt, flush_optimizer_costs, flush_status, flush_tables, flush_user_resources, group_replication_admin, group_replication_stream, index, innodb_redo_log_archive, innodb_redo_log_enable, insert, lock tables, passwordless_user_admin, persist_ro_variables_admin, process, references, reload, replication client, replication slave, replication_applier, replication_slave_admin, resource_group_admin, resource_group_user, role_admin, select, sensitive_variables_observer, service_connection_admin, session_variables_admin, set_user_id, show databases, show view, show_routine, shutdown, super, system_user, system_variables_admin, table_encryption_admin, telemetry_log_admin, trigger, update, xa_recover_admin, grant option on *.* to root@localhost;
CREATE TABLE course_table (
                              course_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                              course_name VARCHAR(255) NOT NULL,
                              time_period VARCHAR(255) NOT NULL,
                              course_fee VARCHAR(255) NOT NULL
);


CREATE TABLE instructor_name (
                                 instructor_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                 instructor_name VARCHAR(255) NOT NULL,
                                 address VARCHAR(255) NOT NULL,
                                 phone VARCHAR(15),
                                 email VARCHAR(255) NOT NULL UNIQUE,
                                 availability VARCHAR(255) NOT NULL
);


CREATE TABLE students (
                          student_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          studentName VARCHAR(255) NOT NULL,
                          studentEmail VARCHAR(255) NOT NULL UNIQUE,
                          studentPhone VARCHAR(15),
                          studentAddress VARCHAR(255) NOT NULL,
                          registerFee VARCHAR(255),
                          registerDate DATE NOT NULL
);


CREATE TABLE lesson_table (
                              lesson_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                              lesson_name VARCHAR(255) NOT NULL,
                              start_time DATETIME NOT NULL,
                              end_time DATETIME NOT NULL,
                              date DATE NOT NULL,
                              course_id BIGINT NOT NULL,
                              instructor_id BIGINT NOT NULL,
                              student_id BIGINT NOT NULL,
                              FOREIGN KEY (course_id) REFERENCES course_table(course_id),
                              FOREIGN KEY (instructor_id) REFERENCES instructor_name(instructor_id),
                              FOREIGN KEY (student_id) REFERENCES students(student_id)
);


CREATE TABLE payment_table (
                               payment_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                               amount VARCHAR(255) NOT NULL,
                               description VARCHAR(255) NOT NULL,
                               time VARCHAR(255) NOT NULL,
                               date DATE NOT NULL,
                               student_id BIGINT NOT NULL,
                               course_id BIGINT NOT NULL UNIQUE,
                               FOREIGN KEY (student_id) REFERENCES students(student_id),
                               FOREIGN KEY (course_id) REFERENCES course_table(course_id)
);


CREATE TABLE student_course (
                                student_id BIGINT NOT NULL,
                                course_id BIGINT NOT NULL,
                                PRIMARY KEY(student_id, course_id),
                                FOREIGN KEY (student_id) REFERENCES students(student_id),
                                FOREIGN KEY (course_id) REFERENCES course_table(course_id)
);


CREATE TABLE users (
                       user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(255) NOT NULL UNIQUE,
                       mobile VARCHAR(20),
                       email VARCHAR(255) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       role VARCHAR(255) NOT NULL
);
