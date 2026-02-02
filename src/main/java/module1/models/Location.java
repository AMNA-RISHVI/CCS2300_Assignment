package module1.models;

public class Location {
    private String id;
    private String name;
    private String type; // e.g., "Restaurant", "Hospital", "Park"
    private double xCoordinate;
    private double yCoordinate;
    
    // Constructor
    public Location(String id, String name, String type, double x, double y) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.xCoordinate = x;
        this.yCoordinate = y;
    }
    
    // Getters and Setters
    public String getId() { return id; }
    public String getName() { return name; }
    public String getType() { return type; }
    public double getX() { return xCoordinate; }
    public double getY() { return yCoordinate; }
    
    @Override
    public String toString() {
        return String.format("%s - %s (%s)", id, name, type);
    }
}