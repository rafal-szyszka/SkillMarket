package it.szyszka.skillmarket.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by rafal on 30.09.17.
 */

public class StringValidator {

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean validNotEmpty(String value) {
        return !value.isEmpty();
    }

    public static boolean validEmail(String value) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(value);
        return matcher.find();
    }

    public static boolean validNoWhiteSpaces(String value) {
        return !value.matches(".*\\s+.*");
    }

    public static boolean validFullName(String value) {
        return value.matches("([a-zA-Z]{3,}([ ][a-zA-Z]{3,})+)");
    }

    public static boolean validPassword(String value) {
        return value.length() >= 8;
    }

}
