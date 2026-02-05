package module1.models;

public class RoadTest {

    public static void main(String[] args) {

        testRoadCreation();
        testWeightedDistance();
        testDifferentTrafficLevels();
        testToString();

        System.out.println("\nAll Road tests completed.");
    }

    // -------- Test 1 --------
    public static void testRoadCreation() {
        Location source = new Location("L001", "Park", "Park", 0, 0);
        Location dest = new Location("L002", "Hospital", "Hospital", 5, 5);

        Road road = new Road("R001", source, dest, 500, "Street", 2);

        checkEquals("R001", road.getId(), "Road ID check");
        checkEquals(source, road.getSource(), "Source location check");
        checkEquals(dest, road.getDestination(), "Destination location check");
        checkEquals(500, road.getDistance(), "Distance check");
        checkEquals("Street", road.getRoadType(), "Road type check");
        checkEquals(2, road.getTrafficLevel(), "Traffic level check");
    }

    // -------- Test 2 --------
    public static void testWeightedDistance() {
        Location source = new Location("L001", "A", "Type", 0, 0);
        Location dest = new Location("L002", "B", "Type", 0, 0);

        Road road = new Road("R001", source, dest, 100, "Highway", 3);

        int weightedDist = road.getWeightedDistance();
        checkEquals(300, weightedDist, "Weighted distance check (100 * 3)");
    }

    // -------- Test 3 --------
    public static void testDifferentTrafficLevels() {
        Location source = new Location("L001", "A", "Type", 0, 0);
        Location dest = new Location("L002", "B", "Type", 0, 0);

        Road lowTraffic = new Road("R001", source, dest, 200, "Street", 1);
        Road highTraffic = new Road("R002", source, dest, 200, "Street", 5);

        checkEquals(200, lowTraffic.getWeightedDistance(), "Low traffic weighted distance");
        checkEquals(1000, highTraffic.getWeightedDistance(), "High traffic weighted distance");
    }

    // -------- Test 4 --------
    public static void testToString() {
        Location source = new Location("L001", "Central Park", "Park", 0, 0);
        Location dest = new Location("L002", "City Mall", "Shopping", 0, 0);

        Road road = new Road("R001", source, dest, 750, "Avenue", 2);
        String result = road.toString();

        checkTrue(result.contains("Central Park"), "toString contains source name");
        checkTrue(result.contains("City Mall"), "toString contains destination name");
        checkTrue(result.contains("750"), "toString contains distance");
        checkTrue(result.contains("Avenue"), "toString contains road type");
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

    private static void checkTrue(boolean condition, String testName) {
        if (condition) {
            System.out.println("PASS: " + testName);
        } else {
            System.out.println("FAIL: " + testName);
        }
    }
}
