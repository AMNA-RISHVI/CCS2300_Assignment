package module1.graphs;

import module1.models.Location;
import module1.models.Road;
import java.util.*;

public class CityGraph {
    
    // Adjacency list: Map each location to its connected roads
    private Map<Location, List<Road>> adjacencyList;
    
    // Quick lookup for locations by ID
    private Map<String, Location> locationRegistry;
    
    // Road registry for quick access
    private Map<String, Road> roadRegistry;
    
    // Statistics
    private int totalLocations;
    private int totalRoads;
    
   
    public CityGraph() {
        adjacencyList = new HashMap<>();
        locationRegistry = new HashMap<>();
        roadRegistry = new HashMap<>();
        totalLocations = 0;
        totalRoads = 0;
    }
    
    
    public int getLocationCount() {
        return totalLocations;
    }
    
    
    public int getRoadCount() {
        return totalRoads;
    }
    
   
    public boolean isEmpty() {
        return totalLocations == 0;
    }
    

    public List<Location> getAllLocations() {
        return new ArrayList<>(locationRegistry.values());
    }
    

    public List<Road> getAllRoads() {
        return new ArrayList<>(roadRegistry.values());
    }
    

    public boolean hasLocation(String locationId) {
        return locationRegistry.containsKey(locationId);
    }
    

    public Location getLocation(String locationId) {
        return locationRegistry.get(locationId);
    }
    

    public Road getRoad(String roadId) {
        return roadRegistry.get(roadId);
    }
    
    
    public boolean addLocation(Location location) {
        if (location == null) {
            throw new IllegalArgumentException("Cannot add null location");
        }
        
        String locationId = location.getId();
        
        // Check if location already exists
        if (hasLocation(locationId)) {
            System.out.println("Location already exists: " + location.getName());
            return false;
        }
        
        // Add to adjacency list and registry
        adjacencyList.put(location, new ArrayList<>());
        locationRegistry.put(locationId, location);
        totalLocations++;
        
        System.out.println("Added location: " + location.getName() + " (ID: " + locationId + ")");
        return true;
    }
    
    
    public int addLocations(Location[] locations) {
        if (locations == null) return 0;
        
        int addedCount = 0;
        for (Location location : locations) {
            if (addLocation(location)) {
                addedCount++;
            }
        }
        return addedCount;
    }
    
    
    public boolean removeLocation(String locationId) {
        if (!hasLocation(locationId)) {
            System.out.println("Location not found: " + locationId);
            return false;
        }
        
        Location location = getLocation(locationId);
        
        // Remove all roads connected to this location
        List<Road> connectedRoads = new ArrayList<>(adjacencyList.get(location));
        for (Road road : connectedRoads) {
            removeRoad(road.getSource().getId(), road.getDestination().getId());
        }
        
        // Remove from adjacency list and registry
        adjacencyList.remove(location);
        locationRegistry.remove(locationId);
        totalLocations--;
        
        System.out.println("Removed location: " + location.getName() + " and all connected roads");
        return true;
    }
    
    
    public int removeLocations(String[] locationIds) {
        if (locationIds == null) return 0;
        
        int removedCount = 0;
        for (String locationId : locationIds) {
            if (removeLocation(locationId)) {
                removedCount++;
            }
        }
        return removedCount;
    }
    
    public boolean updateLocation(String locationId, Location newLocation) {
        if (!hasLocation(locationId)) {
            return false;
        }
    
        Location oldLocation = getLocation(locationId);
    
        // Remove old location
        adjacencyList.remove(oldLocation);
        locationRegistry.remove(locationId);
    
        // Add new location
        adjacencyList.put(newLocation, new ArrayList<>());
        locationRegistry.put(locationId, newLocation);
    
        System.out.println("Updated location: " + oldLocation.getName() + " -> " + newLocation.getName());
        return true;
    }
    
    
    public boolean addRoad(String roadId, String fromId, String toId, int distance, String roadType, int trafficLevel) {
        // Validate inputs
        if (!hasLocation(fromId) || !hasLocation(toId)) {
            System.out.println("One or both locations not found");
            return false;
        }
    
        if (fromId.equals(toId)) {
           System.out.println("Cannot create road from a location to itself");
           return false;
        }
    
        if (distance <= 0) {
            System.out.println("Road distance must be positive");
            return false;
        }
    
        if (trafficLevel < 1 || trafficLevel > 5) {
            System.out.println("Traffic level must be between 1 and 5");
            return false;
        }
        
        Location from = getLocation(fromId);
        Location to = getLocation(toId);
        
        // Check if road already exists
        if (roadExists(fromId, toId)) {
            System.out.println("Road already exists between these locations");
            return false;
        }
        
        // Create forward road
        Road forwardRoad = new Road(roadId + "_F", from, to, distance, roadType, trafficLevel);
    
        // Create reverse road (for bidirectional)
        Road reverseRoad = new Road(roadId + "_R", to, from, distance, roadType, trafficLevel);
    
       // Add to adjacency list
       adjacencyList.get(from).add(forwardRoad);
       adjacencyList.get(to).add(reverseRoad);
    
       // Add to registries
       roadRegistry.put(forwardRoad.getId(), forwardRoad);
       roadRegistry.put(reverseRoad.getId(), reverseRoad);
    
       totalRoads += 2; // Count both directions
       
       System.out.println("Added road: " + from.getName() + " <-> " + to.getName() + " (" + distance + "m, " + roadType + ")");
       return true;
    }
    
    
    public boolean removeRoad(String fromId, String toId) {
        if (!hasLocation(fromId) || !hasLocation(toId)) {
            return false;
        }
        
        Location from = getLocation(fromId);
        Location to = getLocation(toId);
        
        // Find and remove forward road
        List<Road> fromRoads = adjacencyList.get(from);
        Road forwardRoad = null;
        for (Road road : fromRoads) {
            if (road.getDestination().getId().equals(toId)) {
                forwardRoad = road;
                break;
            }
        }
        
        // Find and remove reverse road
        List<Road> toRoads = adjacencyList.get(to);
        Road reverseRoad = null;
        for (Road road : toRoads) {
            if (road.getDestination().getId().equals(fromId)) {
                reverseRoad = road;
                break;
            }
        }
        
        if (forwardRoad == null || reverseRoad == null) {
            System.out.println("Road does not exist between these locations");
            return false;
        }
    
        // Remove from adjacency list
        fromRoads.remove(forwardRoad);
        toRoads.remove(reverseRoad);
    
        // Remove from registries
        roadRegistry.remove(forwardRoad.getId());
        roadRegistry.remove(reverseRoad.getId());
    
        totalRoads -= 2; // Remove both directions
    
        System.out.println("Removed road between " + from.getName() + " and " + to.getName());
        return true;
    }
    
    
    public boolean roadExists(String fromId, String toId) {
        if (!hasLocation(fromId) || !hasLocation(toId)) {
            return false;
        }
        
        Location from = getLocation(fromId);
        List<Road> roads = adjacencyList.get(from);
        
        for (Road road : roads) {
            if (road.getDestination().getId().equals(toId)) {
                return true;
            }
        }
        return false;
    }
    
    
    public List<Road> getConnectedRoads(String locationId) {
        if (!hasLocation(locationId)) {
            return new ArrayList<>();
        }
        
        Location location = getLocation(locationId);
        return new ArrayList<>(adjacencyList.get(location));
    }
    
    
    public List<Location> getNeighbors(String locationId) {
        List<Location> neighbors = new ArrayList<>();
        
        if (!hasLocation(locationId)) {
            return neighbors;
        }
        
        List<Road> roads = getConnectedRoads(locationId);
        for (Road road : roads) {
            neighbors.add(road.getDestination());
        }
        
        return neighbors;
    }
    
    public boolean updateTraffic(String fromId, String toId, int newTrafficLevel) {
        if (newTrafficLevel < 1 || newTrafficLevel > 5) {
            System.out.println("Traffic level must be between 1 and 5");
            return false;
        }
        
        if (!roadExists(fromId, toId)) {
            return false;
        }
        
        // This would require modifying Road class to have setTrafficLevel method
        // For now, we remove and recreate the road
        // In a real implementation, we would have mutable traffic levels
    
        System.out.println("Traffic update not implemented in this version");
        return false;
    }
    
    
    public void displayGraph() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("SMART CITY GRAPH OVERVIEW");
        System.out.println("=".repeat(60));
    
        // Graph statistics
        System.out.printf("Statistics:%n");
        System.out.printf("Locations: %d%n", totalLocations);
        System.out.printf("Roads: %d%n", totalRoads);
        System.out.printf("Density: %.2f roads per location%n", totalLocations > 0 ? (double) totalRoads / totalLocations : 0);
    
        System.out.println("\n Locations:");
        System.out.println("-".repeat(60));
    
        if (totalLocations == 0) {
            System.out.println("No locations added yet.");
            System.out.println("=".repeat(60));
            return;
        }
    
        // Display all locations
        List<Location> locations = getAllLocations();
        locations.sort((a, b) -> a.getId().compareTo(b.getId()));
    
        for (int i = 0; i < locations.size(); i++) {
            Location loc = locations.get(i);
            System.out.printf("%2d. %-6s | %-20s | %-12s | (%3.0f, %3.0f)%n",
                i + 1, loc.getId(), loc.getName(), loc.getType(), 
                loc.getX(), loc.getY());
        }
    
        System.out.println("\n Road Network:");
        System.out.println("-".repeat(60));
    
        if (totalRoads == 0) {
           System.out.println("No roads added yet.");
           System.out.println("=".repeat(60));
           return;
        }
    
        // Display all roads (only one direction to avoid duplication)
        int roadNumber = 1;
        Set<String> displayedRoads = new HashSet<>();
    
        for (Location from : locations) {
            List<Road> roads = adjacencyList.get(from);
          
            if (!roads.isEmpty()) {
                System.out.printf("From %s (%s):%n", from.getName(), from.getId());
            
                for (Road road : roads) {
                    // Only display forward roads (ending with "_F")
                    if (road.getId().endsWith("_F")) {
                        String roadKey = from.getId() + "-" + road.getDestination().getId();
                    
                        if (!displayedRoads.contains(roadKey)) {
                            String trafficSymbol = getTrafficSymbol(road.getTrafficLevel());
                            System.out.printf("%2d. -> %-20s %5d m  [%-8s] %s Traffic: %d/5%n",
                                roadNumber++,
                                road.getDestination().getName(),
                                road.getDistance(),
                                road.getRoadType(),
                                trafficSymbol,
                                road.getTrafficLevel());
                        
                            displayedRoads.add(roadKey);
                        }
                    }
                }
                System.out.println();
            }
        }
    
        // Connectivity analysis
        System.out.println("Connectivity Analysis:");
        System.out.println("-".repeat(60));
    
        if (totalLocations > 0) {
            Location sampleLocation = locations.get(0);
            int reachableCount = countReachableLocations(sampleLocation.getId());
        
            System.out.printf("From %s, you can reach %d of %d locations%n",
                sampleLocation.getName(), reachableCount, totalLocations);
        
            if (reachableCount == totalLocations) {
                System.out.println("Graph is fully connected!");
            } else if (reachableCount > 1) {
                System.out.printf("Graph has %d disconnected components%n",
                    totalLocations - reachableCount + 1);
            } else {
                System.out.println("Graph is disconnected (isolated locations)");
            }
        }
    
        System.out.println("=".repeat(60));
    }
    
    
    public void displayLocationDetails(String locationId) {
        if (!hasLocation(locationId)) {
            System.out.println("Location not found: " + locationId);
            return;
        }
    
        Location location = getLocation(locationId);
        List<Road> roads = getConnectedRoads(locationId);
    
        System.out.println("\n" + "=".repeat(50));
        System.out.println("LOCATION DETAILS");
        System.out.println("=".repeat(50));
    
        System.out.printf("ID: %s%n", location.getId());
        System.out.printf("Name: %s%n", location.getName());
        System.out.printf("Type: %s%n", location.getType());
        System.out.printf("Coordinates: (%.1f, %.1f)%n", location.getX(), location.getY());
    
        System.out.println("\nConnections:");
        System.out.println("-".repeat(50));
    
        if (roads.isEmpty()) {
            System.out.println("No roads connected to this location");
        } else {
            // Separate incoming and outgoing roads
            List<Road> outgoing = new ArrayList<>();
            List<Road> incoming = new ArrayList<>();
        
            for (Road road : roads) {
                if (road.getSource().getId().equals(locationId)) {
                    outgoing.add(road);
                } else {
                    incoming.add(road);
                }
            }
        
            // Display outgoing roads
            if (!outgoing.isEmpty()) {
                System.out.println("  Roads FROM here:");
                for (Road road : outgoing) {
                    String trafficSymbol = getTrafficSymbol(road.getTrafficLevel());
                    System.out.printf("   -> %-15s %5d m  [%-8s] %s%n",
                        road.getDestination().getName(),
                        road.getDistance(),
                        road.getRoadType(),
                        trafficSymbol);
                }
            }
        
            // Display incoming roads
            if (!incoming.isEmpty()) {
                System.out.println("\n  Roads TO here:");
                for (Road road : incoming) {
                    String trafficSymbol = getTrafficSymbol(road.getTrafficLevel());
                    System.out.printf("   <- %-15s %5d m  [%-8s] %s%n",
                        road.getSource().getName(),
                        road.getDistance(),
                        road.getRoadType(),
                        trafficSymbol);
                }
            }
        
            System.out.printf("%n  Total connections: %d%n", roads.size());
        }
    
        System.out.println("=".repeat(50));
    }


    private String getTrafficSymbol(int trafficLevel) {
        switch (trafficLevel) {
            case 1: return "green - light";
            case 2: return "yellow - moderate";
            case 3: return "orange - heavy";
            case 4: return "red - very heavy";
            case 5: return "stop - gridlock";
            default: return "Invalide";
        }
    }


    private int countReachableLocations(String startId) {
        if (!hasLocation(startId)) return 0;
    
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
    
        queue.add(startId);
        visited.add(startId);
    
        while (!queue.isEmpty()) {
            String currentId = queue.poll();
            List<Road> roads = getConnectedRoads(currentId);
        
            for (Road road : roads) {
                String neighborId = road.getDestination().getId();
                if (!visited.contains(neighborId)) {
                    visited.add(neighborId);
                    queue.add(neighborId);
                }
            }
        }
    
        return visited.size();
    }


    public String generateReport() {
        StringBuilder report = new StringBuilder();
    
        report.append("CITY GRAPH REPORT\n");
        report.append("=================\n\n");
    
        report.append("Summary:\n");
        report.append(String.format("Total locations: %d%n", totalLocations));
        report.append(String.format("Total roads: %d%n", totalRoads));
        report.append(String.format("Road density: %.2f%n", 
            totalLocations > 0 ? (double) totalRoads / totalLocations : 0));
    
        report.append("\nLocation Types:\n");
        Map<String, Integer> typeCount = new HashMap<>();
        for (Location loc : getAllLocations()) {
            typeCount.put(loc.getType(), typeCount.getOrDefault(loc.getType(), 0) + 1);
        }
    
        for (Map.Entry<String, Integer> entry : typeCount.entrySet()) {
            report.append(String.format("%-15s: %d%n", entry.getKey(), entry.getValue()));
        }
    
        report.append("\nRoad Types:\n");
        Map<String, Integer> roadTypeCount = new HashMap<>();
        for (Road road : getAllRoads()) {
            // Only count forward roads
            if (road.getId().endsWith("_F")) {
                roadTypeCount.put(road.getRoadType(), 
                roadTypeCount.getOrDefault(road.getRoadType(), 0) + 1);
            }
        }
    
        for (Map.Entry<String, Integer> entry : roadTypeCount.entrySet()) {
            report.append(String.format("%-15s: %d%n", entry.getKey(), entry.getValue()));
        }
        return report.toString();
    }
}


