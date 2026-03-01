package module_2;

import java.util.Arrays;
import java.util.Scanner;
import module_2.sorting.BubbleSort;
import module_2.sorting.MergeSort;
import module_2.sorting.QuickSort;
import module_2.util.PerformanceUtil;

public class SortingMenu {

    @SuppressWarnings("FieldMayBeFinal")
    private Scanner scanner = new Scanner(System.in);

    
    public static void main(String[] args) {
        SortingMenu menu = new SortingMenu();
        menu.start();   
    }

    
    public void start() {

        boolean exit = false;

        while (!exit) {
            System.out.println("\n===== Sorting Menu =====");
            System.out.println("1. Bubble Sort");
            System.out.println("2. Merge Sort");
            System.out.println("3. Quick Sort");
            System.out.println("4. All Sorting algorithms");
            System.out.println("5. Comparison Table");
            System.out.println("6. Exit");
            System.out.print("Choose an option (1-6): ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> runBubbleSort();
                case 2 -> runMergeSort();
                case 3 -> runQuickSort();
                case 4 -> runAllSorts();
                case 5 -> showComparisonTable();
                case 6 -> {
                    System.out.println("Exiting program...");
                    return;
                }
                default -> System.out.println("Invalid choice!");
            }
        }

        scanner.close();
    }

    private int[] readArray() {
        System.out.print("Enter array size: ");
        int size = scanner.nextInt();
        int[] arr = new int[size];

        System.out.println("Enter " + size + " elements:");
        for (int i = 0; i < size; i++) {
            arr[i] = scanner.nextInt();
        }

        return arr;
    }

    private void runBubbleSort() {
        int[] arr = readArray();
        long time = PerformanceUtil.measureTime(() -> BubbleSort.sort(arr));
        System.out.println("Sorted (Bubble): " + Arrays.toString(arr));
        System.out.println("Time: " + time + " ns");
    }

    private void runMergeSort() {
        int[] arr = readArray();
        long time = PerformanceUtil.measureTime(() ->
                MergeSort.sort(arr, 0, arr.length - 1));
        System.out.println("Sorted (Merge): " + Arrays.toString(arr));
        System.out.println("Time: " + time + " ns");
    }

    private void runQuickSort() {
        int[] arr = readArray();
        long time = PerformanceUtil.measureTime(() ->
                QuickSort.sort(arr, 0, arr.length - 1));
        System.out.println("Sorted (Quick): " + Arrays.toString(arr));
        System.out.println("Time: " + time + " ns");
    }

    private void runAllSorts() {

        int[] original = readArray();

        int[] arr1 = Arrays.copyOf(original, original.length);
        int[] arr2 = Arrays.copyOf(original, original.length);
        int[] arr3 = Arrays.copyOf(original, original.length);

        long t1 = PerformanceUtil.measureTime(() -> BubbleSort.sort(arr1));
        long t2 = PerformanceUtil.measureTime(() -> MergeSort.sort(arr2, 0, arr2.length - 1));
        long t3 = PerformanceUtil.measureTime(() -> QuickSort.sort(arr3, 0, arr3.length - 1));

        System.out.println("Bubble: " + Arrays.toString(arr1) + " | " + t1 + " ns");
        System.out.println("Merge : " + Arrays.toString(arr2) + " | " + t2 + " ns");
        System.out.println("Quick : " + Arrays.toString(arr3) + " | " + t3 + " ns");
    }

    private void showComparisonTable() {

        int[] original = readArray();

        int[] arr1 = Arrays.copyOf(original, original.length);
        int[] arr2 = Arrays.copyOf(original, original.length);
        int[] arr3 = Arrays.copyOf(original, original.length);

        long t1 = PerformanceUtil.measureTime(() -> BubbleSort.sort(arr1));
        long t2 = PerformanceUtil.measureTime(() -> MergeSort.sort(arr2, 0, arr2.length - 1));
        long t3 = PerformanceUtil.measureTime(() -> QuickSort.sort(arr3, 0, arr3.length - 1));

        System.out.println("\n===== Comparison Table =====");
        System.out.printf("%-12s %-15s%n", "Algorithm", "Time (ns)");
        System.out.printf("%-12s %-15d%n", "Bubble", t1);
        System.out.printf("%-12s %-15d%n", "Merge", t2);
        System.out.printf("%-12s %-15d%n", "Quick", t3);
    }
}