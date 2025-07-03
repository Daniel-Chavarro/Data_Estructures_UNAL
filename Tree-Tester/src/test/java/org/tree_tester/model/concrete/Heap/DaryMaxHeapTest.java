package org.tree_tester.model.concrete.Heap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("D-ary Max Heap Tests")
class DaryMaxHeapTest {

    private DaryMaxHeap<Integer> maxHeap;

    @BeforeEach
    void setUp() {
        maxHeap = new DaryMaxHeap<>(2); // Binary heap by default
    }

    @Test
    @DisplayName("Test empty heap creation")
    void testEmptyHeapCreation() {
        assertTrue(maxHeap.isEmpty());
        assertEquals(0, maxHeap.getSize());
        assertEquals(0, maxHeap.getHeight());
        assertEquals(2, maxHeap.getDegree());
    }

    @Test
    @DisplayName("Test single element insertion")
    void testSingleElementInsertion() {
        maxHeap.insert(10);

        assertFalse(maxHeap.isEmpty());
        assertEquals(1, maxHeap.getSize());
        assertEquals(Integer.valueOf(10), maxHeap.peek());
        assertTrue(maxHeap.isValidHeap());
    }

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

    @Test
    @DisplayName("Test extract root maintains heap property")
    void testExtractRoot() {
        int[] elements = {50, 30, 70, 20, 40, 60, 80, 90};

        for (int element : elements) {
            maxHeap.insert(element);
        }

        // Extract elements should come out in descending order
        Integer extracted = maxHeap.extractRoot();
        assertEquals(Integer.valueOf(90), extracted);
        assertTrue(maxHeap.isValidHeap());

        extracted = maxHeap.extractRoot();
        assertEquals(Integer.valueOf(80), extracted);
        assertTrue(maxHeap.isValidHeap());

        assertEquals(elements.length - 2, maxHeap.getSize());
    }

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

    @Test
    @DisplayName("Test build heap from list")
    void testBuildHeapFromList() {
        List<Integer> data = Arrays.asList(50, 30, 70, 20, 40, 60, 80, 90);

        maxHeap.buildHeap(data);

        assertEquals(data.size(), maxHeap.getSize());
        assertEquals(Integer.valueOf(90), maxHeap.peek());
        assertTrue(maxHeap.isValidHeap());
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 3, 4, 5})
    @DisplayName("Test different degrees")
    void testDifferentDegrees(int degree) {
        DaryMaxHeap<Integer> heap = new DaryMaxHeap<>(degree);

        assertEquals(degree, heap.getDegree());
        assertTrue(heap.isEmpty());

        // Insert elements and verify heap property
        for (int i = 1; i <= 100; i++) {
            heap.insert(i);
        }

        assertTrue(heap.isValidHeap());
        assertEquals(Integer.valueOf(100), heap.peek());
    }

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

    @Test
    @DisplayName("Test heap type")
    void testGetHeapType() {
        assertTrue(maxHeap.getHeapType().contains("Max"));
    }

    @Test
    @DisplayName("Test height calculation")
    void testHeightCalculation() {
        assertEquals(0, maxHeap.getHeight()); // Empty heap

        maxHeap.insert(10);
        assertEquals(1, maxHeap.getHeight()); // Single element

        // For binary heap (degree 2):
        // Height should be ceil(log_2(n+1))
        for (int i = 11; i <= 17; i++) {
            maxHeap.insert(i);
        }

        int expectedHeight = (int) Math.ceil(Math.log(8+1) / Math.log(2));
        assertEquals(expectedHeight, maxHeap.getHeight());
    }

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

    @Test
    @DisplayName("Test reverse heap sort using extract operations")
    void testReverseHeapSort() {
        int[] unsorted = {64, 34, 25, 12, 22, 11, 90};

        for (int value : unsorted) {
            maxHeap.insert(value);
        }

        // Extract all elements - should come out in reverse sorted order
        int[] reverseSorted = new int[unsorted.length];
        for (int i = 0; i < reverseSorted.length; i++) {
            reverseSorted[i] = maxHeap.extractRoot();
        }

        // Verify reverse sorted order (descending)
        for (int i = 1; i < reverseSorted.length; i++) {
            assertTrue(reverseSorted[i - 1] >= reverseSorted[i], "Array should be sorted in descending order");
        }

        assertTrue(maxHeap.isEmpty());
    }

    @Test
    @DisplayName("Test comparison with min heap behavior")
    void testComparisonWithMinHeap() {
        DaryMinHeap<Integer> minHeap = new DaryMinHeap<>(2);

        int[] elements = {50, 30, 70, 20, 40, 60, 80};

        // Insert same elements in both heaps
        for (int element : elements) {
            maxHeap.insert(element);
            minHeap.insert(element);
        }

        // Max heap should have maximum at root
        assertEquals(Integer.valueOf(80), maxHeap.peek());
        // Min heap should have minimum at root
        assertEquals(Integer.valueOf(20), minHeap.peek());

        // Both should have same size
        assertEquals(maxHeap.getSize(), minHeap.getSize());

        // Both should be valid heaps
        assertTrue(maxHeap.isValidHeap());
        assertTrue(minHeap.isValidHeap());
    }

    @Test
    @DisplayName("Test priority queue behavior")
    void testPriorityQueueBehavior() {
        // Insert elements with different priorities
        maxHeap.insert(10); // Low priority
        maxHeap.insert(50); // Medium priority
        maxHeap.insert(30); // Medium-low priority
        maxHeap.insert(70); // High priority
        maxHeap.insert(60); // Medium-high priority

        // Should extract in priority order (highest first)
        assertEquals(Integer.valueOf(70), maxHeap.extractRoot());
        assertEquals(Integer.valueOf(60), maxHeap.extractRoot());
        assertEquals(Integer.valueOf(50), maxHeap.extractRoot());
        assertEquals(Integer.valueOf(30), maxHeap.extractRoot());
        assertEquals(Integer.valueOf(10), maxHeap.extractRoot());

        assertTrue(maxHeap.isEmpty());
    }
}
