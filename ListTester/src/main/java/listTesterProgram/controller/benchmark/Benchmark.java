package listTesterProgram.controller.benchmark;

import listTesterProgram.model.abstractModels.LinkedList;
import listTesterProgram.model.concrete.Node;
import listTesterProgram.model.concrete.TypeLinkedList;
import listTesterProgram.model.creators.ListCreator;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * A simple benchmark class for testing LinkedList implementations
 * Provides methods for benchmarking operations and exporting results
 */
public class Benchmark {

    private static final int WARMUP_ITERATIONS = 5;
    private static final int BENCHMARK_ITERATIONS = 10;
    private static final int[] DATA_SIZES = {100, 1000, 10000, 100000};
    
    private final ListCreator listCreator;

    /**
     * Constructor for SimpleBenchmark
     */
    public Benchmark() {
        this.listCreator = new ListCreator();
    }

    /**
     * Run benchmarks for all operations with various data sizes
     *
     * @return A list of benchmark results
     */
    public List<Result> runBenchmarks() {
        List<Result> results = new ArrayList<>();

        // Test all operations for each list type and data size
        for (TypeLinkedList type : TypeLinkedList.values()) {
            for (int size : DATA_SIZES) {
                // Create a new list for each test
                LinkedList<Integer> list = listCreator.createLinkedList(type);
                results.add(benchmarkPushFront(type, list, size));

                list = listCreator.createLinkedList(type);
                results.add(benchmarkPushBack(type, list, size));
                
                list = listCreator.createLinkedList(type);
                results.add(benchmarkPopFront(type, list, size));
                
                list = listCreator.createLinkedList(type);
                results.add(benchmarkPopBack(type, list, size));
                
                list = listCreator.createLinkedList(type);
                results.add(benchmarkFind(type, list, size));
                
                list = listCreator.createLinkedList(type);
                results.add(benchmarkErase(type, list, size));
                
                list = listCreator.createLinkedList(type);
                results.add(benchmarkAddAfter(type, list, size));
                
                list = listCreator.createLinkedList(type);
                results.add(benchmarkAddBefore(type, list, size));
            }
        }

        return results;
    }

    /**
     * Benchmark the pushFront operation
     *
     * @param type The type of LinkedList
     * @param list The LinkedList instance
     * @param size The size of data to benchmark
     * @return The benchmark result
     */
    private Result benchmarkPushFront(TypeLinkedList type, LinkedList<Integer> list, int size) {
        // Warmup
        for (int i = 0; i < WARMUP_ITERATIONS; i++) {
            list.pushFront(i);
        }

        clearList(list);

        // Benchmark
        long startTime = System.nanoTime();

        for (int i = 0; i < BENCHMARK_ITERATIONS; i++) {
            for (int j = 0; j < size; j++) {
                list.pushFront(j);
            }
            clearList(list);
        }

        long endTime = System.nanoTime();
        double timeInMs = (endTime - startTime) / 1_000_000.0;
        double avgTimePerOperation = timeInMs / (BENCHMARK_ITERATIONS * size);

        return new Result(type.name(), "pushFront", timeInMs, avgTimePerOperation * 1_000_000, size);
    }

    /**
     * Benchmark the pushBack operation
     *
     * @param type The type of LinkedList
     * @param list The LinkedList instance
     * @param size The size of data to benchmark
     * @return The benchmark result
     */
    private Result benchmarkPushBack(TypeLinkedList type, LinkedList<Integer> list, int size) {
        // Warmup
        for (int i = 0; i < WARMUP_ITERATIONS; i++) {
            list.pushBack(i);
        }

        clearList(list);

        // Benchmark
        long startTime = System.nanoTime();

        for (int i = 0; i < BENCHMARK_ITERATIONS; i++) {
            for (int j = 0; j < size; j++) {
                list.pushBack(j);
            }
            clearList(list);
        }

        long endTime = System.nanoTime();
        double timeInMs = (endTime - startTime) / 1_000_000.0;
        double avgTimePerOperation = timeInMs / (BENCHMARK_ITERATIONS * size);

        return new Result(type.name(), "pushBack", timeInMs, avgTimePerOperation * 1_000_000, size);
    }

    /**
     * Benchmark the popFront operation
     *
     * @param type The type of LinkedList
     * @param list The LinkedList instance
     * @param size The size of data to benchmark
     * @return The benchmark result
     */
    private Result benchmarkPopFront(TypeLinkedList type, LinkedList<Integer> list, int size) {
        // Populate the list
        for (int i = 0; i < size; i++) {
            list.pushBack(i);
        }

        // Benchmark
        long startTime = System.nanoTime();

        for (int i = 0; i < size; i++) {
            try {
                list.popFront();
            } catch (Exception e) {
                // Ignore exceptions during benchmarking
            }
        }

        long endTime = System.nanoTime();
        double timeInMs = (endTime - startTime) / 1_000_000.0;
        double avgTimePerOperation = timeInMs / size;

        return new Result(type.name(), "popFront", timeInMs, avgTimePerOperation * 1_000_000, size);
    }

    /**
     * Benchmark the popBack operation
     *
     * @param type The type of LinkedList
     * @param list The LinkedList instance
     * @param size The size of data to benchmark
     * @return The benchmark result
     */
    private Result benchmarkPopBack(TypeLinkedList type, LinkedList<Integer> list, int size) {
        clearList(list);

        // Populate the list
        for (int i = 0; i < size; i++) {
            list.pushBack(i);
        }

        // Benchmark
        long startTime = System.nanoTime();

        for (int i = 0; i < size; i++) {
            try {
                list.popBack();
            } catch (Exception e) {
                // Ignore exceptions during benchmarking
            }
        }

        long endTime = System.nanoTime();
        double timeInMs = (endTime - startTime) / 1_000_000.0;
        double avgTimePerOperation = timeInMs / size;

        return new Result(type.name(), "popBack", timeInMs, avgTimePerOperation * 1_000_000, size);
    }

    /**
     * Benchmark the find operation
     *
     * @param type The type of LinkedList
     * @param list The LinkedList instance
     * @param size The size of data to benchmark
     * @return The benchmark result
     */
    private Result benchmarkFind(TypeLinkedList type, LinkedList<Integer> list, int size) {
        clearList(list);

        // Populate the list
        for (int i = 0; i < size; i++) {
            list.pushBack(i);
        }

        // Benchmark
        long startTime = System.nanoTime();

        // Find 10% of the elements to keep benchmark time reasonable for large sizes
        int findOperations = Math.max(1, size / 10);
        for (int i = 0; i < findOperations; i++) {
            try {
                int valueToFind = (int) (Math.random() * size);
                list.find(valueToFind);
            } catch (Exception e) {
                // Ignore exceptions during benchmarking
            }
        }

        long endTime = System.nanoTime();
        double timeInMs = (endTime - startTime) / 1_000_000.0;
        double avgTimePerOperation = timeInMs / findOperations;

        return new Result(type.name(), "find", timeInMs, avgTimePerOperation * 1_000_000, size);
    }

    /**
     * Benchmark the erase operation
     *
     * @param type The type of LinkedList
     * @param list The LinkedList instance
     * @param size The size of data to benchmark
     * @return The benchmark result
     */
    private Result benchmarkErase(TypeLinkedList type, LinkedList<Integer> list, int size) {
        clearList(list);

        // Populate the list
        for (int i = 0; i < size; i++) {
            list.pushBack(i);
        }

        // Benchmark
        long startTime = System.nanoTime();

        // Erase 10% of the elements to avoid emptying the list too quickly
        int eraseOperations = Math.max(1, size / 10);
        for (int i = 0; i < eraseOperations; i++) {
            try {
                int valueToErase = (int) (Math.random() * size);
                list.erase(valueToErase);
            } catch (Exception e) {
                // Ignore exceptions during benchmarking
            }
        }

        long endTime = System.nanoTime();
        double timeInMs = (endTime - startTime) / 1_000_000.0;
        double avgTimePerOperation = timeInMs / eraseOperations;

        return new Result(type.name(), "erase", timeInMs, avgTimePerOperation * 1_000_000, size);
    }

    /**
     * Benchmark the addAfter operation
     *
     * @param type The type of LinkedList
     * @param list The LinkedList instance
     * @param size The size of data to benchmark
     * @return The benchmark result
     */
    private Result benchmarkAddAfter(TypeLinkedList type, LinkedList<Integer> list, int size) {
        clearList(list);

        // Populate the list
        for (int i = 0; i < size; i++) {
            list.pushBack(i);
        }

        // Find a node in the middle of the list
        Node<Integer> node = list.find(size / 2);
        if (node == null) {
            // Fallback if node is not found
            return new Result(type.name(), "addAfter", 0, 0, size);
        }

        // Benchmark
        long startTime = System.nanoTime();

        // Add 10% of the elements to avoid making the list too large
        int addOperations = Math.max(1, size / 10);
        for (int i = 0; i < addOperations; i++) {
            try {
                list.addAfter(node, size + i);
            } catch (Exception e) {
                // Ignore exceptions during benchmarking
            }
        }

        long endTime = System.nanoTime();
        double timeInMs = (endTime - startTime) / 1_000_000.0;
        double avgTimePerOperation = timeInMs / addOperations;

        return new Result(type.name(), "addAfter", timeInMs, avgTimePerOperation * 1_000_000, size);
    }

    /**
     * Benchmark the addBefore operation
     *
     * @param type The type of LinkedList
     * @param list The LinkedList instance
     * @param size The size of data to benchmark
     * @return The benchmark result
     */
    private Result benchmarkAddBefore(TypeLinkedList type, LinkedList<Integer> list, int size) {
        clearList(list);

        // Populate the list
        for (int i = 0; i < size; i++) {
            list.pushBack(i);
        }

        // Find a node in the middle of the list
        Node<Integer> node = list.find(size / 2);
        if (node == null) {
            // Fallback if node is not found
            return new Result(type.name(), "addBefore", 0, 0, size);
        }

        // Benchmark
        long startTime = System.nanoTime();

        // Add 10% of the elements to avoid making the list too large
        int addOperations = Math.max(1, size / 10);
        for (int i = 0; i < addOperations; i++) {
            try {
                list.addBefore(node, size + i);
            } catch (Exception e) {
                // Ignore exceptions during benchmarking
            }
        }

        long endTime = System.nanoTime();
        double timeInMs = (endTime - startTime) / 1_000_000.0;
        double avgTimePerOperation = timeInMs / addOperations;

        return new Result(type.name(), "addBefore", timeInMs, avgTimePerOperation * 1_000_000, size);
    }

    /**
     * Clear a LinkedList
     *
     * @param list The LinkedList to clear
     */
    private void clearList(LinkedList<?> list) {
        while (!list.isEmpty()) {
            try {
                list.popFront();
            } catch (Exception e) {
                break;
            }
        }
    }

    /**
     * Export benchmark results to CSV
     *
     * @param results The benchmark results to export
     * @param filePath The path to save the CSV file
     * @throws IOException If an I/O error occurs
     */
    public void exportToCSV(List<Result> results, String filePath) throws IOException {
        try (FileWriter writer = new FileWriter(filePath)) {
            // Use US locale to ensure decimal points (not commas) are used
            Locale usLocale = Locale.US;
            
            writer.write("ListType,Operation,TestSize,TotalTimeMs,AvgTimeNs\n");

            for (Result result : results) {
                String operation = result.getOperation();
                
                // Format with US locale to ensure decimal points
                writer.write(String.format(usLocale, "%s,%s,%d,%.3f,%.3f\n",
                        result.getListType(),
                        operation,
                        result.getTestSize(),
                        result.getTotalTimeMs(),
                        result.getAvgTimeNs()));
            }
        }
    }

    /**
     * Print benchmark results to console
     *
     * @param results The benchmark results to print
     */
    public void printResults(List<Result> results) {
        // Use US locale to ensure decimal points (not commas) are used
        Locale usLocale = Locale.US;
        
        System.out.println("\nBenchmark Results:");
        System.out.println("----------------------------------------------------------------------");
        System.out.printf("%-25s | %-15s | %-10s | %-15s | %-15s\n",
                "List Type", "Operation", "Test Size", "Total Time (ms)", "Avg Time (ns)");
        System.out.println("----------------------------------------------------------------------");

        for (Result result : results) {
            String operation = result.getOperation();
            
            System.out.printf(usLocale, "%-25s | %-15s | %-10d | %-15.3f | %-15.3f\n",
                    result.getListType(),
                    operation,
                    result.getTestSize(),
                    result.getTotalTimeMs(),
                    result.getAvgTimeNs());
        }
    }

    /**
     * Find the fastest implementation for each operation
     *
     * @param results The benchmark results
     * @return A string with the fastest implementations
     */
    public String findFastestImplementations(List<Result> results) {
        // Use US locale to ensure decimal points (not commas) are used
        Locale usLocale = Locale.US;
        
        StringBuilder sb = new StringBuilder();
        sb.append("\nFastest Implementations:\n");
        sb.append("--------------------------------------------------\n");

        List<String> operations = new ArrayList<>();
        for (Result result : results) {
            String baseOperation = result.getOperation();
            if (!operations.contains(baseOperation)) {
                operations.add(baseOperation);
            }
        }

        for (String baseOperation : operations) {
            Result fastest = null;

            for (Result result : results) {
                String resultBaseOperation = result.getOperation();
                if (resultBaseOperation.equals(baseOperation)) {
                    if (fastest == null || result.getAvgTimeNs() < fastest.getAvgTimeNs()) {
                        fastest = result;
                    }
                }
            }

            if (fastest != null) {
                sb.append(String.format(usLocale, "%-15s: %-25s (%.3f ns)\n",
                        baseOperation, fastest.getListType(), fastest.getAvgTimeNs()));
            }
        }

        return sb.toString();
    }

    /**
     * Class to store benchmark results
     */
    public static class Result {
        private final String listType;
        private final String operation;
        private final double totalTimeMs;
        private final double avgTimeNs;
        private final int testSize;

        /**
         * Constructor for Result
         *
         * @param listType The type of LinkedList
         * @param operation The operation being benchmarked
         * @param totalTimeMs The total time in milliseconds
         * @param avgTimeNs The average time in nanoseconds
         * @param testSize The size of the test data
         */
        public Result(String listType, String operation, double totalTimeMs, double avgTimeNs, int testSize) {
            this.listType = listType;
            this.operation = operation;
            this.totalTimeMs = totalTimeMs;
            this.avgTimeNs = avgTimeNs;
            this.testSize = testSize;
        }

        /**
         * Get the type of LinkedList
         *
         * @return The type of LinkedList
         */
        public String getListType() {
            return listType;
        }

        /**
         * Get the operation being benchmarked
         *
         * @return The operation being benchmarked
         */
        public String getOperation() {
            return operation;
        }

        /**
         * Get the total time in milliseconds
         *
         * @return The total time in milliseconds
         */
        public double getTotalTimeMs() {
            return totalTimeMs;
        }

        /**
         * Get the average time in nanoseconds
         *
         * @return The average time in nanoseconds
         */
        public double getAvgTimeNs() {
            return avgTimeNs;
        }

        /**
         * Get the test size
         *
         * @return The test size
         */
        public int getTestSize() {
            return testSize;
        }

        @Override
        public String toString() {
            // Use US locale to ensure decimal points (not commas) are used
            return String.format(Locale.US, "%-25s | %-15s | %-10d | %-15.3f | %-15.3f",
                    listType, operation, testSize, totalTimeMs, avgTimeNs);
        }
    }
}
