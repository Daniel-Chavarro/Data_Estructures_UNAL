package org.tree_tester.model.concrete.Heap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the DaryMaxHeap class, which implements a max heap with a configurable degree.
 * This class tests various functionalities including insertion, extraction, and heap properties.
 */
@DisplayName("D-ary Max Heap Tests")
class DaryMaxHeapTest {
    /**
     * The DaryMaxHeap instance to be tested.
     */
    private DaryMaxHeap<Integer> maxHeap;

    /**
     * Sets up the test environment before each test case.
     * Initializes a new DaryMaxHeap with degree 2 (binary heap).
     */
    @BeforeEach
    void setUp() {
        maxHeap = new DaryMaxHeap<>(2); // Binary heap by default
    }

    /**
     * Tests the creation of an empty heap.
     * Verifies that the heap is empty, has size 0, height 0, and degree 2.
     */
    @Test
    @DisplayName("Test empty heap creation")
    void testEmptyHeapCreation() {
        assertTrue(maxHeap.isEmpty());
        assertEquals(0, maxHeap.getSize());
        assertEquals(0, maxHeap.getHeight());
        assertEquals(2, maxHeap.getDegree());
    }

    /**
     * Tests the insertion of a single element into the heap.
     * Verifies that the heap is not empty, size is 1, and the maximum element is at the root.
     */
    @Test
    @DisplayName("Test single element insertion")
    void testSingleElementInsertion() {
        maxHeap.insert(10);

        assertFalse(maxHeap.isEmpty());
        assertEquals(1, maxHeap.getSize());
        assertEquals(Integer.valueOf(10), maxHeap.peek());
        assertTrue(maxHeap.isValidHeap());
    }

    /**
     * Tests the insertion of multiple elements into the heap.
     * Verifies that the max heap property is maintained after each insertion.
     */
    @Test
    @DisplayName("Test multiple elements insertion maintains max heap property")
    void testMultipleElementsInsertion() {
        int[] elements = {50, 30, 70, 20, 40, 60, 80, 90};

        for (int element : elements) {
            maxHeap.insert(element);
            assertTrue(maxHeap.isValidHeap(), "Heap property should be maintained after inserting " + element);
        }

        assertEquals(elements.length, maxHeap.getSize());
        assertEquals(Integer.valueOf(90), maxHeap.peek()); // Maximum should be at root
    }

    /**
     * Tests the extraction of the root element from the heap.
     * Verifies that the extracted element is the maximum and that the heap property is maintained.
     */
    @Test
    @DisplayName("Test extract root maintains heap property")
    void testExtractRoot() {
        int[] elements = {50, 30, 70, 20, 40, 60, 80, 90};

        for (int element : elements) {
            maxHeap.insert(element);
        }

        Integer extracted = maxHeap.extractRoot();
        assertEquals(Integer.valueOf(90), extracted);
        assertTrue(maxHeap.isValidHeap());

        extracted = maxHeap.extractRoot();
        assertEquals(Integer.valueOf(80), extracted);
        assertTrue(maxHeap.isValidHeap());

        assertEquals(elements.length - 2, maxHeap.getSize());
    }

    /**
     * Tests the peek operation, which retrieves the maximum element without modifying the heap.
     * Verifies that the peeked value is correct and that the heap size remains unchanged.
     */
    @Test
    @DisplayName("Test peek without modifying heap")
    void testPeek() {
        assertNull(maxHeap.peek()); // Empty heap

        maxHeap.insert(50);
        maxHeap.insert(30);
        maxHeap.insert(70);

        assertEquals(Integer.valueOf(70), maxHeap.peek());
        assertEquals(3, maxHeap.getSize()); // Size should not change
        assertTrue(maxHeap.isValidHeap());
    }

    /**
     * Tests the buildHeap method, which constructs a max heap from a list of elements.
     * Verifies that the resulting heap has the correct size and maximum element at the root.
     */
    @Test
    @DisplayName("Test build heap from list")
    void testBuildHeapFromList() {
        List<Integer> data = Arrays.asList(50, 30, 70, 20, 40, 60, 80, 90);

        maxHeap.buildHeap(data);

        assertEquals(data.size(), maxHeap.getSize());
        assertEquals(Integer.valueOf(90), maxHeap.peek());
        assertTrue(maxHeap.isValidHeap());
    }

    /**
     * Tests the behavior of the heap with different degrees.
     * Verifies that the degree is set correctly and that the heap maintains its properties.
     */
    @ParameterizedTest
    @ValueSource(ints = {2, 3, 4, 5})
    @DisplayName("Test different degrees")
    void testDifferentDegrees(int degree) {
        DaryMaxHeap<Integer> heap = new DaryMaxHeap<>(degree);

        assertEquals(degree, heap.getDegree());
        assertTrue(heap.isEmpty());

        for (int i = 1; i <= 100; i++) {
            heap.insert(i);
        }

        assertTrue(heap.isValidHeap());
        assertEquals(Integer.valueOf(100), heap.peek());
    }

    /**
     * Tests the behavior of the heap when inserting invalid elements.
     * Verifies that an exception is thrown for invalid degrees.
     */
    @Test
    @DisplayName("Test invalid degree throws exception")
    void testInvalidDegreeThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new DaryMaxHeap<>(1); // Degree must be at least 2
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new DaryMaxHeap<>(0);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new DaryMaxHeap<>(-1);
        });
    }

    /**
     * Tests the clear functionality of the heap.
     * Verifies that the heap is empty after clearing and that the size is reset to 0.
     */
    @Test
    @DisplayName("Test clear functionality")
    void testClear() {
        maxHeap.insert(50);
        maxHeap.insert(30);
        maxHeap.insert(70);

        assertFalse(maxHeap.isEmpty());
        assertEquals(3, maxHeap.getSize());

        maxHeap.clear();

        assertTrue(maxHeap.isEmpty());
        assertEquals(0, maxHeap.getSize());
        assertNull(maxHeap.peek());
    }

    /**
     * Tests the heap type retrieval.
     * Verifies that the heap type is correctly identified as a max heap.
     */
    @Test
    @DisplayName("Test heap type")
    void testGetHeapType() {
        assertTrue(maxHeap.getHeapType().contains("Max"));
    }

    /**
     * Tests the heap's height calculation.
     * Verifies that the height is calculated correctly based on the number of elements.
     */
    @Test
    @DisplayName("Test height calculation")
    void testHeightCalculation() {
        assertEquals(0, maxHeap.getHeight()); // Empty heap

        maxHeap.insert(10);
        assertEquals(1, maxHeap.getHeight()); // Single element

        for (int i = 11; i <= 17; i++) {
            maxHeap.insert(i);
        }

        int expectedHeight = (int) Math.ceil(Math.log(8 + 1) / Math.log(2));
        assertEquals(expectedHeight, maxHeap.getHeight());
    }

    /**
     * Tests the heap with string elements.
     * Verifies that the max heap property is maintained with string comparisons.
     */
    @Test
    @DisplayName("Test with string elements")
    void testWithStringElements() {
        DaryMaxHeap<String> stringHeap = new DaryMaxHeap<>(2);

        String[] words = {"dog", "cat", "elephant", "ant", "bear"};

        for (String word : words) {
            stringHeap.insert(word);
        }

        assertEquals("elephant", stringHeap.peek()); // Lexicographically largest
        assertEquals(words.length, stringHeap.getSize());
        assertTrue(stringHeap.isValidHeap());
    }

    /**
     * Tests the heap with a large number of elements.
     * Verifies that the heap can handle large sizes and maintains its properties.
     */
    @Test
    @DisplayName("Test reverse heap sort using extract operations")
    void testReverseHeapSort() {
        int[] unsorted = {64, 34, 25, 12, 22, 11, 90};

        for (int value : unsorted) {
            maxHeap.insert(value);
        }


        int[] reverseSorted = new int[unsorted.length];
        for (int i = 0; i < reverseSorted.length; i++) {
            reverseSorted[i] = maxHeap.extractRoot();
        }

        for (int i = 1; i < reverseSorted.length; i++) {
            assertTrue(reverseSorted[i - 1] >= reverseSorted[i], "Array should be sorted in descending order");
        }

        assertTrue(maxHeap.isEmpty());
    }

    /**
     * Tests the heap's behavior when inserting duplicate elements.
     * Verifies that duplicates are handled correctly and the max heap property is maintained.
     */
    @Test
    @DisplayName("Test comparison with min heap behavior")
    void testComparisonWithMinHeap() {
        DaryMinHeap<Integer> minHeap = new DaryMinHeap<>(2);

        int[] elements = {50, 30, 70, 20, 40, 60, 80};

        for (int element : elements) {
            maxHeap.insert(element);
            minHeap.insert(element);
        }

        assertEquals(Integer.valueOf(80), maxHeap.peek());
        assertEquals(Integer.valueOf(20), minHeap.peek());

        assertEquals(maxHeap.getSize(), minHeap.getSize());


        assertTrue(maxHeap.isValidHeap());
        assertTrue(minHeap.isValidHeap());
    }

    /**
     * Tests the priority queue behavior of the max heap.
     * Verifies that elements are extracted in order of priority (highest first).
     */
    @Test
    @DisplayName("Test priority queue behavior")
    void testPriorityQueueBehavior() {
        maxHeap.insert(10);
        maxHeap.insert(50);
        maxHeap.insert(30);
        maxHeap.insert(70);
        maxHeap.insert(60);


        assertEquals(Integer.valueOf(70), maxHeap.extractRoot());
        assertEquals(Integer.valueOf(60), maxHeap.extractRoot());
        assertEquals(Integer.valueOf(50), maxHeap.extractRoot());
        assertEquals(Integer.valueOf(30), maxHeap.extractRoot());
        assertEquals(Integer.valueOf(10), maxHeap.extractRoot());

        assertTrue(maxHeap.isEmpty());
    }
}
