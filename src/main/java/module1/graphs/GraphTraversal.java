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
}