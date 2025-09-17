package lk.ijse.orm.elite_driving_school_management_system.util;

import java.util.regex.Pattern;

public class RegexUtil {
    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");

    private static final Pattern PHONE_PATTERN =
            Pattern.compile("^\\+94\\d{9}$"); // Sri Lanka phone format

    public static boolean isValidEmail(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }

    public static boolean isValidPhone(String phone) {
        return PHONE_PATTERN.matcher(phone).matches();
    }
}
