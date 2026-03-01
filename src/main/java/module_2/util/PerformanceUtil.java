package module_2.util;

public class PerformanceUtil {

    public static long measureTime(Runnable algorithm) {

        long start = System.nanoTime();
        algorithm.run();
        long end = System.nanoTime();

        return end - start;
    }
}