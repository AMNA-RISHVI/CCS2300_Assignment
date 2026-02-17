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
       
       System.out.println("✅ Added road: " + from.getName() + " ↔ " + to.getName() + " (" + distance + "m, " + roadType + ")");
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
    
        System.out.println("⚠️ Traffic update not implemented in this version");
        return false;
    }

}


