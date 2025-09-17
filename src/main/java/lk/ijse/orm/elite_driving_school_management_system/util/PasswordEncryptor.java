package lk.ijse.orm.elite_driving_school_management_system.util;

import org.mindrot.jbcrypt.BCrypt;


    public class PasswordEncryptor {

        // Hash a password
        public static String hashPassword(String plainPassword) {
            return BCrypt.hashpw(plainPassword, BCrypt.gensalt(12));
        }

        // Verify a password
        public static boolean verifyPassword(String plainPassword, String hashedPassword) {
            return BCrypt.checkpw(plainPassword, hashedPassword);
        }
}
