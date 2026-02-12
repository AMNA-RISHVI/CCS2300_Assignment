package module1.models;

public class Location {
    private String id;
    private String name;
    private String type; // e.g., "Restaurant", "Hospital", "Park"
    private double xCoordinate;
    private double yCoordinate;
    
    // Constructor
    public Location(String id, String name, String type, double x, double y) {
        
        // Validate input parameters
        validateId(id);
        validateName(name);
        validateType(type);

        this.id = id;
        this.name = name;
        this.type = type;
        this.xCoordinate = x;
        this.yCoordinate = y;
    }

    private void validateId(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Location ID cannot be null or empty");
        }
        if (!id.startsWith("L")) {
            throw new IllegalArgumentException("Location ID must start with 'L'");
        }
        if (id.length() < 4) {
            throw new IllegalArgumentException("Location ID must be at least 4 characters (e.g., L001)");
        }
    }

    private void validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Location name cannot be null or empty");
        }
        if (name.length() < 2) {
            throw new IllegalArgumentException("Location name must be at least 2 characters");
        }
    }

    private void validateType(String type) {
        if (type == null || type.trim().isEmpty()) {
            throw new IllegalArgumentException("Location type cannot be null or empty");
        }
        
        // Validate against allowed types
        String[] allowedTypes = {"Park", "Hospital", "School", "Mall", "Restaurant", 
                                 "Residential", "Commercial", "Industrial", "Transport"};
        boolean validType = false;
        for (String allowed : allowedTypes) {
            if (allowed.equalsIgnoreCase(type)) {
                validType = true;
                break;
            }
        }
        
        if (!validType) {
            System.out.println("Warning: Location type '" + type + "' is not in recommended list");
            // Not throwing exception, just warning for flexibility
        }
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
