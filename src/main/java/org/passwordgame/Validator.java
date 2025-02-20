package org.passwordgame;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    public boolean hasLowerCase(String str) {
        return str.matches(".*[a-z].*");
    }

    public boolean hasUpperCase(String str) {
        Pattern pattern = Pattern.compile("[A-Z]");
        Matcher matcher = pattern.matcher(str);
        return matcher.find();
    }

    public boolean hasNumber(String str) {
        Pattern pattern = Pattern.compile("\\d");
        Matcher matcher = pattern.matcher(str);
        return matcher.find();
    }

    public boolean hasSpecialCharacter(String str) {
        Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");
        Matcher matcher = pattern.matcher(str);
        return matcher.find();
    }

    public boolean hasSpaces(String str) {
        return str.contains(" ");
    }

    public boolean isValidUsername(String str) {
        return str.matches("[a-zA-Z0-9_-]+");
    }

}
