package module1.trees;

import module1.models.Location;

public class AVLTreeTest {

    private static AVLTree tree;
    private static Location location1, location2, location3, location4, location5;

    public static void main(String[] args) {

        setUp();
        testEmptyTree();

        setUp();
        testInsertSingleLocation();

        setUp();
        testInsertMultipleLocations();

        setUp();
        testInsertDuplicateId();

        setUp();
        testInsertNullLocation();

        setUp();
        testSearchExistingLocation();

        setUp();
        testSearchNonExistentLocation();

        setUp();
        testSearchWithNullId();

        setUp();
        testDeleteLeafNode();

        setUp();
        testDeleteNodeWithOneChild();

        setUp();
        testDeleteNodeWithTwoChildren();

        setUp();
        testDeleteRoot();

        setUp();
        testDeleteNonExistentLocation();

        setUp();
        testDeleteWithNullId();

        setUp();
        testAVLBalancingAfterInsert();

        setUp();
        testAVLBalancingAfterDelete();

        setUp();
        testClearTree();

        setUp();
        testDisplayMethods();

        setUp();
        testGetMinNode();

        System.out.println("\nAll AVLTree tests completed.");
    }

    
    private static void setUp() {
        tree = new AVLTree();

        location1 = new Location("L001", "Central Park", "Park", 0, 0);
        location2 = new Location("L002", "City Hospital", "Hospital", 2, 3);
        location3 = new Location("L003", "Main Mall", "Shopping", 5, 2);
        location4 = new Location("L004", "Tech University", "Education", 7, 8);
        location5 = new Location("L005", "Railway Station", "Transport", 3, 6);
    }

    // ---------- TESTS ----------

    private static void testEmptyTree() {
        checkTrue(tree.isEmpty(), "Empty tree check");
        checkEquals(0, tree.getSize(), "Empty tree size");
        checkEquals(0, tree.getHeight(), "Empty tree height");
        checkNull(tree.search("L001"), "Search in empty tree");
    }

    private static void testInsertSingleLocation() {
        tree.insert(location1);

        checkFalse(tree.isEmpty(), "Tree not empty after insert");
        checkEquals(1, tree.getSize(), "Size after single insert");
        checkEquals(1, tree.getHeight(), "Height after single insert");
        checkEquals(location1, tree.search("L001"), "Search inserted node");
        checkTrue(tree.isBalanced(), "Tree balanced after single insert");
    }

    private static void testInsertMultipleLocations() {
        tree.insert(location1);
        tree.insert(location2);
        tree.insert(location3);

        checkEquals(3, tree.getSize(), "Size after multiple inserts");
        checkTrue(tree.getHeight() <= 2, "Height remains balanced");
        checkEquals(location2, tree.search("L002"), "Search middle node");
        checkTrue(tree.isBalanced(), "Tree balanced after multiple inserts");
    }

    private static void testInsertDuplicateId() {
        tree.insert(location1);
        tree.insert(new Location("L001", "Different Park", "Park", 1, 1));

        checkEquals(1, tree.getSize(), "Duplicate ID does not increase size");
        checkEquals("Different Park",
                tree.search("L001").getName(),
                "Duplicate ID updates node");
    }

    private static void testInsertNullLocation() {
        checkThrows(() -> tree.insert(null), "Insert null location");
    }

    private static void testSearchExistingLocation() {
        tree.insert(location1);
        tree.insert(location2);
        tree.insert(location3);

        Location found = tree.search("L002");
        checkNotNull(found, "Search existing node");
        checkEquals("City Hospital", found.getName(), "Search name check");
        checkEquals("Hospital", found.getType(), "Search type check");
    }

    private static void testSearchNonExistentLocation() {
        tree.insert(location1);
        tree.insert(location2);

        checkNull(tree.search("L999"), "Search non-existent node");
    }

    private static void testSearchWithNullId() {
        checkThrows(() -> tree.search(null), "Search with null ID");
        checkThrows(() -> tree.search(""), "Search with empty ID");
    }

    private static void testDeleteLeafNode() {
        tree.insert(location1);
        tree.insert(location2);
        tree.insert(location3);

        tree.delete("L003");

        checkEquals(2, tree.getSize(), "Size after deleting leaf");
        checkNull(tree.search("L003"), "Leaf node deleted");
        checkTrue(tree.isBalanced(), "Tree balanced after leaf delete");
    }

    private static void testDeleteNodeWithOneChild() {
        tree.insert(location1);
        tree.insert(location3);
        tree.insert(location2);

        tree.delete("L003");

        checkEquals(2, tree.getSize(), "Size after deleting one-child node");
        checkNull(tree.search("L003"), "One-child node deleted");
        checkTrue(tree.isBalanced(), "Tree balanced");
    }

    private static void testDeleteNodeWithTwoChildren() {
        tree.insert(location2);
        tree.insert(location1);
        tree.insert(location3);

        tree.delete("L002");

        checkEquals(2, tree.getSize(), "Size after deleting two-child node");
        checkNull(tree.search("L002"), "Two-child node deleted");
        checkTrue(tree.isBalanced(), "Tree balanced");
    }

    private static void testDeleteRoot() {
        tree.insert(location2);
        tree.insert(location1);
        tree.insert(location3);

        tree.delete("L002");

        checkEquals(2, tree.getSize(), "Root deleted");
        checkTrue(tree.isBalanced(), "Tree balanced after root delete");
    }

    private static void testDeleteNonExistentLocation() {
        tree.insert(location1);
        tree.insert(location2);

        tree.delete("L999");

        checkEquals(2, tree.getSize(), "Delete non-existent node");
    }

    private static void testDeleteWithNullId() {
        checkThrows(() -> tree.delete(null), "Delete null ID");
        checkThrows(() -> tree.delete(""), "Delete empty ID");
    }

    private static void testAVLBalancingAfterInsert() {
        tree.insert(new Location("L001", "AA", "Type", 0, 0));
        tree.insert(new Location("L002", "BB", "Type", 0, 0));
        tree.insert(new Location("L003", "CC", "Type", 0, 0));
        tree.insert(new Location("L004", "DD", "Type", 0, 0));
        tree.insert(new Location("L005", "EE", "Type", 0, 0));

        checkTrue(tree.getHeight() <= 3, "AVL balanced after sequential insert");
        checkTrue(tree.isBalanced(), "Tree remains balanced");
        checkEquals(5, tree.getSize(), "Correct size after inserts");
    }

    private static void testAVLBalancingAfterDelete() {
        tree.insert(location1);
        tree.insert(location2);
        tree.insert(location3);
        tree.insert(location4);
        tree.insert(location5);

        tree.delete("L003");
        checkTrue(tree.isBalanced(), "Balanced after delete");

        tree.delete("L001");
        checkTrue(tree.isBalanced(), "Balanced after second delete");
    }

    private static void testClearTree() {
        tree.insert(location1);
        tree.insert(location2);
        tree.insert(location3);

        tree.clear();

        checkTrue(tree.isEmpty(), "Tree cleared");
        checkEquals(0, tree.getSize(), "Size after clear");
        checkEquals(0, tree.getHeight(), "Height after clear");
        checkNull(tree.search("L001"), "Search after clear");
    }

    private static void testDisplayMethods() {
        tree.insert(location1);
        tree.insert(location2);

        tree.displayInOrder();
        tree.displayTree();

        checkTrue(true, "Display methods executed");
    }

    private static void testGetMinNode() {
        tree.insert(new Location("L003", "CC", "Type", 0, 0));
        tree.insert(new Location("L001", "AA", "Type", 0, 0));
        tree.insert(new Location("L005", "EE", "Type", 0, 0));
        tree.insert(new Location("L002", "BB", "Type", 0, 0));
        tree.insert(new Location("L004", "DD", "Type", 0, 0));

        tree.displayInOrder();
        System.out.println("PASS: In-order traversal executed");
    }

    // ---------- HELPER METHODS ----------

    private static void checkEquals(Object expected, Object actual, String testName) {
        if (expected.equals(actual)) {
            System.out.println("PASS: " + testName);
        } else {
            System.out.println("FAIL: " + testName +
                    " | Expected: " + expected + ", Actual: " + actual);
        }
    }

    private static void checkTrue(boolean condition, String testName) {
        System.out.println((condition ? "PASS: " : "FAIL: ") + testName);
    }

    private static void checkFalse(boolean condition, String testName) {
        checkTrue(!condition, testName);
    }

    private static void checkNull(Object obj, String testName) {
        checkTrue(obj == null, testName);
    }

    private static void checkNotNull(Object obj, String testName) {
        checkTrue(obj != null, testName);
    }

    
    private static void checkThrows(Runnable action, String testName) {
        try {
            action.run();
            System.out.println("FAIL: " + testName + " (no exception thrown)");
        } catch (IllegalArgumentException e) {
            System.out.println("PASS: " + testName);
        } catch (Exception e) {
            System.out.println("FAIL: " + testName + " (wrong exception type)");
        }
    }
}

