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
        if (node == null) {
            return 0;
        }
        return node.height;
    }
    
    
    private void updateHeight(AVLNode node) {
        if (node != null) {
            int leftHeight = (node.left != null) ? node.left.height : 0;
            int rightHeight = (node.right != null) ? node.right.height : 0;
            node.height = 1 + Math.max(leftHeight, rightHeight);
        }
    }

    private boolean verifyHeight(AVLNode node) {
        if (node == null) return true;

        int expectedHeight = 1 + Math.max(height(node.left), height(node.right));
        boolean correct = (node.height == expectedHeight);

        if (!correct) {
            System.err.println(
                "Height mismatch at node " + node.location.getId() + ": expected " + expectedHeight + ", got " + node.height );
        }
    
        return correct && verifyHeight(node.left) && verifyHeight(node.right);
    }

    public boolean verifyTree() {
        return verifyHeight(root);
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
        if (location == null) {
            throw new IllegalArgumentException("Cannot insert null location");
        }
        
        root = insert(root, location);
        size++;
        System.out.println("Inserted location: " + location.getName() + " (Tree height: " + getHeight() + ")");
    }
    
    
    private AVLNode insert(AVLNode node, Location location) {
        // Base case: create new node
        if (node == null) {
            return new AVLNode(location);
        }
    
        // Compare location IDs
        int comparison = location.getId().compareTo(node.location.getId());
    
        if (comparison < 0) {
            // Insert in left subtree
            node.left = insert(node.left, location);
        } else if (comparison > 0) {
            // Insert in right subtree
            node.right = insert(node.right, location);
        } else {
            // Duplicate ID - update the location (or throw exception)
            System.out.println("Location ID already exists. Updating location data.");
            node.location = location;
            size--; // Adjust size since we're replacing, not adding
            return node;
        }
        
        // Update height of this ancestor node
        updateHeight(node);
    
        // Balance the tree
        return balance(node);
    }
    
    
    public void insertAll(Location[] locations) {
        if (locations == null) return;
    
        for (Location location : locations) {
            insert(location);
        }
    }

    @Override
    public Location search(String locationId) {
        if (locationId == null || locationId.trim().isEmpty()) {
            throw new IllegalArgumentException("Location ID cannot be null or empty");
        }
        
        AVLNode result = search(root, locationId);
        return (result != null) ? result.location : null;
    }
    
    private AVLNode search(AVLNode node, String locationId) {
        if (node == null) {
            return null;
        }
        
        int comparison = locationId.compareTo(node.location.getId());
    
        if (comparison < 0) {
            return search(node.left, locationId);
        } else if (comparison > 0) {
            return search(node.right, locationId);
        } else {
            return node; // Found
        }
    }

    @Override
    public void delete(String locationId) {
        if (locationId == null || locationId.trim().isEmpty()) {
            throw new IllegalArgumentException("Location ID cannot be null or empty");
        }
        
        Location location = search(locationId);
        if (location == null) {
            System.out.println("Location not found: " + locationId);
            return;
        }
        
        root = delete(root, locationId);
        size--;
        System.out.println("Deleted location: " + location.getName() + " (Tree height: " + getHeight() + ")");
    }
    
    private AVLNode delete(AVLNode node, String locationId) {
        if (node == null) {
            return null;
        }
        
        int comparison = locationId.compareTo(node.location.getId());
        
        if (comparison < 0) {
            node.left = delete(node.left, locationId);
        } else if (comparison > 0) {
            node.right = delete(node.right, locationId);
        } else {
            // Node to delete found
        
            // Case 1: Node with only one child or no child
            if (node.left == null || node.right == null) {
                AVLNode temp = (node.left != null) ? node.left : node.right;
            
                // No child case
                if (temp == null) {
                    node = null;
                }
                // One child case
                else {
                    node = temp; // Copy the child
                }
            }
            
            // Case 2: Node with two children
            else {
                // Get inorder successor (smallest in right subtree)
                AVLNode successor = getMinNode(node.right);
            
                // Copy successor's data to this node
                node.location = successor.location;
            
                // Delete the inorder successor
                node.right = delete(node.right, successor.location.getId());
            }
        }
        
        // If tree had only one node, return
        if (node == null) {
            return null;
        }
    
        // Update height and balance
        return balance(node);
    }


    private AVLNode getMinNode(AVLNode node) {
        AVLNode current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }
    
    
    @Override
    public void displayInOrder() {
        if (isEmpty()) {
            System.out.println("Tree is empty.");
            return;
        }
    
        System.out.println("\n=== AVL Tree Contents (In-Order) ===");
        System.out.println("Total locations: " + size);
        System.out.println("Tree height: " + getHeight());
        System.out.println("Balanced: " + isBalanced());
        System.out.println("-------------------------------");
    
        List<Location> locations = new ArrayList<>();
        inOrderTraversal(root, locations);
    
        for (int i = 0; i < locations.size(); i++) {
            System.out.printf("%2d. %s%n", i + 1, locations.get(i));
        }
    }
    
    
    private void inOrderTraversal(AVLNode node, List<Location> locations) {
        if (node != null) {
            inOrderTraversal(node.left, locations);
            locations.add(node.location);
            inOrderTraversal(node.right, locations);
        }
    }
    
    public boolean isBalanced() {
        return isBalanced(root);
    }

    private boolean isBalanced(AVLNode node) {
        if (node == null) return true;
    
        int balance = getBalance(node);
        return Math.abs(balance) <= 1 && 
           isBalanced(node.left) && 
           isBalanced(node.right);
    }
    
    public void displayTree() {
        System.out.println("\n=== AVL Tree Structure ===");
        displayTree(root, "", true);
    }
    
    private void displayTree(AVLNode node, String prefix, boolean isTail) {
        if (node != null) {
            System.out.println(prefix + (isTail ? "└── " : "├── ") + node.location.getId() + " (" + node.location.getName() + ")");
        
            String childPrefix = prefix + (isTail ? "    " : "│   ");
        
            if (node.left != null || node.right != null) {
                if (node.left != null) {
                    displayTree(node.left, childPrefix, false);
                }
                if (node.right != null) {
                    displayTree(node.right, childPrefix, true);
                }
            }
        }
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
