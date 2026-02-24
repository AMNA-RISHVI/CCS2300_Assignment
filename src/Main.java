import java.util.Arrays;
import java.util.Scanner;
import sorting.BubbleSort;
import sorting.MergeSort;
import sorting.QuickSort;
import utils.PerformanceUtil;

public class Main {

    @SuppressWarnings("ConvertToTryWithResources")
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("===== Sort Your Data =====");

        System.out.print("Enter number of elements: ");
        int n = sc.nextInt();

        int[] original = new int[n];

        System.out.println("Enter numbers manually:");
        for (int i = 0; i < n; i++) {
            original[i] = sc.nextInt();
        }

        int[] bubbleArr = Arrays.copyOf(original, n);
        int[] mergeArr = Arrays.copyOf(original, n);
        int[] quickArr = Arrays.copyOf(original, n);

        long bubbleTime = PerformanceUtil.measureTime(() -> 
            BubbleSort.sort(bubbleArr)
        );

        long mergeTime = PerformanceUtil.measureTime(() -> 
            MergeSort.sort(mergeArr, 0, mergeArr.length - 1)
        );

        long quickTime = PerformanceUtil.measureTime(() -> 
            QuickSort.sort(quickArr, 0, quickArr.length - 1)
        );

        System.out.println("\nSorted Output (Bubble Sort): " + Arrays.toString(bubbleArr));
        System.out.println("Sorted Output (Merge Sort):  " + Arrays.toString(mergeArr));
        System.out.println("Sorted Output (Quick Sort):  " + Arrays.toString(quickArr));

        System.out.println("\n===== Performance Comparison Table =====");
        System.out.printf("%-15s %-20s\n", "Algorithm", "Execution Time (ns)");
        System.out.println("----------------------------------------");
        System.out.printf("%-15s %-20d\n", "Bubble Sort", bubbleTime);
        System.out.printf("%-15s %-20d\n", "Merge Sort", mergeTime);
        System.out.printf("%-15s %-20d\n", "Quick Sort", quickTime);

        sc.close();
    }
}