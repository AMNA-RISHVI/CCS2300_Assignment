# 📘 Module 1: Smart City Route Planner

## Overview
This module implements a **graph-based city navigation system** with an **AVL tree** for efficient location storage. It provides a console menu to manage locations and roads, perform graph traversals (BFS/DFS), find shortest paths, and display statistics.

---

## ✨ Features
- **Location management** – add, remove, update, search, and list locations.
- **Road management** – add/remove bidirectional roads with distance, type, and traffic level (1–5).
- **Graph algorithms** – BFS/DFS traversal, shortest path by number of hops.
- **AVL tree** – self‑balancing tree that stores all locations and keeps them sorted by ID.
- **Input validation** – all user inputs are checked for correct format and range.
- **Graph visualization** – display the entire graph with formatted tables and traffic level.
- **Connectivity check** – determine if the graph is fully connected.
- **Statistics** – view location/road counts, average degree, type distribution, etc.
- **Demo data** – pre‑loaded sample data (5 locations, 5 roads) for immediate testing.

---

## 🚀 How to Run
1. Compile all Java files:
   ```bash
   javac -d bin src/main/java/module1/**/*.java src/main/java/MainApp.java
   ```
2. Run the main application:
   ```bash
   java -cp bin MainApp
   ```
3. From the main menu, select **1** (Module 1) and choose whether to load the demo data.

---

## 📁 Project Structure
```
module1/
├── models/
│   ├── Location.java        – location with ID, name, type, coordinates
│   └── Road.java             – bidirectional road with distance, type, traffic
├── trees/
│   ├── LocationTree.java     – interface for tree operations
│   └── AVLTree.java          – AVL tree implementation
├── graphs/
│   ├── CityGraph.java        – adjacency‑list graph with location/road management
│   └── GraphTraversal.java   – BFS, DFS, path‑finding algorithms
├── menu/
│   └── RoutePlannerMenu.java – interactive console menu for all features
├── utils/
│   ├── InputValidator.java   – validation methods for IDs, names, numbers
│   └── exceptions/           – custom exceptions (DuplicateLocation, InvalidInput, etc.)
└── test/                      – manual test classes (not part of the main app)
    ├── CityGraphTest.java
    ├── AVLTreeTest.java
    ├── LocationTest.java
    ├── RoadTest.java
    └── InputValidatorTest.java
```

---

## 🧭 Using the Menu
Once inside Module 1, you will see the main menu:

```
============================================================
   MAIN MENU
============================================================
1. Location Management
2. Road Management
3. Graph Operations
4. Tree Operations
5. System Statistics
6. Demo Data
7. Exit to Module Selection
```

### 1. Location Management
- Add, remove, update, search, list, and view details of locations.
- Sync tree and graph to maintain consistency.

### 2. Road Management
- Add, remove roads, update traffic levels, list all roads, and view connections of a specific location.

### 3. Graph Operations
- BFS / DFS traversal.
- Display the full graph (locations and roads).
- Check connectivity.
- Generate a random graph for testing.

### 4. Tree Operations
- Display the AVL tree structure (size, height, in‑order list).
- Check if the tree is balanced.

### 5. System Statistics
- Graph overview (counts, average degree, isolated locations, type distribution).
- Tree statistics (reuses tree operations).
- Connectivity summary (reachable locations from the first node).

### 6. Demo Data
- Load the standard demo data (5 locations, 5 roads).
- Clear all data.

---

## 📊 Sample Demo Data
After loading the demo data, the following locations are available:

| ID    | Name               | Type        | Coordinates |
|-------|--------------------|-------------|-------------|
| L001  | Central Park       | Park        | (50, 50)    |
| L002  | City Hospital      | Hospital    | (30, 70)    |
| L003  | Main Mall          | Shopping    | (70, 40)    |
| L004  | Tech University    | Education   | (80, 80)    |
| L005  | Railway Station    | Transport   | (40, 30)    |

Roads connect them with distances, types, and traffic levels (see the road listing).

---

## 🧠 Key Algorithms
- **BFS Traversal** – uses a `Queue` to visit nodes level by level.
- **DFS Traversal** – uses a `Stack` for depth‑first exploration.
- **Shortest Path (BFS)** – finds the path with the fewest hops by storing whole paths in a queue.
- **AVL Tree** – automatically balances after each insert/delete using four rotation cases (left, right, left‑right, right‑left).

---

## ✅ Input Validation
All user input is validated through `InputValidator`:
- **Location ID**: must start with `L` followed by at least three digits (e.g., `L001`).
- **Road ID**: must start with `R` followed by at least three digits.
- **Name**: 2–50 characters, letters, numbers, spaces, and basic punctuation allowed.
- **Traffic level**: integer between 1 and 5.
- **Distance**: positive integer.
- **Coordinates**: doubles between -1000 and 1000.

Invalid inputs prompt the user to re‑enter; the program never crashes.

---

## 🔗 Integration with Main Application
The module is designed to be called from a central `MainApp`:
```java
RoutePlannerMenu routePlanner = new RoutePlannerMenu();
routePlanner.displayMainMenu();
```
When the user chooses to exit, control returns to the main menu.

---

## 📦 Dependencies
- Java 11 or higher (no external libraries).

---

## 👨‍💻 Author
M.R.F.Amna – responsible for Module 1 in this group project.

