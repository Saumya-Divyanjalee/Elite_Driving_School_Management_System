package lk.ijse.orm.elite_driving_school_management_system.util;

import lk.ijse.orm.elite_driving_school_management_system.exception.LoginException;

public class RegexUtil {

    public static void validateRequired(String value, String field) throws LoginException {
        if (value == null || value.isEmpty()) {
            throw new LoginException(field + " is required");
        }
    }

    public static boolean isValidEmail(String email) {
        return email != null && email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }
}
