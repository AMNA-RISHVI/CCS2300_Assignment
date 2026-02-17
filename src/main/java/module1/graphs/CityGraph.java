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
        
        System.out.println("✅ Added location: " + location.getName() + " (ID: " + locationId + ")");
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
    
        System.out.println("Updated location: " + oldLocation.getName() + " → " + newLocation.getName());
        return true;
    }
}
