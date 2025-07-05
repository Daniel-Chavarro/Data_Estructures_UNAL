package org.tree_tester.controller;

import org.openjdk.jmh.results.RunResult;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.tree_tester.model.benchmark.TreeBenchmark;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Locale;

/**
 * Quick test to verify CSV generation works correctly.
 * This is a temporary testing class for validating benchmark CSV output.
 */
public class QuickCSVTest {

    public static void main(String[] args) throws RunnerException {
        System.out.println("=== Quick CSV Test ===");
        System.out.println("Running a small benchmark to test CSV generation...");

        // Run a quick benchmark with minimal data and multiple threads
        Options opt = new OptionsBuilder()
                .include(TreeBenchmark.class.getSimpleName() + ".insertBenchmark")
                .param("dataSize", "100", "1000")
                .param("treeType", "AVL", "BST")
                .forks(1)
                .warmupIterations(1)
                .measurementIterations(2)
                .build();

        Collection<RunResult> results = new Runner(opt).run();

        // Export to CSV using the same logic as BenchmarkController
        String filename = exportTestResultsToCSV(results);

        System.out.println("\n✓ Test completed!");
        System.out.println("Results exported to: " + filename);
        System.out.println("Total benchmarks: " + results.size());

        // Print some sample results to console
        System.out.println("\nSample results:");
        for (RunResult result : results) {
            String label = result.getPrimaryResult().getLabel();
            double score = result.getPrimaryResult().getScore();
            double error = result.getPrimaryResult().getScoreError();
            String unit = result.getPrimaryResult().getScoreUnit();
            System.out.printf("%s: %.3f ± %.3f %s%n", label, score, error, unit);
        }
    }

    /**
     * Exports test results to CSV using the same format as BenchmarkController.
     *
     * @param results the JMH benchmark results
     * @return the filename of the generated CSV file
     */
    private static String exportTestResultsToCSV(Collection<RunResult> results) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String filename = "results/test_benchmark_results_" + timestamp + ".csv";

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

                writer.write(String.format(Locale.US,"%s,%s,%s,%s,%d,%.3f,%.3f,%s\n",
                        methodName, dataSize, treeType, mode, forks, score, error, unit));
            }
        } catch (IOException e) {
            System.err.println("Error writing CSV file: " + e.getMessage());
        }

        return filename;
    }
}
