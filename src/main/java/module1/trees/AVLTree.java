package module1.trees;

import module1.models.Location;
import java.util.ArrayList;
import java.util.List;

public class AVLTree implements LocationTree {
    
    private class AVLNode {
        Location location;
        AVLNode left;
        AVLNode right;
        int height;
        
        AVLNode(Location location) {
            this.location = location;
            this.height = 1;  // New node is initially at height 1
        }
    }
    
    private AVLNode root;
    private int size;
    
    
    public AVLTree() {
        this.root = null;
        this.size = 0;
    }
    
    
    public AVLTree(Location location) {
        this.root = new AVLNode(location);
        this.size = 1;
    }
    
   
    private int height(AVLNode node) {
        return (node == null) ? 0 : node.height;
    }
    
    
    private void updateHeight(AVLNode node) {
        if (node != null) {
            node.height = 1 + Math.max(height(node.left), height(node.right));
        }
    }
    
    
    private int getBalance(AVLNode node) {
        if (node == null) {
            return 0;
        }
        return height(node.left) - height(node.right);
    }
    

    // Basic interface implementations
    @Override
    public boolean isEmpty() {
        return root == null;
    }
    
    @Override
    public int getSize() {
        return size;
    }
    
    @Override
    public int getHeight() {
        return height(root);
    }
    
    @Override
    public void clear() {
        root = null;
        size = 0;
    }
    
    // Other methods 
    @Override
    public void insert(Location location) {
        throw new UnsupportedOperationException("Insert not implemented yet");
    }
    
    @Override
    public void delete(String locationId) {
        throw new UnsupportedOperationException("Delete not implemented yet");
    }
    
    @Override
    public Location search(String locationId) {
        throw new UnsupportedOperationException("Search not implemented yet");
    }
    
    @Override
    public void displayInOrder() {
        throw new UnsupportedOperationException("Display not implemented yet");
    }
    
    private AVLNode rightRotate(AVLNode y) {
        if (y == null || y.left == null) {
            return y;
        }
        
        AVLNode x = y.left;
        AVLNode T2 = x.right;
        
        // Perform rotation
        x.right = y;
        y.left = T2;
    
        // Update heights
        updateHeight(y);
        updateHeight(x);
    
        return x;
    }


    private AVLNode leftRotate(AVLNode x) {
        if (x == null || x.right == null) {
            return x;
        }
        
        AVLNode y = x.right;
        AVLNode T2 = y.left;
    
        // Perform rotation
        y.left = x;
        x.right = T2;
    
       // Update heights
       updateHeight(x);
       updateHeight(y);
    
       return y;
    }


    private AVLNode leftRightRotate(AVLNode node) {
        if (node == null) return null;
        node.left = leftRotate(node.left);
        return rightRotate(node);
    }


    private AVLNode rightLeftRotate(AVLNode node) {
        if (node == null) return null;
        node.right = rightRotate(node.right);
        return leftRotate(node);
    }


    private AVLNode balance(AVLNode node) {
        if (node == null) return null;
        
        updateHeight(node);
        int balanceFactor = getBalance(node);
    
        // Left Heavy
        if (balanceFactor > 1) {
            
            // Left-Left case
            if (getBalance(node.left) >= 0) {
                return rightRotate(node);
            }
            
            // Left-Right case
            else {
                return leftRightRotate(node);
            }
        }
    
        // Right Heavy
        if (balanceFactor < -1) {

            // Right-Right case
            if (getBalance(node.right) <= 0) {
                return leftRotate(node);
            }

            // Right-Left case
            else {
                return rightLeftRotate(node);
            }
        }
        
        return node; // Already balanced
    }
}
