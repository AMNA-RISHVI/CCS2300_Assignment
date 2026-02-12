package module1.models;

public class LocationTest {

    public static void main(String[] args) {

        testLocationCreation();
        testToString();
        testMultipleLocations();

        // NEW validation tests
        testInvalidLocationId();
        testInvalidLocationName();
        testInvalidLocationType();
        testValidLocationWithValidation();

        System.out.println("\nAll tests completed.");
    }

    // -------- Test 1 --------
    public static void testLocationCreation() {
        Location location = new Location("L001", "Central Park", "Park", 10.5, 20.3);

        checkEquals("L001", location.getId(), "ID check");
        checkEquals("Central Park", location.getName(), "Name check");
        checkEquals("Park", location.getType(), "Type check");
        checkDoubleEquals(10.5, location.getX(), 0.001, "X coordinate check");
        checkDoubleEquals(20.3, location.getY(), 0.001, "Y coordinate check");
    }

    // -------- Test 2 --------
    public static void testToString() {
        Location location = new Location("L002", "City Hospital", "Hospital", 5.0, 15.0);

        String result = location.toString();
        checkEquals("L002 - City Hospital (Hospital)", result, "toString check");
    }

    // -------- Test 3 --------
    public static void testMultipleLocations() {
        Location loc1 = new Location("L001", "Park", "Recreation", 0, 0);
        Location loc2 = new Location("L002", "Hospital", "Medical", 5, 10);

        checkNotEquals(loc1.getId(), loc2.getId(), "Different ID check");
        checkNotEquals(loc1.getX(), loc2.getX(), "Different X coordinate check");
    }


    // -------- VALIDATION TESTS --------

    public static void testInvalidLocationId() {

        checkThrows(() -> new Location(null, "Test", "Park", 0, 0),
                "Null ID should throw exception");

        checkThrows(() -> new Location("", "Test", "Park", 0, 0),
                "Empty ID should throw exception");

        checkThrows(() -> new Location("001", "Test", "Park", 0, 0),
                "ID without 'L' should throw exception");
    }

    public static void testInvalidLocationName() {

        checkThrows(() -> new Location("L001", null, "Park", 0, 0),
                "Null name should throw exception");

        checkThrows(() -> new Location("L001", "", "Park", 0, 0),
                "Empty name should throw exception");
    }

    public static void testInvalidLocationType() {

        checkThrows(() -> new Location("L001", "Test", null, 0, 0),
                "Null type should throw exception");

        checkThrows(() -> new Location("L001", "Test", "", 0, 0),
                "Empty type should throw exception");
    }

    public static void testValidLocationWithValidation() {
        try {
            Location location = new Location("L999", "Valid Park", "Park", 10.5, 20.3);

            checkEquals("L999", location.getId(), "Valid ID check");
            checkEquals("Valid Park", location.getName(), "Valid name check");

        } catch (Exception e) {
            System.out.println("FAIL: Valid location should NOT throw exception");
        }
    }


    // -------- Helper Methods --------
    private static void checkEquals(Object expected, Object actual, String testName) {
        if (expected.equals(actual)) {
            System.out.println("PASS: " + testName);
        } else {
            System.out.println("FAIL: " + testName +
                    " | Expected: " + expected + ", Actual: " + actual);
        }
    }

    private static void checkNotEquals(Object a, Object b, String testName) {
        if (!a.equals(b)) {
            System.out.println("PASS: " + testName);
        } else {
            System.out.println("FAIL: " + testName +
                    " | Both values are equal: " + a);
        }
    }

    private static void checkDoubleEquals(double expected, double actual, double tolerance, String testName) {
        if (Math.abs(expected - actual) <= tolerance) {
            System.out.println("PASS: " + testName);
        } else {
            System.out.println("FAIL: " + testName +
                    " | Expected: " + expected + ", Actual: " + actual);
        }
    }


    private static void checkThrows(Runnable action, String testName) {
        try {
            action.run();
            System.out.println("FAIL: " + testName + " (no exception thrown)");
        } catch (IllegalArgumentException e) {
            System.out.println("PASS: " + testName);
        } catch (Exception e) {
            System.out.println("FAIL: " + testName +
                    " (wrong exception type: " + e + ")");
        }
    }
}















    

    

    
    
    

    
