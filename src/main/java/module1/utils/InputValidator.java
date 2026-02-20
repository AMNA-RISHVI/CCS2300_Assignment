package module1.utils;

import java.util.Scanner;

public class InputValidator {

    public boolean isValidLocationId(String id) {
        return id != null && id.matches("L\\d{3,}");
    }

    public boolean isValidRoadId(String id) {
        return id != null && id.matches("R\\d{3,}");
    }

    public boolean isValidName(String name) {
        return name != null && name.length() >= 2 && name.length() <= 50;
    }

    public int getIntInput(Scanner scanner, String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                int val = Integer.parseInt(input);
                if (val >= min && val <= max) return val;
                System.out.printf("Enter between %d and %d.%n", min, max);
            } catch (NumberFormatException e) {
                System.out.println("Invalid number.");
            }
        }
    }

    public double getDoubleInput(Scanner scanner, String prompt, double min, double max) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                double val = Double.parseDouble(input);
                if (val >= min && val <= max) return val;
                System.out.printf("Enter between %.1f and %.1f.%n", min, max);
            } catch (NumberFormatException e) {
                System.out.println("Invalid number.");
            }
        }
    }

    public double getDoubleInput(Scanner scanner, String prompt, double min, double max, double defaultValue) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim();
        if (input.isEmpty()) return defaultValue;
        try {
            double val = Double.parseDouble(input);
            if (val >= min && val <= max) return val;
            System.out.printf("Using default (%.1f).%n", defaultValue);
        } catch (NumberFormatException e) {
            System.out.printf("Using default (%.1f).%n", defaultValue);
        }
        return defaultValue;
    }


    //Validates that an integer is within a given range.
    public void validateIntRange(int value, int min, int max, String fieldName) {
        if (value < min || value > max) {
            throw new IllegalArgumentException(
                String.format("%s must be between %d and %d (got %d)", fieldName, min, max, value)
            );
        }
    }


    public void validateDoubleRange(double value, double min, double max, String fieldName) {
        if (value < min || value > max) {
            throw new IllegalArgumentException(
                String.format("%s must be between %.2f and %.2f (got %.2f)", fieldName, min, max, value)
            );
        }
    }
}
    
    
    
    
        