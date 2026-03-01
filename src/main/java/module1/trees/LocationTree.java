package module1.trees;

import module1.models.Location;

public interface LocationTree {
    
    void insert(Location location);
    
    void delete(String locationId);
    
    Location search(String locationId);
    
    void displayInOrder();
    
    int getHeight();
    
    int getSize();
    
    boolean isEmpty();
    
    void clear();
}
