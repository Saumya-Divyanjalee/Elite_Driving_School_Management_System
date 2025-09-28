package lk.ijse.orm.elite_driving_school_management_system.util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordEncryptor {

    // Hash plain password with bcrypt
    public static String hashPassword(String plainPassword) {
        if (plainPassword == null || plainPassword.trim().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty!");
        }
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt(12)); // workload factor 12
    }

    // Verify input password against stored hash
    public static boolean verifyPassword(String plainPassword, String hashedPassword) {
        if (plainPassword == null || hashedPassword == null || hashedPassword.isEmpty()) {
            return false;
        }

        try {
            return BCrypt.checkpw(plainPassword, hashedPassword);
        } catch (Exception e) {
            // Handle invalid salt version or other BCrypt errors
            System.err.println("BCrypt verification error: " + e.getMessage());
            return false;
        }
    }
}