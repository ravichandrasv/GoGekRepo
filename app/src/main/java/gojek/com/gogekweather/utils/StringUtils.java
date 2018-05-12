package gojek.com.gogekweather.utils;

import android.text.TextUtils;

public class StringUtils {
    private StringUtils() {

    }


    public static String extractVerificationCode(String message) {
        String verificationCode = null;

        if (message != null) {
            verificationCode = "";
        }

        return verificationCode;
    }

    public static boolean isNullOrEmpty(String input) {
        return input == null || TextUtils.isEmpty(input);
    }

}
