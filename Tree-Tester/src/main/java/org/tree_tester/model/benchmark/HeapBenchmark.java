package org.tree_tester.model.benchmark;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.tree_tester.model.Abstract.Heap;
import org.tree_tester.model.concrete.Heap.DaryMaxHeap;
import org.tree_tester.model.concrete.Heap.DaryMinHeap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
/**
 * JMH benchmark class for testing performance of different heap implementations.
 * Benchmarks various operations including insert, extract root, build heap, and mixed operations
 * on D-ary Min and Max heaps with different degrees and data sizes.
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Benchmark)
@Fork(1)
@Warmup(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
public class HeapBenchmark {

    @Param({"100", "1000", "10000", "100000", "1000000", "10000000", "100000000"})
    private int dataSize;

    @Param({"2", "3", "4"})
    private int degree;

    @Param({"MIN", "MAX"})
    private String heapType;

    private Heap<Integer> heap;
    private List<Integer> insertData;
    private List<Integer> buildHeapData;

    @Setup(Level.Trial)
    public void setup() {
        switch (heapType) {
            case "MIN":
                heap = new DaryMinHeap<>(degree);
                break;
            case "MAX":
                heap = new DaryMaxHeap<>(degree);
                break;
            default:
                throw new IllegalArgumentException("Unknown heap type: " + heapType);
        }

        Random random = new Random(42);

        insertData = new ArrayList<>();
        for (int i = 0; i < dataSize; i++) {
            insertData.add(random.nextInt(dataSize * 10));
        }

        buildHeapData = new ArrayList<>(insertData);
        Collections.shuffle(buildHeapData, random);
    }

    @Setup(Level.Invocation)
    public void setupInvocation() {
        heap.clear();
    }

    @Benchmark
    public void insertBenchmark() {
        for (Integer value : insertData) {
            heap.insert(value);
        }
    }

    @Benchmark
    public void extractRootBenchmark() {

        for (Integer value : insertData) {
            heap.insert(value);
        }

        while (!heap.isEmpty()) {
            heap.extractRoot();
        }
    }

    @Benchmark
    public void buildHeapBenchmark() {
        heap.buildHeap(buildHeapData);
    }

    @Benchmark
    public void peekBenchmark() {
        for (Integer value : insertData) {
            heap.insert(value);
        }

        for (int i = 0; i < dataSize; i++) {
            heap.peek();
        }
    }

    @Benchmark
    public void mixedOperationsBenchmark() {
        Random random = new Random(42);

        for (int i = 0; i < dataSize; i++) {
            int operation = random.nextInt(3);
            int value = random.nextInt(dataSize * 10);

            switch (operation) {
                case 0:
                    heap.insert(value);
                    break;
                case 1:
                    if (!heap.isEmpty()) {
                        heap.peek();
                    }
                    break;
                case 2:
                    if (!heap.isEmpty()) {
                        heap.extractRoot();
                    }
                    break;
            }
        }
    }

    @Benchmark
    public void heapSortBenchmark() {
        heap.buildHeap(buildHeapData);

        List<Integer> sorted = new ArrayList<>();
        while (!heap.isEmpty()) {
            sorted.add(heap.extractRoot());
        }
    }
}
