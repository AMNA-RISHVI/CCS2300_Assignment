# 🚀 Smart City Management System – Group Assignment

## 📋 Project Overview
A console-based Java application that integrates three modules:
- **Module 1:** Smart City Route Planner – graph-based navigation with AVL tree.
- **Module 2:** Data Sorter – comparison of Bubble, Merge, and Quick Sort.
- **Module 3:** Algorithm Performance Analyzer – measures execution time of sorting and searching algorithms.

Developed as a group project for CCS2300 Data Structures and Algorithms.

---

## 👥 Team Members
| Name                      | CIT Number       | Module Responsibility         |
|---------------------------|------------------|-------------------------------|
| M.R.F. Amna               |  CIT-24-02-0048  | Module 1: Smart City Route Planner |
| H.M. Gunawardhana         |  CIT-24-02-0005  | Module 2: Data Sorter              |
| M.M.C.I. Sewmini          |  CIT-24-02-0193  | Module 3: Algorithm Performance Analyzer |

---

## 🧩 Module 1 – Smart City Route Planner
**Developed by:** M.R.F. Amna

### Features
- Graph (adjacency list) to represent locations and bidirectional roads.
- AVL tree for efficient location storage and retrieval.
- Add, remove, update, search, and list locations.
- Add, remove, and update roads (distance, type, traffic level).
- BFS and DFS traversal (using Queue and Stack).
- Shortest path finding by number of hops (BFS).
- Graph visualization and connectivity check.
- System statistics (location/road counts, type distribution, isolated nodes).
- Input validation for all user inputs.

### Key Classes
- `Location` – model for a city location.
- `Road` – model for a bidirectional road.
- `CityGraph` – graph implementation with adjacency list.
- `GraphTraversal` – BFS, DFS, path‑finding algorithms.
- `AVLTree` – self‑balancing tree.
- `RoutePlannerMenu` – interactive menu for Module 1.
- `InputValidator` – validation utilities.

---

## 📊 Module 2 – Data Sorter
**Developed by:** H.M.Gunawardhana

### Features
- Manual entry or random generation of integer arrays.
- Bubble Sort, Merge Sort, Quick Sort implementations.
- Execution time measurement using `System.nanoTime()`.
- Individual sorting or all three for comparison.
- Comparison table display.

### Key Classes
- `BubbleSort` – bubble sort algorithm.
- `MergeSort` – merge sort algorithm.
- `QuickSort` – quick sort algorithm.
- `PerformanceUtil` – utility to measure execution time.
- `SortingMenu` – console menu for Module 2.

---

## ⏱️ Module 3 – Algorithm Performance Analyzer
**Developed by:** M.M.C.I. Sewmini

### Features
- Generates random arrays of specified size.
- Sorts using Merge Sort (or other) and measures time.
- Searches for a user‑defined target using Binary Search.
- Displays results in a formatted table.
- Reusable `Testresult` class to store performance metrics.

### Key Classes
- `AlgorithmAnalyzer` – core analysis logic.
- `SortingAlgorithm` – merge sort implementation.
- `SearchingAlgorithm` – binary search.
- `Testresult` – data container for test results.
- `PerformanceAnalyzerMenu` – menu interface.

---

## 🛠️ How to Compile and Run

### Prerequisites
- Java JDK 11 or higher.
- No external libraries required.

### Compilation
From the project root directory, run:
```bash
javac -d bin src/main/java/**/*.java
```

### Execution
```bash
java -cp bin MainApp
```

Follow the on‑screen menus to navigate between modules.
---

## 📁 Project Structure
```
src/main/java/
├── module1/                     # Smart City Route Planner
│   ├── models/
│   ├── graphs/
│   ├── trees/
│   ├── menu/
│   └── utils/
├── module2/                     # Data Sorter
│   ├── sorting/
│   ├── util/
│   └── SortingMenu.java
├── module3/                     # Algorithm Performance Analyzer
│   ├── analysis/
│   ├── model/
│   ├── menu/
│   └── PerformanceAnalyzerMenu.java
└── MainApp.java                  # Main integration point
```

---
