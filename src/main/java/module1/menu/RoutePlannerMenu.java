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
            System.out.print("\nChoice(1-7): ");

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



    private void locationManagementMenu() {
        while (true) {
            clearConsole();
            printHeader("LOCATION MANAGEMENT");
            System.out.println("1. Add Location");
            System.out.println("2. Remove Location");
            System.out.println("3. Update Location");
            System.out.println("4. Search Location");
            System.out.println("5. List All Locations");
            System.out.println("6. View Location Details");
            System.out.println("7. Sync Tree & Graph");
            System.out.println("8. Back");
            System.out.print("\nChoice(1-8): ");

            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1": addLocation(); break;
                case "2": removeLocation(); break;
                case "3": updateLocation(); break;
                case "4": searchLocation(); break;
                case "5": listAllLocations(); break;
                case "6": viewLocationDetails(); break;
                case "7": syncTreeAndGraph(); break;
                case "8": return;
                default: System.out.println("Invalid choice."); pause();
            }
        }
    }

    private void addLocation() {
        clearConsole();
        printHeader("ADD LOCATION");
        try {
            String id;
            while (true) {
                System.out.print("ID (e.g., L001): ");
                id = scanner.nextLine().trim().toUpperCase();
                if (!validator.isValidLocationId(id)) {
                    System.out.println("Invalid format. Use L followed by 3 digits.");
                    continue;
                }
                if (locationTree.search(id) != null) {
                    System.out.println("ID already exists.");
                    continue;
                }
                break;
            }

            System.out.print("Name: ");
            String name = scanner.nextLine().trim();
            if (!validator.isValidName(name)) {
                System.out.println("Name must be 2–50 chars.");
                pause(); return;
            }
            System.out.print("Type: ");
            String type = scanner.nextLine().trim();
            double x = validator.getDoubleInput(scanner, "X coordinate: ", -1000, 1000);
            double y = validator.getDoubleInput(scanner, "Y coordinate: ", -1000, 1000);

            Location loc = new Location(id, name, type, x, y);
            locationTree.insert(loc);
            cityGraph.addLocation(loc);
            System.out.println("Location added.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        pause();
    }
    
    
    private void removeLocation() {
        clearConsole();
        printHeader("REMOVE LOCATION");
        System.out.print("Location ID: ");
        String id = scanner.nextLine().trim().toUpperCase();
        Location loc = locationTree.search(id);
        if (loc == null) {
            System.out.println("Not found.");
            pause(); return;
        }
        System.out.println("Found: " + loc);
        System.out.print("Confirm removal? (y/n): ");
        if (scanner.nextLine().trim().equalsIgnoreCase("y")) {
            locationTree.delete(id);
            cityGraph.removeLocation(id);
            System.out.println("Removed.");
        }
        pause();
    }

    private void updateLocation() {
        clearConsole();
        printHeader("UPDATE LOCATION");
        System.out.print("Location ID: ");
        String id = scanner.nextLine().trim().toUpperCase();
        Location old = locationTree.search(id);
        if (old == null) {
            System.out.println("Not found.");
            pause(); return;
        }
        System.out.println("Current: " + old);
        System.out.println("Leave blank to keep current value.");

        System.out.print("New name [" + old.getName() + "]: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) name = old.getName();

        System.out.print("New type [" + old.getType() + "]: ");
        String type = scanner.nextLine().trim();
        if (type.isEmpty()) type = old.getType();

        double x = validator.getDoubleInput(scanner, "New X [" + old.getX() + "]: ", -1000, 1000, old.getX());
        double y = validator.getDoubleInput(scanner, "New Y [" + old.getY() + "]: ", -1000, 1000, old.getY());

        Location updated = new Location(id, name, type, x, y);
        locationTree.delete(id);
        locationTree.insert(updated);
        cityGraph.updateLocation(id, updated);
        System.out.println("Updated.");
        pause();
    }

    private void searchLocation() {
        clearConsole();
        printHeader("SEARCH LOCATION");
        System.out.print("Location ID: ");
        String id = scanner.nextLine().trim().toUpperCase();
        Location loc = locationTree.search(id);
        if (loc != null) {
            System.out.println("Found:");
            System.out.println(loc);
            boolean inGraph = cityGraph.hasLocation(id);
            System.out.println("In graph: " + (inGraph ? "Yes" : "No"));
        } else {
            System.out.println("Not found.");
        }
        pause();
    }

    private void listAllLocations() {
        clearConsole();
        printHeader("ALL LOCATIONS");
        if (cityGraph.isEmpty()) {
            System.out.println("No locations.");
        } else {
            List<Location> list = cityGraph.getAllLocations();
            list.sort((a,b) -> a.getId().compareTo(b.getId()));
            System.out.printf("%-6s %-20s %-12s %-10s%n", "ID", "Name", "Type", "Coords");
            System.out.println("-".repeat(60));
            for (Location loc : list) {
                System.out.printf("%-6s %-20s %-12s (%.1f,%.1f)%n",
                    loc.getId(), loc.getName(), loc.getType(), loc.getX(), loc.getY());
            }
        }
        pause();
    }

    private void viewLocationDetails() {
        clearConsole();
        printHeader("LOCATION DETAILS");
        System.out.print("Location ID: ");
        String id = scanner.nextLine().trim().toUpperCase();
        if (!cityGraph.hasLocation(id)) {
            System.out.println("Not found in graph.");
        } else {
            cityGraph.displayLocationDetails(id);
        }
        pause();
    }

    private void syncTreeAndGraph() {
        clearConsole();
        printHeader("SYNC TREE & GRAPH");
        // Simple consistency check – if graph has location not in tree, add it.
        for (Location loc : cityGraph.getAllLocations()) {
            if (locationTree.search(loc.getId()) == null) {
                locationTree.insert(loc);
                System.out.println("Added to tree: " + loc.getId());
            }
        }
        System.out.println("Sync complete.");
        pause();
    }




    private void roadManagementMenu() {
        while (true) {
            clearConsole();
            printHeader("ROAD MANAGEMENT");
            System.out.println("1. Add Road");
            System.out.println("2. Remove Road");
            System.out.println("3. Update Traffic");
            System.out.println("4. List All Roads");
            System.out.println("5. View Location Connections");
            System.out.println("6. Back");
            System.out.print("\nChoice (1-6): ");

            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1": addRoad(); break;
                case "2": removeRoad(); break;
                case "3": updateTraffic(); break;
                case "4": listAllRoads(); break;
                case "5": viewLocationConnections(); break;
                case "6": return;
                default: System.out.println("Invalid choice."); pause();
            }
        }
    }

    private void addRoad() {
        clearConsole();
        printHeader("ADD ROAD");
        System.out.print("Road ID (e.g., R001): ");
        String roadId = scanner.nextLine().trim().toUpperCase();
        if (!validator.isValidRoadId(roadId)) {
            System.out.println("Invalid format.");
            pause(); return;
        }
        System.out.print("From Location ID: ");
        String fromId = scanner.nextLine().trim().toUpperCase();
        if (!cityGraph.hasLocation(fromId)) {
            System.out.println("From location not found.");
            pause(); return;
        }
        System.out.print("To Location ID: ");
        String toId = scanner.nextLine().trim().toUpperCase();
        if (!cityGraph.hasLocation(toId)) {
            System.out.println("To location not found.");
            pause(); return;
        }
        if (fromId.equals(toId)) {
            System.out.println("Cannot connect to itself.");
            pause(); return;
        }
        if (cityGraph.roadExists(fromId, toId)) {
            System.out.println("Road already exists.");
            pause(); return;
        }
        int distance = validator.getIntInput(scanner, "Distance (m): ", 1, 10000);
        System.out.print("Road type (Street/Avenue/Highway/Alley): ");
        String type = scanner.nextLine().trim();
        int traffic = validator.getIntInput(scanner, "Traffic (1-5): ", 1, 5);

        if (cityGraph.addRoad(roadId, fromId, toId, distance, type, traffic)) {
            System.out.println("Road added.");
        }
        pause();
    }

    private void removeRoad() {
        clearConsole();
        printHeader("REMOVE ROAD");
        System.out.print("From Location ID: ");
        String fromId = scanner.nextLine().trim().toUpperCase();
        System.out.print("To Location ID: ");
        String toId = scanner.nextLine().trim().toUpperCase();
        if (cityGraph.removeRoad(fromId, toId)) {
            System.out.println("Road removed.");
        } else {
            System.out.println("Road not found.");
        }
        pause();
    }

    private void updateTraffic() {
        clearConsole();
        printHeader("UPDATE TRAFFIC");
        System.out.print("From Location ID: ");
        String fromId = scanner.nextLine().trim().toUpperCase();
        System.out.print("To Location ID: ");
        String toId = scanner.nextLine().trim().toUpperCase();
        if (!cityGraph.roadExists(fromId, toId)) {
            System.out.println("Road does not exist.");
            pause(); return;
        }
        int newTraffic = validator.getIntInput(scanner, "New traffic (1-5): ", 1, 5);
        // Simplified: remove and re-add with new traffic
        Road old = null;
        for (Road r : cityGraph.getConnectedRoads(fromId)) {
            if (r.getDestination().getId().equals(toId)) old = r;
        }
        if (old != null) {
            cityGraph.removeRoad(fromId, toId);
            cityGraph.addRoad(old.getId().replace("_F",""), fromId, toId,
                old.getDistance(), old.getRoadType(), newTraffic);
            System.out.println("Traffic updated.");
        }
        pause();
    }

    private void listAllRoads() {
        clearConsole();
        printHeader("ALL ROADS");
        List<Road> roads = cityGraph.getAllRoads();
        if (roads.isEmpty()) {
            System.out.println("No roads.");
        } else {
            Set<String> shown = new HashSet<>();
            System.out.printf("%-6s %-20s %-20s %-8s %-10s %-6s%n", "ID", "From", "To", "Dist", "Type", "Traffic");
            System.out.println("-".repeat(80));
            for (Road r : roads) {
                if (r.getId().endsWith("_F") && !shown.contains(r.getId())) {
                    System.out.printf("%-6s %-20s %-20s %-8d %-10s %-6d%n",
                        r.getId().replace("_F",""),
                        r.getSource().getName(),
                        r.getDestination().getName(),
                        r.getDistance(),
                        r.getRoadType(),
                        r.getTrafficLevel());
                    shown.add(r.getId());
                }
            }
        }
        pause();
    }

    private void viewLocationConnections() {
        clearConsole();
        printHeader("LOCATION CONNECTIONS");
        System.out.print("Location ID: ");
        String id = scanner.nextLine().trim().toUpperCase();
        cityGraph.displayLocationDetails(id);
        pause();
    }



    private void graphOperationsMenu() {
        while (true) {
            clearConsole();
            printHeader("GRAPH OPERATIONS");
            System.out.println("1. BFS Traversal");
            System.out.println("2. DFS Traversal");
            System.out.println("3. Display Graph");
            System.out.println("4. Check Connectivity");
            System.out.println("5. Generate Random Graph");
            System.out.println("6. Back");
            System.out.print("\nChoice (1-6): ");

            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1": bfsTraversal(); break;
                case "2": dfsTraversal(); break;
                case "3": cityGraph.displayGraph(); pause(); break;
                case "4": checkConnectivity(); break;
                case "5": generateRandomGraph(); break;
                case "6": return;
                default: System.out.println("Invalid."); pause();
            }
        }
    }

    private void bfsTraversal() {
        clearConsole();
        printHeader("BFS TRAVERSAL");
        System.out.print("Start location ID: ");
        String start = scanner.nextLine().trim().toUpperCase();
        if (!cityGraph.hasLocation(start)) {
            System.out.println("Not found.");
        } else {
            graphTraversal.bfsTraversal(start);
        }
        pause();
    }

    private void dfsTraversal() {
        clearConsole();
        printHeader("DFS TRAVERSAL");
        System.out.print("Start location ID: ");
        String start = scanner.nextLine().trim().toUpperCase();
        if (!cityGraph.hasLocation(start)) {
            System.out.println("Not found.");
        } else {
            graphTraversal.dfsTraversal(start);
        }
        pause();
    }

    private void checkConnectivity() {
        clearConsole();
        printHeader("CONNECTIVITY");
        // Simple connectivity check using BFS from first location
        List<Location> all = cityGraph.getAllLocations();
        if (all.isEmpty()) {
            System.out.println("Graph empty.");
        } else {
            List<Location> visited = graphTraversal.bfsTraversal(all.get(0).getId());
            if (visited.size() == all.size()) {
                System.out.println("Graph is fully connected.");
            } else {
                System.out.println("Graph has " + (all.size() - visited.size()) + " unreachable locations.");
            }
        }
        pause();
    }

    private void generateRandomGraph() {
        clearConsole();
        printHeader("GENERATE RANDOM GRAPH");
        System.out.print("Number of locations (3-20): ");
        int n = validator.getIntInput(scanner, "", 3, 20);
        System.out.print("Connection probability (10-90%): ");
        int prob = validator.getIntInput(scanner, "", 10, 90);

        // Clear existing
        locationTree.clear();
        cityGraph = new CityGraph();
        graphTraversal = new GraphTraversal(cityGraph);

        Random rand = new Random();
        String[] types = {"Park","Hospital","School","Mall","Residential"};
        for (int i = 1; i <= n; i++) {
            String id = String.format("L%03d", i);
            String name = "Loc" + i;
            String type = types[rand.nextInt(types.length)];
            double x = rand.nextDouble() * 100;
            double y = rand.nextDouble() * 100;
            Location loc = new Location(id, name, type, x, y);
            locationTree.insert(loc);
            cityGraph.addLocation(loc);
        }
        List<Location> locs = cityGraph.getAllLocations();
        for (int i = 0; i < locs.size(); i++) {
            for (int j = i+1; j < locs.size(); j++) {
                if (rand.nextInt(100) < prob) {
                    String rid = "R" + locs.get(i).getId().substring(1) + locs.get(j).getId().substring(1);
                    int dist = 100 + rand.nextInt(900);
                    String rtype = rand.nextBoolean() ? "Street" : "Avenue";
                    int traffic = 1 + rand.nextInt(5);
                    cityGraph.addRoad(rid, locs.get(i).getId(), locs.get(j).getId(), dist, rtype, traffic);
                }
            }
        }
        System.out.println("Random graph generated.");
        pause();
    }



    private void treeOperationsMenu() {
        while (true) {
            clearConsole();
            printHeader("TREE OPERATIONS");
            System.out.println("1. Display Tree Structure");
            System.out.println("2. In-Order Traversal");
            System.out.println("3. Tree Statistics");
            System.out.println("4. Check Balance");
            System.out.println("5. Back");
            System.out.print("\nChoice (1-5): ");

            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1": displayTreeStructure(); break;
                case "2": locationTree.displayInOrder(); pause(); break;
                case "3": displayTreeStats(); break;
                case "4": checkTreeBalance(); break;
                case "5": return;
                default: System.out.println("Invalid."); pause();
            }
        }
    }

    private void displayTreeStructure() {
        clearConsole();
        printHeader("TREE STRUCTURE");
        if (locationTree.isEmpty()) {
            System.out.println("Tree empty.");
        } else {
            System.out.println("Size: " + locationTree.getSize());
            System.out.println("Height: " + locationTree.getHeight());
            System.out.println("In-order traversal:");
            locationTree.displayInOrder();
        }
        pause();
    }

    private void displayTreeStats() {
        clearConsole();
        printHeader("TREE STATISTICS");
        if (locationTree.isEmpty()) {
            System.out.println("Tree empty.");
        } else {
            int size = locationTree.getSize();
            int height = locationTree.getHeight();
            boolean balanced = locationTree.isBalanced();
            System.out.println("Size: " + size);
            System.out.println("Height: " + height);
            System.out.println("Balanced: " + (balanced ? "Yes" : "No"));
            System.out.printf("Ideal height: %.0f%n", Math.ceil(Math.log(size+1)/Math.log(2)));
        }
        pause();
    }

    private void checkTreeBalance() {
        clearConsole();
        printHeader("BALANCE CHECK");
        if (locationTree.isEmpty()) {
            System.out.println("Tree empty.");
        } else {
            boolean balanced = locationTree.isBalanced();
            System.out.println(balanced ? "Tree is balanced." : "Tree is NOT balanced.");
        }
        pause();
    }




    private void systemStatisticsMenu() {
        while (true) {
            clearConsole();
            printHeader("SYSTEM STATISTICS");
            System.out.println("1. Graph Overview");
            System.out.println("2. Tree Statistics");
            System.out.println("3. Connectivity Summary");
            System.out.println("4. Back");
            System.out.print("\nChoice(1-4): ");

            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1": displayGraphOverview(); break;
                case "2": displayTreeStats(); break;   // reuse existing method
                case "3": displayConnectivitySummary(); break;
                case "4": return;
                default: System.out.println("Invalid choice."); pause();
            }
        }
    }

    private void displayGraphOverview() {
        clearConsole();
        printHeader("GRAPH OVERVIEW");
        int locCount = cityGraph.getLocationCount();
        int roadCount = cityGraph.getRoadCount(); // bidirectional count

        System.out.println("Locations: " + locCount);
        System.out.println("Roads (bidirectional): " + roadCount);

        if (locCount > 0) {
            double avgDegree = (double) roadCount / locCount;
            System.out.printf("Average degree: %.2f%n", avgDegree);

            // Count isolated locations (no roads)
            int isolated = 0;
            for (Location loc : cityGraph.getAllLocations()) {
                if (cityGraph.getConnectedRoads(loc.getId()).isEmpty()) {
                    isolated++;
                }
            }
            System.out.println("Isolated locations: " + isolated);

            // Location type distribution
            Map<String, Integer> typeCount = new HashMap<>();
            for (Location loc : cityGraph.getAllLocations()) {
                typeCount.put(loc.getType(), typeCount.getOrDefault(loc.getType(), 0) + 1);
            }
            System.out.println("\nType distribution:");
            for (Map.Entry<String, Integer> e : typeCount.entrySet()) {
                System.out.printf("   %-15s: %d%n", e.getKey(), e.getValue());
            }
        }
        pause();
    }

    private void displayConnectivitySummary() {
        clearConsole();
        printHeader("CONNECTIVITY SUMMARY");
        List<Location> all = cityGraph.getAllLocations();
        if (all.isEmpty()) {
            System.out.println("Graph is empty.");
            pause();
            return;
        }

        // BFS from first location to see how many are reachable
        String startId = all.get(0).getId();
        List<Location> visited = graphTraversal.bfsTraversal(startId);
        int reachable = visited.size();
        int total = all.size();

        System.out.println("Total locations: " + total);
        System.out.println("Locations reachable from " + startId + ": " + reachable);

        if (reachable == total) {
            System.out.println("Graph is fully connected.");
        } else {
            System.out.println("Graph has " + (total - reachable) + " unreachable locations.");
            System.out.println("(Try BFS from other starting points to explore components.)");
        }
        pause();
    }




    private void demoDataMenu() {
        while (true) {
            clearConsole();
            printHeader("DEMO DATA");
            System.out.println("Demo data loaded: " + (demoDataLoaded ? "Yes" : "No"));
            System.out.println("1. Load Standard Demo");
            System.out.println("2. Clear All Data");
            System.out.println("3. Back");
            System.out.print("\nChoice (1-3): ");

            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    loadDemoData();
                    break;
                case "2":
                    clearAllData();
                    break;
                case "3":
                    return;
                default:
                    System.out.println("Invalid choice.");
                    pause();
            }
        }
    }

    private void loadDemoData() {
        clearConsole();
        printHeader("LOAD DEMO DATA");
        System.out.print("This will replace all current data. Continue? (y/n): ");
        if (!scanner.nextLine().trim().equalsIgnoreCase("y")) {
            System.out.println("Cancelled.");
            pause();
            return;
        }
        initializeSampleData();
        demoDataLoaded = true;
        System.out.println("Demo data loaded.");
        pause();
    }

    private void clearAllData() {
        clearConsole();
        printHeader("CLEAR ALL DATA");
        System.out.print("Are you sure? (y/n): ");
        if (!scanner.nextLine().trim().equalsIgnoreCase("y")) {
            System.out.println("Cancelled.");
            pause();
            return;
        }
        locationTree.clear();
        cityGraph = new CityGraph();
        graphTraversal = new GraphTraversal(cityGraph);
        demoDataLoaded = false;
        System.out.println("All data cleared.");
        pause();
    }
}


