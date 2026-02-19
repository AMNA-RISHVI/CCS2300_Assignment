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


    public void displayMainMenu() {
        while (true) {
            clearConsole();
            printHeader("MAIN MENU");
            System.out.println("1. Location Management");
            System.out.println("2. Road Management");
            System.out.println("3. Graph Operations");
            System.out.println("4. Tree Operations");
            System.out.println("5. System Statistics");
            System.out.println("6. Demo Data");
            System.out.println("7. Exit to Module Selection");
            System.out.print("\nChoice: ");

            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1": locationManagementMenu(); break;
                case "2": roadManagementMenu(); break;
                case "3": graphOperationsMenu(); break;
                case "4": treeOperationsMenu(); break;
                case "5": systemStatisticsMenu(); break;
                case "6": demoDataMenu(); break;
                case "7": return;
                default: System.out.println("Invalid choice."); pause();
            }
        }
    }
    
    private void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                System.out.print("\033[H\033[2J");
        } catch (Exception e) {
            System.out.println("\n".repeat(50));
        }
    }
}

