package org.example.taskmanagementsystem.services;

import java.util.regex.Pattern;

public class ValidationUtils {
    public static boolean isNullOrEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }
    public static boolean isValidLength(String value, int maxLength) {
        return value != null && value.length() <= maxLength;
    }
    public static boolean isValidEmail(String email) {
        if (isNullOrEmpty(email)) return false;
        String emailRegex = "^[A-Za-z0-9] + @gmail.com";
        return Pattern.matches(emailRegex, email);
    }

    public static boolean isPositiveInteger(String number) {
        try {
            int num = Integer.parseInt(number);
            return num > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public static boolean isValidDate(String date) {
        if (isNullOrEmpty(date)) return false;
        String dateRegex = "^\\d{4}-\\d{2}-\\d{2}$";
        return Pattern.matches(dateRegex, date);
    }
    public static boolean validateTask(String taskName, String description, String dueDate) {
        if (isNullOrEmpty(taskName)) {
            System.out.println("Task name cannot be empty!");
            return false;
        }
        if (!isValidLength(taskName, 100)) {
            System.out.println("Task name is too long!");
            return false;
        }
        if (!isValidLength(description, 500)) {
            System.out.println("Description is too long!");
            return false;
        }
        if (!isValidDate(dueDate)) {
            System.out.println("Invalid date format. Use YYYY-MM-DD.");
            return false;
        }
        return true;
    }
}
