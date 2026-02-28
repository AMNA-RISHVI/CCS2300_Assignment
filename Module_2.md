# **Module 2: Sorting Algorithms Project**

## **Overview**
This **Java project** demonstrates the implementation and performance comparison of three popular sorting algorithms:

- **Bubble Sort**  
- **Merge Sort**  
- **Quick Sort**  

Users can manually input an array of integers, select a sorting algorithm from a menu, and view:

- The **sorted array**  
- **Execution time** (in nanoseconds)  

The project also provides a feature to **run all sorting algorithms** on the same dataset and display a **performance comparison table**.

---

## **Project Structure**

```text
src/main/java/module_2
│
├── module_2/
│   ├── SortingMenu.java           # Main menu and user interaction
│
├── module_2/sorting/
│   ├── BubbleSort.java           # Bubble Sort implementation
│   ├── MergeSort.java            # Merge Sort implementation
│   └── QuickSort.java            # Quick Sort implementation
│
└── module_2/utils/
    └── PerformanceUtil.java      # Utility to measure execution time
    Features

Manual Array Input – Enter array size and elements manually.

Sorting Options:

1 → Bubble Sort

2 → Merge Sort

3 → Quick Sort

4 → Run All Sorting Algorithms

5 → Display Execution Time Comparison Table

6 → Exit

Performance Measurement – Uses PerformanceUtil to measure execution time.

Comparison Table – Displays runtime of all algorithms for the same input.

How to Run

Clone the repository:

git clone <repository_url>

Navigate to the project directory:

cd Module2_DataSorter

Compile the Java files:

javac module_2/sorting/*.java module_2/utils/*.java module_2/SortingMenu.java

Run the program:

java module_2.SortingMenu

Follow the on-screen menu to enter the array and select sorting options.

Code Highlights

BubbleSort.java – Implements simple bubble sort (best for small datasets).

MergeSort.java – Implements divide-and-conquer merge sort.

QuickSort.java – Implements quick sort using the last element as pivot.

PerformanceUtil.java – Measures execution time.

SortingMenu.java – Handles user input, runs sorting algorithms, and displays results.

Learning Outcomes

Understand differences in sorting algorithms and their time complexity.

Learn to measure algorithm performance in Java.

Practice modular programming and code organization.

Develop user-friendly command-line interfaces.

Future Enhancements

Add random array generation option.

Include graphical representation of sorting performance.

Support descending order sorting.

Extend to other sorting algorithms like Heap Sort or Radix Sort.