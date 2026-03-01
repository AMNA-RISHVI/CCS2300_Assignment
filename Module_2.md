# 🚀 Module_2 – Sorting Algorithms Project

---

## 📖 **Overview**

This project is a **Java console-based application** developed for Module 2.  
It demonstrates the implementation and comparison of multiple **sorting algorithms** while measuring their execution performance.

---

## ✨ **Features**

- 🔢 Manual number input
- 📊 Three sorting algorithms implemented
- ⏱ Execution time measurement (in nanoseconds)
- 📈 Performance comparison table
- 🖥 Menu-driven console interface
- 📂 Modular package structure

---

## ▶ **How to Run**

1. Open the project in **VS Code**
2. Navigate to:
   `src/main/java/module_2/SortingMenu.java`
3. Run the `SortingMenu` class
4. Follow the on-screen menu instructions

---

## 📁 **Project Structure**


src/main/java/

   │
   └── module_2/

      │
      ├── SortingMenu.java
 
      │
      ├── sorting/

         │ ├── BubbleSort.java

         │ ├── MergeSort.java

         │ └── QuickSort.java

      │
      └── util/
  
           └── PerformanceUtil.java


---

## 🖥 **Menu Options**


Bubble Sort

Merge Sort

Quick Sort

All Sorting Algorithms

Comparison Table

Exit


---

## 🔎 **Algorithms Used**

- 🔹 **Bubble Sort**
- 🔹 **Merge Sort**
- 🔹 **Quick Sort**

Each algorithm sorts the user-entered numbers in ascending order.

---

## ⏱ **Time Complexity Table**

| Algorithm     | Best Case | Average Case | Worst Case |
|--------------|-----------|-------------|------------|
| Bubble Sort  | O(n)      | O(n²)       | O(n²)      |
| Merge Sort   | O(n log n)| O(n log n)  | O(n log n) |
| Quick Sort   | O(n log n)| O(n log n)  | O(n²)      |

---

## 🧪 **Sample Demo Data**

**Example Input:**

Size: 5
Elements: 5 2 9 1 3


**Sorted Output:**

1 2 3 5 9


Execution time will be displayed in nanoseconds for each algorithm.

---

## 🧠 **Key Algorithms**

- **Bubble Sort** – Repeatedly swaps adjacent elements if they are in the wrong order.
- **Merge Sort** – Divides the array into halves and merges them in sorted order.
- **Quick Sort** – Uses a pivot element to partition and sort the array recursively.

---

## ✅ **Input Validation**

- ✔ User must enter a valid array size  
- ✔ User must enter integer values  
- ✔ Invalid menu options are handled with an error message  

---

## 🔗 **Integration with Main Application**

The `SortingMenu` class integrates:

- Sorting algorithm classes  
- Performance measurement utility  
- User input handling  
- Output display and comparison table  

All components work together through a menu-driven system.

---

## 📦 **Dependencies**

- ☕ Java Development Kit (JDK 8 or higher)
- 🚫 No external libraries required
- Uses standard Java packages:
  - `java.util`
  - `System.nanoTime()`

---

## 👤 🎓 **Author**

**H.M. Gunawarshana**  
Student ID: **CIT-24-02-0005**  
Sri Lanka Technological Campus

---
