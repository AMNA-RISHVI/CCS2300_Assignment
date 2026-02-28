import java.util.Arrays;
import java.util.Scanner;
import sorting.BubbleSort;
import sorting.MergeSort;
import sorting.QuickSort;
import utils.PerformanceUtil;

public class SortingMenu {

    @SuppressWarnings("FieldMayBeFinal")
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        boolean exit = false;

        while (!exit) {
            System.out.println("\n===== Sorting Menu =====");
            System.out.println("1. Bubble Sort");
            System.out.println("2. Merge Sort");
            System.out.println("3. Quick Sort");
            System.out.println("4. All Sorts");
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
                    exit = true;
                    System.out.println("Exiting program...");
                }
                default -> System.out.println("Invalid choice! Please enter 1-6.");
            }
        }

        scanner.close();
    }

    // Method to read array manually from user
    private static int[] readArray() {
        System.out.print("Enter the size of the array: ");
        int size = scanner.nextInt();
        int[] arr = new int[size];

        System.out.println("Enter " + size + " elements:");
        for (int i = 0; i < size; i++) {
            arr[i] = scanner.nextInt();
        }

        return arr;
    }

    private static void runBubbleSort() {
        int[] arr = readArray();
        System.out.println("Original Array: " + Arrays.toString(arr));

        long time = PerformanceUtil.measureTime(() -> BubbleSort.sort(arr));

        System.out.println("Sorted Array (Bubble Sort): " + Arrays.toString(arr));
        System.out.println("Execution Time: " + time + " ns");
    }

    private static void runMergeSort() {
        int[] arr = readArray();
        System.out.println("Original Array: " + Arrays.toString(arr));

        long time = PerformanceUtil.measureTime(() -> MergeSort.sort(arr, 0, arr.length - 1));

        System.out.println("Sorted Array (Merge Sort): " + Arrays.toString(arr));
        System.out.println("Execution Time: " + time + " ns");
    }

    private static void runQuickSort() {
        int[] arr = readArray();
        System.out.println("Original Array: " + Arrays.toString(arr));

        long time = PerformanceUtil.measureTime(() -> QuickSort.sort(arr, 0, arr.length - 1));

        System.out.println("Sorted Array (Quick Sort): " + Arrays.toString(arr));
        System.out.println("Execution Time: " + time + " ns");
    }

    private static void runAllSorts() {
        int[] original = readArray();

        int[] arr1 = Arrays.copyOf(original, original.length);
        int[] arr2 = Arrays.copyOf(original, original.length);
        int[] arr3 = Arrays.copyOf(original, original.length);

        System.out.println("Original Array: " + Arrays.toString(original));

        long timeBubble = PerformanceUtil.measureTime(() -> BubbleSort.sort(arr1));
        long timeMerge = PerformanceUtil.measureTime(() -> MergeSort.sort(arr2, 0, arr2.length - 1));
        long timeQuick = PerformanceUtil.measureTime(() -> QuickSort.sort(arr3, 0, arr3.length - 1));

        System.out.println("Bubble Sort: " + Arrays.toString(arr1) + " | Time: " + timeBubble + " ns");
        System.out.println("Merge Sort:  " + Arrays.toString(arr2) + " | Time: " + timeMerge + " ns");
        System.out.println("Quick Sort:  " + Arrays.toString(arr3) + " | Time: " + timeQuick + " ns");
    }

    private static void showComparisonTable() {
        int[] original = readArray();

        int[] arr1 = Arrays.copyOf(original, original.length);
        int[] arr2 = Arrays.copyOf(original, original.length);
        int[] arr3 = Arrays.copyOf(original, original.length);

        long timeBubble = PerformanceUtil.measureTime(() -> BubbleSort.sort(arr1));
        long timeMerge = PerformanceUtil.measureTime(() -> MergeSort.sort(arr2, 0, arr2.length - 1));
        long timeQuick = PerformanceUtil.measureTime(() -> QuickSort.sort(arr3, 0, arr3.length - 1));

        System.out.println("\n===== Sorting Performance Comparison (ns) =====");
        System.out.printf("%-15s %-20s%n", "Algorithm", "Execution Time");
        System.out.printf("%-15s %-20d%n", "Bubble Sort", timeBubble);
        System.out.printf("%-15s %-20d%n", "Merge Sort", timeMerge);
        System.out.printf("%-15s %-20d%n", "Quick Sort", timeQuick);
    }
}