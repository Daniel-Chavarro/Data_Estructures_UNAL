package org.tree_tester.model.concrete.Heap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("D-ary Min Heap Tests")
class DaryMinHeapTest {

    private DaryMinHeap<Integer> minHeap;

    @BeforeEach
    void setUp() {
        minHeap = new DaryMinHeap<>(2); // Binary heap by default
    }

    @Test
    @DisplayName("Test empty heap creation")
    void testEmptyHeapCreation() {
        assertTrue(minHeap.isEmpty());
        assertEquals(0, minHeap.getSize());
        assertEquals(0, minHeap.getHeight());
        assertEquals(2, minHeap.getDegree());
    }

    @Test
    @DisplayName("Test single element insertion")
    void testSingleElementInsertion() {
        minHeap.insert(10);

        assertFalse(minHeap.isEmpty());
        assertEquals(1, minHeap.getSize());
        assertEquals(Integer.valueOf(10), minHeap.peek());
        assertTrue(minHeap.isValidHeap());
    }

    @Test
    @DisplayName("Test multiple elements insertion maintains min heap property")
    void testMultipleElementsInsertion() {
        int[] elements = {50, 30, 70, 20, 40, 60, 80, 10};

        for (int element : elements) {
            minHeap.insert(element);
            assertTrue(minHeap.isValidHeap(), "Heap property should be maintained after inserting " + element);
        }

        assertEquals(elements.length, minHeap.getSize());
        assertEquals(Integer.valueOf(10), minHeap.peek()); // Minimum should be at root
    }

    @Test
    @DisplayName("Test extract root maintains heap property")
    void testExtractRoot() {
        int[] elements = {50, 30, 70, 20, 40, 60, 80, 10};

        for (int element : elements) {
            minHeap.insert(element);
        }

        // Extract elements should come out in ascending order
        Integer extracted = minHeap.extractRoot();
        assertEquals(Integer.valueOf(10), extracted);
        assertTrue(minHeap.isValidHeap());

        extracted = minHeap.extractRoot();
        assertEquals(Integer.valueOf(20), extracted);
        assertTrue(minHeap.isValidHeap());

        assertEquals(elements.length - 2, minHeap.getSize());
    }

    @Test
    @DisplayName("Test peek without modifying heap")
    void testPeek() {
        assertNull(minHeap.peek()); // Empty heap

        minHeap.insert(50);
        minHeap.insert(30);
        minHeap.insert(70);

        assertEquals(Integer.valueOf(30), minHeap.peek());
        assertEquals(3, minHeap.getSize()); // Size should not change
        assertTrue(minHeap.isValidHeap());
    }

    @Test
    @DisplayName("Test build heap from list")
    void testBuildHeapFromList() {
        List<Integer> data = Arrays.asList(50, 30, 70, 20, 40, 60, 80, 10);

        minHeap.buildHeap(data);

        assertEquals(data.size(), minHeap.getSize());
        assertEquals(Integer.valueOf(10), minHeap.peek());
        assertTrue(minHeap.isValidHeap());
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 3, 4, 5})
    @DisplayName("Test different degrees")
    void testDifferentDegrees(int degree) {
        DaryMinHeap<Integer> heap = new DaryMinHeap<>(degree);

        assertEquals(degree, heap.getDegree());
        assertTrue(heap.isEmpty());

        // Insert elements and verify heap property
        for (int i = 100; i >= 1; i--) {
            heap.insert(i);
        }

        assertTrue(heap.isValidHeap());
        assertEquals(Integer.valueOf(1), heap.peek());
    }

    @Test
    @DisplayName("Test invalid degree throws exception")
    void testInvalidDegreeThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new DaryMinHeap<>(1); // Degree must be at least 2
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new DaryMinHeap<>(0);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new DaryMinHeap<>(-1);
        });
    }

    @Test
    @DisplayName("Test clear functionality")
    void testClear() {
        minHeap.insert(50);
        minHeap.insert(30);
        minHeap.insert(70);

        assertFalse(minHeap.isEmpty());
        assertEquals(3, minHeap.getSize());

        minHeap.clear();

        assertTrue(minHeap.isEmpty());
        assertEquals(0, minHeap.getSize());
        assertNull(minHeap.peek());
    }

    @Test
    @DisplayName("Test heap type")
    void testGetHeapType() {
        assertTrue(minHeap.getHeapType().contains("Min"));
    }

    @Test
    @DisplayName("Test height calculation")
    void testHeightCalculation() {
        assertEquals(0, minHeap.getHeight()); // Empty heap

        minHeap.insert(10);
        assertEquals(1, minHeap.getHeight()); // Single element

        // For binary heap (degree 2):
        // Height should be ceil(log_2(n+1))
        for (int i = 2; i <= 7; i++) {
            minHeap.insert(i);
        }

        int expectedHeight = (int) Math.ceil(Math.log(8) / Math.log(2));
        assertEquals(expectedHeight, minHeap.getHeight());
    }

    @Test
    @DisplayName("Test with string elements")
    void testWithStringElements() {
        DaryMinHeap<String> stringHeap = new DaryMinHeap<>(2);

        String[] words = {"dog", "cat", "elephant", "ant", "bear"};

        for (String word : words) {
            stringHeap.insert(word);
        }

        assertEquals("ant", stringHeap.peek()); // Lexicographically smallest
        assertEquals(words.length, stringHeap.getSize());
        assertTrue(stringHeap.isValidHeap());
    }

    @Test
    @DisplayName("Test heap sort using extract operations")
    void testHeapSort() {
        int[] unsorted = {64, 34, 25, 12, 22, 11, 90};

        for (int value : unsorted) {
            minHeap.insert(value);
        }

        // Extract all elements - should come out sorted
        int[] sorted = new int[unsorted.length];
        for (int i = 0; i < sorted.length; i++) {
            sorted[i] = minHeap.extractRoot();
        }

        // Verify sorted order
        for (int i = 1; i < sorted.length; i++) {
            assertTrue(sorted[i - 1] <= sorted[i], "Array should be sorted in ascending order");
        }

        assertTrue(minHeap.isEmpty());
    }
}
