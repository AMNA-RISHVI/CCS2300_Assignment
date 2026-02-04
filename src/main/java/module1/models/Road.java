package module1.models;

public class Road {
    private String id;
    private Location source;
    private Location destination;
    private int distance; // in meters
    private String roadType; // "Highway", "Street", "Alley"
    private int trafficLevel; // 1-5 scale (1=lowest traffic)
    

    public Road(String id, Location source, Location destination, 
                int distance, String roadType, int trafficLevel) {
        this.id = id;
        this.source = source;
        this.destination = destination;
        this.distance = distance;
        this.roadType = roadType;
        this.trafficLevel = trafficLevel;
    }
    
    // Getters
    public String getId() { return id; }
    public Location getSource() { return source; }
    public Location getDestination() { return destination; }
    public int getDistance() { return distance; }
    public String getRoadType() { return roadType; }
    public int getTrafficLevel() { return trafficLevel; }
    
    
    public int getWeightedDistance() {
        return distance * trafficLevel;
    }
    
    
    @Override
    public String toString() {
        return String.format("%s -> %s: %dm (%s)", 
            source.getName(), destination.getName(), distance, roadType);
    }
}