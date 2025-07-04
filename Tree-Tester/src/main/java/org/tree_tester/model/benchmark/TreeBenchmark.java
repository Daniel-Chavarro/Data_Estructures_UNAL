package org.tree_tester.model.benchmark;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.tree_tester.model.Abstract.Tree;
import org.tree_tester.model.concrete.Tree.AVLTree;
import org.tree_tester.model.concrete.Tree.BinarySearchTree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * JMH benchmark class for testing performance of different tree implementations.
 * Benchmarks various operations including insert, search, delete, and traversals
 * on AVL trees and Binary Search Trees with different data sizes.
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Benchmark)
@Fork(1)
@Warmup(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
public class TreeBenchmark {

    @Param({"100", "1000", "10000"})
    private int dataSize;

    @Param({"AVL", "BST"})
    private String treeType;

    private Tree<Integer> tree;
    private List<Integer> insertData;
    private List<Integer> searchData;
    private List<Integer> deleteData;

    @Setup(Level.Trial)
    public void setup() {
        // Crear el árbol según el tipo especificado
        switch (treeType) {
            case "AVL":
                tree = new AVLTree<>();
                break;
            case "BST":
                tree = new BinarySearchTree<>();
                break;
            default:
                throw new IllegalArgumentException("Unknown tree type: " + treeType);
        }

        // Generar datos de prueba
        Random random = new Random(42); // Semilla fija para reproducibilidad

        insertData = new ArrayList<>();
        for (int i = 0; i < dataSize; i++) {
            insertData.add(random.nextInt(dataSize * 10));
        }

        searchData = new ArrayList<>(insertData);
        Collections.shuffle(searchData, random);

        deleteData = new ArrayList<>(insertData.subList(0, Math.min(dataSize / 2, insertData.size())));
        Collections.shuffle(deleteData, random);
    }

    @Setup(Level.Invocation)
    public void setupInvocation() {
        tree.clear();
    }

    @Benchmark
    public void insertBenchmark() {
        for (Integer value : insertData) {
            tree.insert(value);
        }
    }

    @Benchmark
    public void searchBenchmark() {
        // Primero insertar datos
        for (Integer value : insertData) {
            tree.insert(value);
        }

        // Luego buscar
        for (Integer value : searchData) {
            tree.search(value);
        }
    }

    @Benchmark
    public void deleteBenchmark() {
        // Primero insertar datos
        for (Integer value : insertData) {
            tree.insert(value);
        }

        // Luego eliminar algunos
        for (Integer value : deleteData) {
            tree.delete(value);
        }
    }

    @Benchmark
    public void mixedOperationsBenchmark() {
        Random random = new Random(42);

        for (int i = 0; i < dataSize; i++) {
            int operation = random.nextInt(3);
            int value = random.nextInt(dataSize * 10);

            switch (operation) {
                case 0: // Insert
                    tree.insert(value);
                    break;
                case 1: // Search
                    tree.search(value);
                    break;
                case 2: // Delete
                    tree.delete(value);
                    break;
            }
        }
    }
}
