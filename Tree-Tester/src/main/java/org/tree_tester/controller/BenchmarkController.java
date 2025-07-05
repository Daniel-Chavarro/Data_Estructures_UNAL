package org.tree_tester.controller;

import org.openjdk.jmh.results.RunResult;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.tree_tester.model.benchmark.HeapBenchmark;
import org.tree_tester.model.benchmark.TreeBenchmark;
import org.tree_tester.model.concrete.DataSize;
import org.tree_tester.model.concrete.HeapDegree;
import org.tree_tester.model.concrete.TreeType;
import org.tree_tester.view.ConsoleView;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Interactive benchmark controller for managing tree and heap performance tests.
 * Provides a console interface for configuring and executing JMH benchmarks with robust input validation.
 */
public class BenchmarkController {
    
    private final ConsoleView view;
    private static final String RESULTS_DIR = "results";
    
    public BenchmarkController() {
        this.view = new ConsoleView();
        try {
            Files.createDirectories(Paths.get(RESULTS_DIR));
        } catch (IOException e) {
            view.println("Warning: Could not create results directory: " + e.getMessage());
        }
    }
    
    /**
     * Starts the interactive benchmark interface.
     */
    public void start() {
        showWelcome();
        
        boolean running = true;
        while (running) {
            showMainMenu();
            int choice = view.readInt("Select an option: ");
            
            switch (choice) {
                case 1:
                    runTreeBenchmarks();
                    break;
                case 2:
                    runHeapBenchmarks();
                    break;
                case 3:
                    runAllBenchmarks();
                    break;
                case 4:
                    running = false;
                    view.println("Goodbye!");
                    break;
                default:
                    view.println("Invalid option. Please select between 1-4.");
            }
        }
    }
    
    private void showWelcome() {
        view.println("=====================================");
        view.println("    JMH BENCHMARK SYSTEM");
        view.println("    Trees and Heaps - UNAL");
        view.println("=====================================");
    }
    
    private void showMainMenu() {
        view.println("\n--- MAIN MENU ---");
        view.println("1. Run Tree benchmarks");
        view.println("2. Run Heap benchmarks");
        view.println("3. Run all benchmarks");
        view.println("4. Exit");
    }
    
    /**
     * Runs the tree benchmarks with the specified configuration.
     */
    private void runTreeBenchmarks() {
        view.println("\n=== TREE BENCHMARKS ===");
        
        BenchmarkConfig config = getTreeBenchmarkConfig();
        
        try {
            view.println("Starting tree benchmarks...");
            view.println("This may take several minutes. Please wait...");
            
            Options opt = new OptionsBuilder()
                    .include(TreeBenchmark.class.getSimpleName())
                    .param("dataSize", config.getDataSizes())
                    .param("treeType", config.getTreeTypes())
                    .forks(config.getForks())
                    .warmupIterations(config.getWarmupIterations())
                    .measurementIterations(config.getMeasurementIterations())
                    .build();
            
            Collection<RunResult> results = new Runner(opt).run();
            
            String filename = exportTreeResultsToCSV(results);
            view.println("\n✓ Tree benchmarks completed!");
            view.println("Results exported to: " + filename);
            showResultsSummary(results, "TREES");
            
        } catch (RunnerException e) {
            view.println("❌ Error running tree benchmarks: " + e.getMessage());
        }
    }
    
    /**
     * Runs the heap benchmarks with the specified configuration.
     */
    private void runHeapBenchmarks() {
        view.println("\n=== HEAP BENCHMARKS ===");
        
        BenchmarkConfig config = getHeapBenchmarkConfig();
        
        try {
            view.println("Starting heap benchmarks...");
            view.println("This may take several minutes. Please wait...");
            
            Options opt = new OptionsBuilder()
                    .include(HeapBenchmark.class.getSimpleName())
                    .param("dataSize", config.getDataSizes())
                    .param("degree", config.getDegrees())
                    .param("heapType", config.getHeapTypes())
                    .forks(config.getForks())
                    .warmupIterations(config.getWarmupIterations())
                    .measurementIterations(config.getMeasurementIterations())
                    .build();
            
            Collection<RunResult> results = new Runner(opt).run();
            
            String filename = exportHeapResultsToCSV(results);
            view.println("\n✓ Heap benchmarks completed!");
            view.println("Results exported to: " + filename);
            showResultsSummary(results, "HEAPS");
            
        } catch (RunnerException e) {
            view.println("❌ Error running heap benchmarks: " + e.getMessage());
        }
    }
    
    /**
     * Runs all available benchmarks (trees and heaps) with default configuration.
     */
    private void runAllBenchmarks() {
        view.println("\n=== RUNNING ALL BENCHMARKS ===");
        view.println("Tree and heap benchmarks will be executed with default configuration.");
        view.println("This may take a long time. Continue? (y/n)");
        
        String confirm = view.readString("Response: ");
        if (!confirm.toLowerCase().startsWith("y")) {
            view.println("Operation cancelled.");
            return;
        }
        
        runTreeBenchmarks();
        runHeapBenchmarks();
        
        view.println("\n🎉 All benchmarks completed!");
    }
    
    /**
     * Gets the benchmark configuration for tree benchmarks from the user with validation.
     */
    private BenchmarkConfig getTreeBenchmarkConfig() {
        BenchmarkConfig config = new BenchmarkConfig();
        
        view.println("\n--- Tree benchmark configuration ---");
        
        // Get and validate data sizes
        config.setDataSizes(getValidatedDataSizes());

        // Get and validate tree types
        config.setTreeTypes(getValidatedTreeTypes());

        setCommonBenchmarkConfig(config);
        return config;
    }
    
    /**
     * Gets the benchmark configuration for heap benchmarks from the user with validation.
     */
    private BenchmarkConfig getHeapBenchmarkConfig() {
        BenchmarkConfig config = new BenchmarkConfig();
        
        view.println("\n--- Heap benchmark configuration ---");
        
        // Get and validate data sizes (limited for heaps)
        config.setDataSizes(getValidatedDataSizesForHeaps());

        // Get and validate heap degrees
        config.setDegrees(getValidatedHeapDegrees());

        // Get and validate heap types
        config.setHeapTypes(getValidatedHeapTypes());

        setCommonBenchmarkConfig(config);
        return config;
    }
    
    /**
     * Gets and validates data sizes input from user.
     */
    private String[] getValidatedDataSizes() {
        view.println("Available data sizes:");
        view.println(DataSize.getAvailableSizes());

        while (true) {
            try {
                String input = view.readString("Enter sizes separated by comma (enter for all): ");

                if (input.trim().isEmpty()) {
                    return Arrays.stream(DataSize.values())
                            .map(size -> String.valueOf(size.getValue()))
                            .toArray(String[]::new);
                }

                List<String> validSizes = new ArrayList<>();
                String[] parts = input.split(",");

                for (String part : parts) {
                    String trimmed = part.trim();
                    if (!trimmed.isEmpty()) {
                        DataSize size = DataSize.fromString(trimmed);
                        validSizes.add(String.valueOf(size.getValue()));
                    }
                }

                if (validSizes.isEmpty()) {
                    throw new IllegalArgumentException("No valid sizes provided");
                }

                view.println("Selected sizes: " + validSizes.stream()
                        .map(s -> s + " elements")
                        .collect(Collectors.joining(", ")));

                return validSizes.toArray(new String[0]);

            } catch (IllegalArgumentException e) {
                view.println("❌ " + e.getMessage());
                view.println("Please try again.");
            }
        }
    }

    /**
     * Gets and validates data sizes for heaps (limited to reasonable sizes).
     */
    private String[] getValidatedDataSizesForHeaps() {
        view.println("Available data sizes for heaps:");
        List<DataSize> heapSizes = Arrays.asList(DataSize.SMALL, DataSize.MEDIUM, DataSize.LARGE, DataSize.EXTRA_LARGE, DataSize.HUGE);
        view.println(heapSizes.stream()
                .map(size -> size.getValue() + " (" + size.getDescription() + ")")
                .collect(Collectors.joining(", ")));

        while (true) {
            try {
                String input = view.readString("Enter sizes separated by comma (enter for all): ");

                if (input.trim().isEmpty()) {
                    return heapSizes.stream()
                            .map(size -> String.valueOf(size.getValue()))
                            .toArray(String[]::new);
                }

                List<String> validSizes = new ArrayList<>();
                String[] parts = input.split(",");

                for (String part : parts) {
                    String trimmed = part.trim();
                    if (!trimmed.isEmpty()) {
                        DataSize size = DataSize.fromString(trimmed);
                        if (heapSizes.contains(size)) {
                            validSizes.add(String.valueOf(size.getValue()));
                        } else {
                            throw new IllegalArgumentException("Size " + trimmed + " is too large for heap benchmarks. Use sizes up to " + DataSize.LARGE.getValue());
                        }
                    }
                }

                if (validSizes.isEmpty()) {
                    throw new IllegalArgumentException("No valid sizes provided");
                }

                view.println("Selected sizes: " + validSizes.stream()
                        .map(s -> s + " elements")
                        .collect(Collectors.joining(", ")));

                return validSizes.toArray(new String[0]);

            } catch (IllegalArgumentException e) {
                view.println("❌ " + e.getMessage());
                view.println("Please try again.");
            }
        }
    }

    /**
     * Gets and validates tree types input from user.
     */
    private String[] getValidatedTreeTypes() {
        List<TreeType> treeTypes = Arrays.asList(TreeType.AVL, TreeType.BST);
        view.println("Available tree types:");
        view.println(treeTypes.stream()
                .map(type -> type.name() + " (" + type.getDescription() + ")")
                .collect(Collectors.joining(", ")));

        while (true) {
            try {
                String input = view.readString("Enter types separated by comma (enter for both): ");

                if (input.trim().isEmpty()) {
                    return treeTypes.stream()
                            .map(TreeType::name)
                            .toArray(String[]::new);
                }

                List<String> validTypes = new ArrayList<>();
                String[] parts = input.split(",");

                for (String part : parts) {
                    String trimmed = part.trim().toUpperCase();
                    if (!trimmed.isEmpty()) {
                        TreeType type = parseTreeType(trimmed);
                        if (treeTypes.contains(type)) {
                            validTypes.add(type.name());
                        }
                    }
                }

                if (validTypes.isEmpty()) {
                    throw new IllegalArgumentException("No valid tree types provided");
                }

                view.println("Selected types: " + validTypes.stream()
                        .collect(Collectors.joining(", ")));

                return validTypes.toArray(new String[0]);

            } catch (IllegalArgumentException e) {
                view.println("❌ " + e.getMessage());
                view.println("Please try again.");
            }
        }
    }

    /**
     * Gets and validates heap degrees input from user.
     */
    private String[] getValidatedHeapDegrees() {
        view.println("Available heap degrees:");
        view.println(Arrays.stream(HeapDegree.values())
                .map(degree -> degree.getValue() + " (" + degree.getDescription() + ")")
                .collect(Collectors.joining(", ")));

        while (true) {
            try {
                String input = view.readString("Enter degrees separated by comma (enter for all): ");

                if (input.trim().isEmpty()) {
                    return Arrays.stream(HeapDegree.values())
                            .map(degree -> String.valueOf(degree.getValue()))
                            .toArray(String[]::new);
                }

                List<String> validDegrees = new ArrayList<>();
                String[] parts = input.split(",");

                for (String part : parts) {
                    String trimmed = part.trim();
                    if (!trimmed.isEmpty()) {
                        HeapDegree degree = HeapDegree.fromString(trimmed);
                        validDegrees.add(String.valueOf(degree.getValue()));
                    }
                }

                if (validDegrees.isEmpty()) {
                    throw new IllegalArgumentException("No valid degrees provided");
                }

                view.println("Selected degrees: " + validDegrees.stream()
                        .collect(Collectors.joining(", ")));

                return validDegrees.toArray(new String[0]);

            } catch (IllegalArgumentException e) {
                view.println("❌ " + e.getMessage());
                view.println("Please try again.");
            }
        }
    }

    /**
     * Gets and validates heap types input from user.
     */
    private String[] getValidatedHeapTypes() {
        List<TreeType> heapTypes = Arrays.asList(TreeType.MIN_HEAP, TreeType.MAX_HEAP);
        view.println("Available heap types:");
        view.println(heapTypes.stream()
                .map(type -> type.name().replace("_HEAP", "") + " (" + type.getDescription() + ")")
                .collect(Collectors.joining(", ")));

        while (true) {
            try {
                String input = view.readString("Enter types separated by comma (enter for both): ");

                if (input.trim().isEmpty()) {
                    return heapTypes.stream()
                            .map(type -> type.name().replace("_HEAP", ""))
                            .toArray(String[]::new);
                }

                List<String> validTypes = new ArrayList<>();
                String[] parts = input.split(",");

                for (String part : parts) {
                    String trimmed = part.trim().toUpperCase();
                    if (!trimmed.isEmpty()) {
                        // Handle both "MIN" and "MIN_HEAP" formats
                        if (!trimmed.endsWith("_HEAP")) {
                            trimmed += "_HEAP";
                        }
                        TreeType type = parseTreeType(trimmed);
                        if (heapTypes.contains(type)) {
                            validTypes.add(type.name().replace("_HEAP", ""));
                        }
                    }
                }

                if (validTypes.isEmpty()) {
                    throw new IllegalArgumentException("No valid heap types provided");
                }

                view.println("Selected types: " + validTypes.stream()
                        .collect(Collectors.joining(", ")));

                return validTypes.toArray(new String[0]);

            } catch (IllegalArgumentException e) {
                view.println("❌ " + e.getMessage());
                view.println("Please try again.");
            }
        }
    }

    /**
     * Parses a string to TreeType enum with error handling.
     */
    private TreeType parseTreeType(String input) {
        try {
            return TreeType.valueOf(input.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid type: " + input + ". Valid types: AVL, BST, MIN, MAX");
        }
    }

    /**
     * Sets the common configuration parameters for benchmarks.
     */
    private void setCommonBenchmarkConfig(BenchmarkConfig config) {
        view.println("\n--- Advanced configuration ---");
        
        int forks = view.readInt("Number of forks (recommended 1): ");
        config.setForks(forks);
        
        int warmup = view.readInt("Warmup iterations (recommended 3): ");
        config.setWarmupIterations(warmup);
        
        int measurement = view.readInt("Measurement iterations (recommended 5): ");
        config.setMeasurementIterations(measurement);
    }
    
    /**
     * Exports the results of tree benchmarks to a CSV file.
     */
    private String exportTreeResultsToCSV(Collection<RunResult> results) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String filename = RESULTS_DIR + "/tree_benchmark_results_" + timestamp + ".csv";
        
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write("Benchmark,DataSize,TreeType,Mode,Count,Score,Error,Units\n");

            for (RunResult result : results) {
                // Get benchmark name from BenchmarkParams instead of label
                String methodName = result.getParams().getBenchmark();

                // Extract just the method name from the full class.method format
                if (methodName.contains(".")) {
                    methodName = methodName.substring(methodName.lastIndexOf('.') + 1);
                }

                String dataSize = "";
                String treeType = "";

                // Get parameters from BenchmarkParams
                if (result.getParams() != null && result.getParams().getParamsKeys() != null) {
                    for (String key : result.getParams().getParamsKeys()) {
                        String value = result.getParams().getParam(key);

                        if ("dataSize".equals(key)) {
                            dataSize = value;
                        } else if ("treeType".equals(key)) {
                            treeType = value;
                        }
                    }
                }

                double score = result.getPrimaryResult().getScore();
                double error = result.getPrimaryResult().getScoreError();
                String unit = result.getPrimaryResult().getScoreUnit();
                String mode = result.getParams().getMode().shortLabel();
                int forks = result.getParams().getForks();

                // Use Locale.US to ensure decimal point instead of comma
                writer.write(String.format(Locale.US, "%s,%s,%s,%s,%d,%.3f,%.3f,%s\n",
                        methodName, dataSize, treeType, mode, forks, score, error, unit));
            }
        } catch (IOException e) {
            view.println("Error writing CSV file: " + e.getMessage());
        }
        
        return filename;
    }
    
    /**
     * Exports the results of heap benchmarks to a CSV file.
     */
    private String exportHeapResultsToCSV(Collection<RunResult> results) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String filename = RESULTS_DIR + "/heap_benchmark_results_" + timestamp + ".csv";
        
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write("Benchmark,DataSize,HeapType,Degree,Mode,Count,Score,Error,Units\n");

            for (RunResult result : results) {
                // Get benchmark name from BenchmarkParams instead of label
                String methodName = result.getParams().getBenchmark();

                // Extract just the method name from the full class.method format
                if (methodName.contains(".")) {
                    methodName = methodName.substring(methodName.lastIndexOf('.') + 1);
                }

                String dataSize = "";
                String heapType = "";
                String degree = "";

                // Get parameters from BenchmarkParams
                if (result.getParams() != null && result.getParams().getParamsKeys() != null) {
                    for (String key : result.getParams().getParamsKeys()) {
                        String value = result.getParams().getParam(key);

                        switch (key) {
                            case "dataSize":
                                dataSize = value;
                                break;
                            case "heapType":
                                heapType = value;
                                break;
                            case "degree":
                                degree = value;
                                break;
                        }
                    }
                }

                double score = result.getPrimaryResult().getScore();
                double error = result.getPrimaryResult().getScoreError();
                String unit = result.getPrimaryResult().getScoreUnit();
                String mode = result.getParams().getMode().shortLabel();
                int forks = result.getParams().getForks();

                // Use Locale.US to ensure decimal point instead of comma
                writer.write(String.format(Locale.US, "%s,%s,%s,%s,%s,%d,%.3f,%.3f,%s\n",
                        methodName, dataSize, heapType, degree, mode, forks, score, error, unit));
            }
        } catch (IOException e) {
            view.println("Error writing CSV file: " + e.getMessage());
        }
        
        return filename;
    }
    
    /**
     * Shows a summary of the benchmark results in the console.
     */
    private void showResultsSummary(Collection<RunResult> results, String benchmarkType) {
        view.println("\n--- " + benchmarkType + " RESULTS SUMMARY ---");
        view.println("Total benchmarks executed: " + results.size());
        
        double totalTime = 0;
        for (RunResult result : results) {
            totalTime += result.getPrimaryResult().getScore();
        }
        
        view.println(String.format("Average total time: %.3f μs", totalTime / results.size()));
        view.println("Time unit: microseconds (μs)");
        view.println("Detailed results are available in the generated CSV file.");
    }
    
    /**
     * Encapsulates the configuration parameters for benchmarks.
     */
    private static class BenchmarkConfig {
        private String[] dataSizes;
        private String[] treeTypes;
        private String[] heapTypes;
        private String[] degrees;
        private int forks = 1;
        private int warmupIterations = 3;
        private int measurementIterations = 5;

        public String[] getDataSizes() { return dataSizes; }
        public void setDataSizes(String[] dataSizes) { this.dataSizes = dataSizes; }

        public String[] getTreeTypes() { return treeTypes; }
        public void setTreeTypes(String[] treeTypes) { this.treeTypes = treeTypes; }

        public String[] getHeapTypes() { return heapTypes; }
        public void setHeapTypes(String[] heapTypes) { this.heapTypes = heapTypes; }

        public String[] getDegrees() { return degrees; }
        public void setDegrees(String[] degrees) { this.degrees = degrees; }

        public int getForks() { return forks; }
        public void setForks(int forks) { this.forks = forks; }

        public int getWarmupIterations() { return warmupIterations; }
        public void setWarmupIterations(int warmupIterations) { this.warmupIterations = warmupIterations; }

        public int getMeasurementIterations() { return measurementIterations; }
        public void setMeasurementIterations(int measurementIterations) { this.measurementIterations = measurementIterations; }
    }
}
