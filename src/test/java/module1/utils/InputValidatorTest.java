package module1.utils;

import module1.utils.exceptions.*;

import java.util.Set;

/**
 * Manual test harness for InputValidator.
 * Run this class to verify all validation methods work correctly.
 */
public class InputValidatorTest {

    private static InputValidator validator = new InputValidator();
    private static int testsPassed = 0;
    private static int testsFailed = 0;

    public static void main(String[] args) {
        System.out.println("=== Manual InputValidator Test ===\n");

        testIsValidLocationId();
        testIsValidRoadId();
        testIsValidName();
        testValidateIntRange();
        testValidateDoubleRange();
        testValidateLocationIdFormat();
        testValidateRoadIdFormat();
        testIsValidRoadType();
        testDuplicateLocationId();
        testDuplicateRoadId();

        System.out.println("\n=== Test Summary ===");
        System.out.println("Passed: " + testsPassed);
        System.out.println("Failed: " + testsFailed);
    }

    private static void assertTrue(boolean condition, String testName) {
        if (condition) {
            System.out.println("PASS: " + testName);
            testsPassed++;
        } else {
            System.out.println("FAIL: " + testName);
            testsFailed++;
        }
    }

    private static void assertFalse(boolean condition, String testName) {
        assertTrue(!condition, testName);
    }

    // --- Test methods ---
    private static void testIsValidLocationId() {
        System.out.println("\n--- Location ID Format ---");
        assertTrue(validator.isValidLocationId("L001"), "Valid ID L001");
        assertTrue(validator.isValidLocationId("L999"), "Valid ID L999");
        assertTrue(validator.isValidLocationId("L1234"), "Valid ID L1234");
        assertFalse(validator.isValidLocationId("L1"), "Too short (L1)");
        assertFalse(validator.isValidLocationId("l001"), "Lowercase l");
        assertFalse(validator.isValidLocationId("001"), "Missing L");
        assertFalse(validator.isValidLocationId(null), "Null ID");
    }

    private static void testIsValidRoadId() {
        System.out.println("\n--- Road ID Format ---");
        assertTrue(validator.isValidRoadId("R001"), "Valid R001");
        assertTrue(validator.isValidRoadId("R999"), "Valid R999");
        assertFalse(validator.isValidRoadId("R1"), "Too short");
        assertFalse(validator.isValidRoadId("r001"), "Lowercase");
        assertFalse(validator.isValidRoadId(null), "Null");
    }

    private static void testIsValidName() {
        System.out.println("\n--- Name Validation ---");
        assertTrue(validator.isValidName("Central Park"), "Normal name");
        assertTrue(validator.isValidName("St. Mary's"), "With punctuation");
        assertTrue(validator.isValidName("A".repeat(50)), "50 chars");
        assertFalse(validator.isValidName("A"), "Too short (1 char)");
        assertFalse(validator.isValidName("A".repeat(51)), "Too long (51 chars)");
        assertFalse(validator.isValidName("   "), "Only spaces");
        assertFalse(validator.isValidName(null), "Null");
    }

    private static void testValidateIntRange() {
        System.out.println("\n--- Integer Range Validation ---");
        // Valid case
        try {
            validator.validateIntRange(5, 1, 10, "test");
            System.out.println("PASS: Valid int range");
            testsPassed++;
        } catch (IllegalArgumentException e) {
            System.out.println("FAIL: Valid int range threw exception: " + e.getMessage());
            testsFailed++;
        }

        // Below min
        try {
            validator.validateIntRange(0, 1, 10, "test");
            System.out.println("FAIL: Below min should throw");
            testsFailed++;
        } catch (IllegalArgumentException e) {
            System.out.println("PASS: Below min correctly threw");
            testsPassed++;
        }

        // Above max
        try {
            validator.validateIntRange(11, 1, 10, "test");
            System.out.println("FAIL: Above max should throw");
            testsFailed++;
        } catch (IllegalArgumentException e) {
            System.out.println("PASS: Above max correctly threw");
            testsPassed++;
        }
    }

    private static void testValidateDoubleRange() {
        System.out.println("\n--- Double Range Validation ---");
        // Valid
        try {
            validator.validateDoubleRange(50.5, 0.0, 100.0, "test");
            System.out.println("PASS: Valid double range");
            testsPassed++;
        } catch (IllegalArgumentException e) {
            System.out.println("FAIL: Valid double range threw: " + e.getMessage());
            testsFailed++;
        }

        // Below min
        try {
            validator.validateDoubleRange(-1.0, 0.0, 100.0, "test");
            System.out.println("FAIL: Below min should throw");
            testsFailed++;
        } catch (IllegalArgumentException e) {
            System.out.println("PASS: Below min threw correctly");
            testsPassed++;
        }

        // Above max
        try {
            validator.validateDoubleRange(101.0, 0.0, 100.0, "test");
            System.out.println("FAIL: Above max should throw");
            testsFailed++;
        } catch (IllegalArgumentException e) {
            System.out.println("PASS: Above max threw correctly");
            testsPassed++;
        }
    }

    private static void testValidateLocationIdFormat() {
        System.out.println("\n--- Location ID Format Exception ---");
        // Valid
        try {
            validator.validateLocationIdFormat("L001");
            System.out.println("PASS: Valid location ID format");
            testsPassed++;
        } catch (InvalidLocationIdException e) {
            System.out.println("FAIL: Valid ID threw: " + e.getMessage());
            testsFailed++;
        }

        // Invalid
        try {
            validator.validateLocationIdFormat("L1");
            System.out.println("FAIL: Invalid format L1 should throw");
            testsFailed++;
        } catch (InvalidLocationIdException e) {
            System.out.println("PASS: Invalid L1 threw correctly");
            testsPassed++;
        }

        try {
            validator.validateLocationIdFormat("001");
            System.out.println("FAIL: Invalid 001 should throw");
            testsFailed++;
        } catch (InvalidLocationIdException e) {
            System.out.println("PASS: Invalid 001 threw correctly");
            testsPassed++;
        }
    }

    private static void testValidateRoadIdFormat() {
        System.out.println("\n--- Road ID Format Exception ---");
        // Valid
        try {
            validator.validateRoadIdFormat("R001");
            System.out.println("PASS: Valid road ID format");
            testsPassed++;
        } catch (InvalidRoadIdException e) {
            System.out.println("FAIL: Valid ID threw: " + e.getMessage());
            testsFailed++;
        }

        // Invalid
        try {
            validator.validateRoadIdFormat("R1");
            System.out.println("FAIL: Invalid R1 should throw");
            testsFailed++;
        } catch (InvalidRoadIdException e) {
            System.out.println("PASS: Invalid R1 threw correctly");
            testsPassed++;
        }
    }

    private static void testIsValidRoadType() {
        System.out.println("\n--- Road Type Validation ---");
        // Assuming allowed types: Street, Avenue, Highway, Alley
        assertTrue(validator.isValidRoadType("Street"), "Street");
        assertTrue(validator.isValidRoadType("Avenue"), "Avenue");
        assertTrue(validator.isValidRoadType("Highway"), "Highway");
        assertTrue(validator.isValidRoadType("Alley"), "Alley");
        assertFalse(validator.isValidRoadType("Path"), "Invalid type");
        assertFalse(validator.isValidRoadType(null), "Null");
    }

    private static void testDuplicateLocationId() {
        System.out.println("\n--- Duplicate Location ID ---");
        Set<String> existing = Set.of("L001", "L002");
        assertTrue(validator.isDuplicateLocationId("L001", existing), "Duplicate true");
        assertFalse(validator.isDuplicateLocationId("L003", existing), "Not duplicate");

        // Duplicate case – should throw
        try {
            validator.checkDuplicateLocationId("L001", existing);
            System.out.println("FAIL: DuplicateLocationException expected but not thrown");
            testsFailed++;
        } catch (DuplicateLocationException e) {
            System.out.println("PASS: DuplicateLocationException thrown as expected");
            testsPassed++;
        }

        // Non‑duplicate case – should NOT throw
        try {
            validator.checkDuplicateLocationId("L003", existing);
            System.out.println("PASS: New ID no exception");
            testsPassed++;
        } catch (DuplicateLocationException e) {
            System.out.println("FAIL: New ID threw exception: " + e.getMessage());
            testsFailed++;
        }
    }

    private static void testDuplicateRoadId() {
        System.out.println("\n--- Duplicate Road ID (with suffixes) ---");
        Set<String> existing = Set.of("R001_F", "R001_R", "R002_F");
        assertTrue(validator.isDuplicateRoadId("R001", existing), "Duplicate with suffix");
        assertTrue(validator.isDuplicateRoadId("R002", existing), "Duplicate with suffix");
        assertFalse(validator.isDuplicateRoadId("R003", existing), "Not duplicate");

        // Duplicate case
        try {
            validator.checkDuplicateRoadId("R001", existing);
            System.out.println("FAIL: DuplicateRoadException expected but not thrown");
            testsFailed++;
        } catch (DuplicateRoadException e) {
            System.out.println("PASS: DuplicateRoadException thrown as expected");
            testsPassed++;
        }

        // Non‑duplicate case
        try {
            validator.checkDuplicateRoadId("R003", existing);
            System.out.println("PASS: New road ID no exception");
            testsPassed++;
        } catch (DuplicateRoadException e) {
            System.out.println("FAIL: New road ID threw exception: " + e.getMessage());
            testsFailed++;
        }
    }
}