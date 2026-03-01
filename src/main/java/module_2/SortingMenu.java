package module_2;

import java.util.Arrays;
import java.util.Scanner;
import module_2.sorting.BubbleSort;
import module_2.sorting.MergeSort;
import module_2.sorting.QuickSort;
import module_2.util.PerformanceUtil;

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
            System.out.println("4. All Sorting Algorithms");
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
        final int[] arr = readArray();
        System.out.println("Original Array: " + Arrays.toString(arr));

        @SuppressWarnings("Convert2Lambda")
        long time = PerformanceUtil.measureTime(new Runnable() {
            @SuppressWarnings("override")
            public void run() {
                BubbleSort.sort(arr);
            }
        });

        System.out.println("Sorted Array (Bubble Sort): " + Arrays.toString(arr));
        System.out.println("Execution Time: " + time + " ns");
    }

    private static void runMergeSort() {
        final int[] arr = readArray();
        System.out.println("Original Array: " + Arrays.toString(arr));

        @SuppressWarnings("Convert2Lambda")
        long time = PerformanceUtil.measureTime(new Runnable() {
            @SuppressWarnings("override")
            public void run() {
                MergeSort.sort(arr, 0, arr.length - 1);
            }
        });

        System.out.println("Sorted Array (Merge Sort): " + Arrays.toString(arr));
        System.out.println("Execution Time: " + time + " ns");
    }

    private static void runQuickSort() {
        final int[] arr = readArray();
        System.out.println("Original Array: " + Arrays.toString(arr));

        @SuppressWarnings("Convert2Lambda")
        long time = PerformanceUtil.measureTime(new Runnable() {
            @SuppressWarnings("override")
            public void run() {
                QuickSort.sort(arr, 0, arr.length - 1);
            }
        });

        System.out.println("Sorted Array (Quick Sort): " + Arrays.toString(arr));
        System.out.println("Execution Time: " + time + " ns");
    }

    private static void runAllSorts() {
        final int[] original = readArray();

        final int[] arr1 = Arrays.copyOf(original, original.length);
        final int[] arr2 = Arrays.copyOf(original, original.length);
        final int[] arr3 = Arrays.copyOf(original, original.length);

        System.out.println("Original Array: " + Arrays.toString(original));

        @SuppressWarnings("Convert2Lambda")
        long timeBubble = PerformanceUtil.measureTime(new Runnable() {
            @SuppressWarnings("override")
            public void run() {
                BubbleSort.sort(arr1);
            }
        });

        @SuppressWarnings("Convert2Lambda")
        long timeMerge = PerformanceUtil.measureTime(new Runnable() {
            @SuppressWarnings("override")
            public void run() {
                MergeSort.sort(arr2, 0, arr2.length - 1);
            }
        });

        @SuppressWarnings("Convert2Lambda")
        long timeQuick = PerformanceUtil.measureTime(new Runnable() {
            @SuppressWarnings("override")
            public void run() {
                QuickSort.sort(arr3, 0, arr3.length - 1);
            }
        });

        System.out.println("Bubble Sort: " + Arrays.toString(arr1) + " | Time: " + timeBubble + " ns");
        System.out.println("Merge Sort:  " + Arrays.toString(arr2) + " | Time: " + timeMerge + " ns");
        System.out.println("Quick Sort:  " + Arrays.toString(arr3) + " | Time: " + timeQuick + " ns");
    }

    private static void showComparisonTable() {
        final int[] original = readArray();

        final int[] arr1 = Arrays.copyOf(original, original.length);
        final int[] arr2 = Arrays.copyOf(original, original.length);
        final int[] arr3 = Arrays.copyOf(original, original.length);

        @SuppressWarnings("Convert2Lambda")
        long timeBubble = PerformanceUtil.measureTime(new Runnable() {
            @SuppressWarnings("override")
            public void run() {
                BubbleSort.sort(arr1);
            }
        });

        @SuppressWarnings("Convert2Lambda")
        long timeMerge = PerformanceUtil.measureTime(new Runnable() {
            @SuppressWarnings("override")
            public void run() {
                MergeSort.sort(arr2, 0, arr2.length - 1);
            }
        });

        @SuppressWarnings("Convert2Lambda")
        long timeQuick = PerformanceUtil.measureTime(new Runnable() {
            @SuppressWarnings("override")
            public void run() {
                QuickSort.sort(arr3, 0, arr3.length - 1);
            }
        });

        System.out.println("\n===== Sorting Performance Comparison (ns) =====");
        System.out.printf("%-15s %-20s%n", "Algorithm", "Execution Time");
        System.out.printf("%-15s %-20d%n", "Bubble Sort", timeBubble);
        System.out.printf("%-15s %-20d%n", "Merge Sort", timeMerge);
        System.out.printf("%-15s %-20d%n", "Quick Sort", timeQuick);
    }
}