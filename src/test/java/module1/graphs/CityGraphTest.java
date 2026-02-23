package module1.graphs;

import module1.models.Location;

import java.util.List;

public class CityGraphTest {

    public static void main(String[] args) {

        CityGraph graph = new CityGraph();
        GraphTraversal traversal = new GraphTraversal(graph);

        Location locA = new Location("L001", "Central Park", "Park", 0, 0);
        Location locB = new Location("L002", "City Hospital", "Hospital", 2, 3);
        Location locC = new Location("L003", "Main Mall", "Shopping", 5, 2);
        Location locD = new Location("L004", "Tech University", "Education", 7, 8);
        Location locE = new Location("L005", "Railway Station", "Transport", 3, 6);

        /* ---------- Empty graph ---------- */
        check("Empty graph",
                graph.isEmpty() &&
                graph.getLocationCount() == 0 &&
                graph.getRoadCount() == 0);

        /* ---------- Add location ---------- */
        check("Add location",
                graph.addLocation(locA) &&
                graph.getLocationCount() == 1 &&
                graph.hasLocation("L001"));

        /* ---------- Duplicate location ---------- */
        check("Duplicate location rejected",
                !graph.addLocation(new Location("L001", "Duplicate", "Park", 0, 0)) &&
                graph.getLocationCount() == 1);

        /* ---------- Null location ---------- */
        expectException("Add null location", () -> graph.addLocation(null));

        /* ---------- Remove location ---------- */
        graph.addLocation(locB);
        check("Remove location",
                graph.removeLocation("L001") &&
                !graph.hasLocation("L001") &&
                graph.getLocationCount() == 1);

        /* ---------- Add road ---------- */
        graph.addLocation(locA);
        check("Add road",
                graph.addRoad("R001", "L001", "L002", 500, "Street", 2) &&
                graph.getRoadCount() == 2);

        /* ---------- Invalid road ---------- */
        check("Invalid road rejected",
                !graph.addRoad("R002", "L001", "L999", 200, "Street", 2));

        /* ---------- BFS traversal ---------- */
        graph.addLocation(locC);
        graph.addRoad("R003", "L002", "L003", 400, "Street", 2);

        List<Location> bfs = traversal.bfsTraversal("L001");
        check("BFS traversal",
                bfs.size() == 3 && bfs.get(0).getId().equals("L001"));

        /* ---------- DFS traversal ---------- */
        List<Location> dfs = traversal.dfsTraversal("L001");
        check("DFS traversal",
                dfs.contains(locA) && dfs.contains(locB) && dfs.contains(locC));

        /* ---------- Shortest path BFS ---------- */
        graph.addLocation(locD);
        graph.addLocation(locE);
        graph.addRoad("R004", "L001", "L004", 300, "Street", 2);
        graph.addRoad("R005", "L004", "L005", 300, "Street", 2);

        List<Location> path = traversal.findShortestPathBFS("L001", "L005");
        check("Shortest path BFS",
                path.size() == 3 &&
                path.get(0).getId().equals("L001") &&
                path.get(2).getId().equals("L005"));

        /* ---------- No path ---------- */
        CityGraph isolated = new CityGraph();
        isolated.addLocation(locA);
        isolated.addLocation(locB);

        GraphTraversal isoTraversal = new GraphTraversal(isolated);
        check("No path exists",
                isoTraversal.findShortestPathBFS("L001", "L002").isEmpty());

        System.out.println("\n ALL TESTS COMPLETED");
    }

    /* ---------- Helper methods ---------- */

    private static void check(String testName, boolean condition) {
        if (condition) {
            System.out.println("PASS: " + testName);
        } else {
            System.out.println("FAIL: " + testName);
        }
    }

    private static void expectException(String testName, Runnable action) {
        try {
            action.run();
            System.out.println("FAIL: " + testName + " (exception not thrown)");
        } catch (Exception e) {
            System.out.println("PASS: " + testName);
        }
    }
}
