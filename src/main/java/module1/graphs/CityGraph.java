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
}
