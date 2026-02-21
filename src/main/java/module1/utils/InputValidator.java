package module1.utils;

import java.util.Scanner;
import java.util.Set;
import java.util.Collection;


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


    // Add constants for allowed road types
    private static final Set<String> ALLOWED_ROAD_TYPES = Set.of("Street", "Avenue", "Highway", "Alley");
    
    
    //Validates a location ID format and throws exception if invalid
    public void validateLocationIdFormat(String id) throws InvalidLocationIdException {
        if (!isValidLocationId(id)) {
            throw new InvalidLocationIdException(
                "Invalid location ID format. Must start with 'L' followed by at least 3 digits (e.g., L001)"
            );
        }
    }

    //Validates a road ID format and throws exception if invalid
    public void validateRoadIdFormat(String id) throws InvalidRoadIdException {
        if (!isValidRoadId(id)) {
            throw new InvalidRoadIdException(
                "Invalid road ID format. Must start with 'R' followed by at least 3 digits (e.g., R001)"
            );
        }
    }

    //Checks if a road type is allowed
    public boolean isValidRoadType(String type) {
        return type != null && ALLOWED_ROAD_TYPES.contains(type);
    }

    //Validates a road type
    public void validateRoadType(String type) throws InvalidInputException {
        if (!isValidRoadType(type)) {
            throw new InvalidInputException("Road type must be one of: Street, Avenue, Highway, Alley");
        }
    }


    //Checks if a location ID already exists
    public boolean isDuplicateLocationId(String id, Collection<String> existingIds) {
        return existingIds != null && existingIds.contains(id);
    }

    //Throws DuplicateLocationException if ID already exists
    public void checkDuplicateLocationId(String id, Collection<String> existingIds) throws DuplicateLocationException {
        if (isDuplicateLocationId(id, existingIds)) {
            throw new DuplicateLocationException("Location ID '" + id + "' already exists.");
        }
    }

    //Checks if a road ID already exists (considering bidirectional suffixes)
    public boolean isDuplicateRoadId(String id, Collection<String> existingRoadIds) {
        if (existingRoadIds == null) return false;
        return existingRoadIds.contains(id) ||
           existingRoadIds.contains(id + "_F") ||
           existingRoadIds.contains(id + "_R");
    }

    public void checkDuplicateRoadId(String id, Collection<String> existingRoadIds) throws DuplicateRoadException {
        if (isDuplicateRoadId(id, existingRoadIds)) {
            throw new DuplicateRoadException("Road ID '" + id + "' (or its bidirectional counterpart) already exists.");
        }
    }

}
    
    
    
    
        