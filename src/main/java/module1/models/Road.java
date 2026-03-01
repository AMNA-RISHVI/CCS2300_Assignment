package module1.models;

public class Road {
    private final String id;
    private final Location source;
    private final Location destination;
    private final int distance; // in meters
    private String roadType; // "Highway", "Street", "Alley"
    private int trafficLevel; // 1-5 scale (1=lowest traffic)
    

    public Road(String id, Location source, Location destination, int distance, String roadType, int trafficLevel) {
        
        validateParameters(id, source, destination, distance, roadType, trafficLevel);

        this.id = id;
        this.source = source;
        this.destination = destination;
        this.distance = distance;
        this.roadType = roadType;
        this.trafficLevel = trafficLevel;
    }


    private void validateParameters(String id, Location source, Location destination,
                                    int distance, String roadType, int trafficLevel) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Road ID cannot be null or empty");
        }
        if (source == null) {
            throw new IllegalArgumentException("Source location cannot be null");
        }
        if (destination == null) {
            throw new IllegalArgumentException("Destination location cannot be null");
        }
        if (source.equals(destination)) {
            throw new IllegalArgumentException("Road cannot connect a location to itself");
        }
        if (distance <= 0) {
            throw new IllegalArgumentException("Road distance must be positive");
        }
        if (roadType == null || roadType.trim().isEmpty()) {
            throw new IllegalArgumentException("Road type cannot be null or empty");
        }
        if (trafficLevel < 1 || trafficLevel > 5) {
            throw new IllegalArgumentException("Traffic level must be between 1 and 5");
        }
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


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Road road = (Road) obj;
        return id.equals(road.id) || 
               (source.equals(road.source) && destination.equals(road.destination));
    }
    
    
    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + source.hashCode();
        result = 31 * result + destination.hashCode();
        return result;
    }
}