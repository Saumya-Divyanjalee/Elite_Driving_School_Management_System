package lk.ijse.orm.elite_driving_school_management_system.util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordEncryptor {

    public static String hashPassword(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt(12));
    }

    public static boolean verifyPassword(String plainPassword, String hashedPassword) {
        if (hashedPassword == null) return false;
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}
