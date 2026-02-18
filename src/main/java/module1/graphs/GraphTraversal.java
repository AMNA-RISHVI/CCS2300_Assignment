package module1.graphs;

import module1.models.Location;
import module1.models.Road;
import java.util.*;

public class GraphTraversal {
    
    private CityGraph cityGraph;
    
    public GraphTraversal(CityGraph cityGraph) {
        if (cityGraph == null) {
            throw new IllegalArgumentException("CityGraph cannot be null");
        }
        this.cityGraph = cityGraph;
    }
    

    public CityGraph getCityGraph() {
        return cityGraph;
    }
    
    
    public void setCityGraph(CityGraph cityGraph) {
        if (cityGraph == null) {
            throw new IllegalArgumentException("CityGraph cannot be null");
        }
        this.cityGraph = cityGraph;
    }
    
    
    private boolean locationExists(String locationId) {
        return cityGraph.hasLocation(locationId);
    }
    
    
    private void validateLocations(String startId, String endId) {
        if (!locationExists(startId)) {
            throw new IllegalArgumentException("Start location not found: " + startId);
        }
        if (endId != null && !locationExists(endId)) {
            throw new IllegalArgumentException("End location not found: " + endId);
        }
    }
    
    
    protected void printPath(List<Location> path, int totalDistance) {
        if (path == null || path.isEmpty()) {
            System.out.println("No path found");
            return;
        }
        
        System.out.println("\n Route Found:");
        System.out.println("═".repeat(40));
        
        for (int i = 0; i < path.size(); i++) {
            Location location = path.get(i);
            System.out.printf("%2d. %-20s (%s)%n", 
                i + 1, location.getName(), location.getType());
            
            if (i < path.size() - 1) {
                System.out.println("    ↓");
            }
        }
        
        System.out.println("═".repeat(40));
        System.out.printf("Total stops: %d | Approx. distance: %dm%n", 
            path.size(), totalDistance);
    }
    
    
    
    public List<Location> bfsTraversal(String startId) {
        validateLocations(startId, null);
        
        System.out.println("\n🚶 BFS Traversal (Queue-based)");
        System.out.println("Starting from: " + cityGraph.getLocation(startId).getName());
        System.out.println("-".repeat(40));
        
        List<Location> visitedOrder = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        Queue<Location> queue = new LinkedList<>();
    
        Location start = cityGraph.getLocation(startId);
        queue.add(start);
        visited.add(startId);
    
        int level = 0;
    
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            System.out.printf("Level %d: ", level);
            
            for (int i = 0; i < levelSize; i++) {
                Location current = queue.poll();
                visitedOrder.add(current);
                
                System.out.print(current.getName());
                if (i < levelSize - 1) System.out.print(", ");
                
                
                // Add neighbors to queue
                List<Road> roads = cityGraph.getConnectedRoads(current.getId());
                for (Road road : roads) {
                    Location neighbor = road.getDestination();
                    if (!visited.contains(neighbor.getId())) {
                        visited.add(neighbor.getId());
                        queue.add(neighbor);
                    }
                }
            }
            System.out.println();
            level++;
        }
    
        System.out.println("-".repeat(40));
        System.out.println("BFS complete. Visited " + visitedOrder.size() + " locations.");
    
        return visitedOrder;
    }
    

    
    //Finds the shortest path (by number of stops) using BFS.
    
    public List<Location> findShortestPathBFS(String startId, String endId) {
        validateLocations(startId, endId);
        
        System.out.println("\n Finding shortest path (BFS)");
        System.out.println("From: " + cityGraph.getLocation(startId).getName());
        System.out.println("To: " + cityGraph.getLocation(endId).getName());
        
        if (startId.equals(endId)) {
            List<Location> path = new ArrayList<>();
            path.add(cityGraph.getLocation(startId));
            System.out.println("Start and end are the same location!");
            return path;
        }
        
        // Queue for BFS, storing paths instead of single nodes
        Queue<List<Location>> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
    
        // Start with path containing only the start location
        List<Location> initialPath = new ArrayList<>();
        initialPath.add(cityGraph.getLocation(startId));
        queue.add(initialPath);
        visited.add(startId);
        
        while (!queue.isEmpty()) {
            List<Location> currentPath = queue.poll();
            Location lastLocation = currentPath.get(currentPath.size() - 1);
            
            // Check if we reached destination
            if (lastLocation.getId().equals(endId)) {
                int totalDistance = calculatePathDistance(currentPath);
                printPath(currentPath, totalDistance);
                return currentPath;
            }
            
            
            // Explore neighbors
            List<Road> roads = cityGraph.getConnectedRoads(lastLocation.getId());
            for (Road road : roads) {
                Location neighbor = road.getDestination();
                
                if (!visited.contains(neighbor.getId())) {
                    visited.add(neighbor.getId());
                    
                    // Create new path by adding neighbor
                    List<Location> newPath = new ArrayList<>(currentPath);
                    newPath.add(neighbor);
                    queue.add(newPath);
                }
            }
        }
        
        System.out.println("\n No path found between the locations");
        return new ArrayList<>();
    }
    
    private int calculatePathDistance(List<Location> path) {
        if (path.size() < 2) return 0;
        
        int totalDistance = 0;
        for (int i = 0; i < path.size() - 1; i++) {
            String fromId = path.get(i).getId();
            String toId = path.get(i + 1).getId();
            
            List<Road> roads = cityGraph.getConnectedRoads(fromId);
            for (Road road : roads) {
                if (road.getDestination().getId().equals(toId)) {
                    totalDistance += road.getDistance();
                    break;
                }
            }
        }
        return totalDistance;
    }
}