package org.tree_tester.controller;

/**
 * Main entry point for the interactive benchmark system.
 * This application provides a console-based interface for running
 * performance benchmarks on various tree and heap data structures
 * using the Java Microbenchmark Harness (JMH) framework.
 *
 * <p>The system supports benchmarking:
 * <ul>
 *   <li>AVL Trees and Binary Search Trees</li>
 *   <li>D-ary Min and Max Heaps with configurable degrees</li>
 * </ul>
 *
 * <p>Results are automatically exported to CSV files with timestamps
 * for further analysis and comparison.
 */
public class AplMain {

    /**
     * Entry point of the application.
     * Initializes and starts the interactive benchmark controller.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        BenchmarkController controller = new BenchmarkController();
        controller.start();
    }
}
