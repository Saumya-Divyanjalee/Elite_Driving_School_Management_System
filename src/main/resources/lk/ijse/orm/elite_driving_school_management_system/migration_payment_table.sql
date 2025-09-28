-- Migration script to remove unique constraint from payment_table.course_id
-- This script should be run on the existing database to fix the constraint issue

-- First, we need to drop the existing table and recreate it without the UNIQUE constraint
-- Note: This will result in data loss. If you have existing data, you should back it up first.

-- Backup existing data (if any)
CREATE TABLE payment_table_backup AS SELECT * FROM payment_table;

-- Drop the existing table
DROP TABLE payment_table;

-- Recreate the table without the UNIQUE constraint
CREATE TABLE payment_table (
    payment_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    amount VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    time VARCHAR(255) NOT NULL,
    date DATE NOT NULL,
    student_id BIGINT NOT NULL,
    course_id BIGINT NOT NULL,
    user_id VARCHAR(255),
    FOREIGN KEY (student_id) REFERENCES students(student_id),
    FOREIGN KEY (course_id) REFERENCES course_table(course_id)
);

-- Restore data from backup (if needed)
-- INSERT INTO payment_table SELECT * FROM payment_table_backup;

-- Drop the backup table
-- DROP TABLE payment_table_backup;