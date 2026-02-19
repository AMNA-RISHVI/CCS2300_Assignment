package module1.menu;

import module1.models.Location;
import module1.models.Road;
import module1.trees.AVLTree;
import module1.graphs.CityGraph;
import module1.graphs.GraphTraversal;
import module1.utils.InputValidator;
import java.util.*;

/**
 * Main menu interface for Module 1 – Smart City Route Planner.
 * Provides console-based interaction for managing locations, roads,
 * and performing graph traversals.
 */
public class RoutePlannerMenu {

    private Scanner scanner;
    private AVLTree locationTree;
    private CityGraph cityGraph;
    private GraphTraversal graphTraversal;
    private InputValidator validator;
    private boolean demoDataLoaded;

    public RoutePlannerMenu() {
        scanner = new Scanner(System.in);
        locationTree = new AVLTree();
        cityGraph = new CityGraph();
        graphTraversal = new GraphTraversal(cityGraph);
        validator = new InputValidator();
        demoDataLoaded = false;

        System.out.println("\n" + "*".repeat(50));
        System.out.println("   SMART CITY ROUTE PLANNER ");
        System.out.println("*".repeat(50));
        loadDemoDataPrompt();
    }

    private void loadDemoDataPrompt() {
        System.out.print("\nLoad demo data? (y/n): ");
        if (scanner.nextLine().trim().equalsIgnoreCase("y")) {
            initializeSampleData();
            demoDataLoaded = true;
            System.out.println("Demo data loaded.");
        }
        pause();
    }

    private void initializeSampleData() {
        // Simple sample data – 5 locations, 5 roads
        Location[] locs = {
            new Location("L001", "Central Park", "Park", 50, 50),
            new Location("L002", "City Hospital", "Hospital", 30, 70),
            new Location("L003", "Main Mall", "Shopping", 70, 40),
            new Location("L004", "Tech University", "Education", 80, 80),
            new Location("L005", "Railway Station", "Transport", 40, 30)
        };
        for (Location loc : locs) {
            locationTree.insert(loc);
            cityGraph.addLocation(loc);
        }
        cityGraph.addRoad("R001", "L001", "L002", 500, "Street", 2);
        cityGraph.addRoad("R002", "L001", "L003", 1200, "Avenue", 3);
        cityGraph.addRoad("R003", "L002", "L004", 800, "Highway", 1);
        cityGraph.addRoad("R004", "L003", "L005", 600, "Street", 2);
        cityGraph.addRoad("R005", "L004", "L005", 1500, "Highway", 1);
    }

    private void pause() {
        System.out.print("\nPress Enter to continue...");
        scanner.nextLine();
    }

    private void printHeader(String title) {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("   " + title);
        System.out.println("=".repeat(60));
    }

    // Other methods will be added
}