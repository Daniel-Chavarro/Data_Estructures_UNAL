package org.tree_tester.model.concrete.Tree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("AVL Tree Tests")
class AVLTreeTest {

    private AVLTree<Integer> avl;

    @BeforeEach
    void setUp() {
        avl = new AVLTree<>();
    }

    @Test
    @DisplayName("Test empty tree creation")
    void testEmptyTreeCreation() {
        assertTrue(avl.isEmpty());
        assertEquals(0, avl.getSize());
        assertEquals(0, avl.getHeight());
        assertNull(avl.findMin());
        assertNull(avl.findMax());
    }

    @Test
    @DisplayName("Test single element insertion")
    void testSingleElementInsertion() {
        assertTrue(avl.insert(10));
        assertFalse(avl.isEmpty());
        assertEquals(1, avl.getSize());
        assertTrue(avl.search(10));
        assertEquals(Integer.valueOf(10), avl.findMin());
        assertEquals(Integer.valueOf(10), avl.findMax());
    }

    @Test
    @DisplayName("Test multiple elements insertion maintains balance")
    void testMultipleElementsInsertionBalance() {
        int[] elements = {50, 30, 70, 20, 40, 60, 80};

        for (int element : elements) {
            assertTrue(avl.insert(element));
            assertTrue(avl.isBalanced(), "Tree should remain balanced after inserting " + element);
        }

        assertEquals(elements.length, avl.getSize());

        for (int element : elements) {
            assertTrue(avl.search(element));
        }
    }

    @Test
    @DisplayName("Test right rotation scenario")
    void testRightRotation() {
        // Insert elements that will cause a right rotation
        avl.insert(10);
        avl.insert(5);
        avl.insert(3); // This should trigger a right rotation

        assertTrue(avl.isBalanced());
        assertEquals(3, avl.getSize());
        assertTrue(avl.search(10));
        assertTrue(avl.search(5));
        assertTrue(avl.search(3));
    }

    @Test
    @DisplayName("Test left rotation scenario")
    void testLeftRotation() {
        // Insert elements that will cause a left rotation
        avl.insert(10);
        avl.insert(15);
        avl.insert(20); // This should trigger a left rotation

        assertTrue(avl.isBalanced());
        assertEquals(3, avl.getSize());
        assertTrue(avl.search(10));
        assertTrue(avl.search(15));
        assertTrue(avl.search(20));
    }

    @Test
    @DisplayName("Test left-right rotation scenario")
    void testLeftRightRotation() {
        // Insert elements that will cause a left-right rotation
        avl.insert(10);
        avl.insert(5);
        avl.insert(7); // This should trigger a left-right rotation

        assertTrue(avl.isBalanced());
        assertEquals(3, avl.getSize());
        assertTrue(avl.search(10));
        assertTrue(avl.search(5));
        assertTrue(avl.search(7));
    }

    @Test
    @DisplayName("Test right-left rotation scenario")
    void testRightLeftRotation() {
        // Insert elements that will cause a right-left rotation
        avl.insert(10);
        avl.insert(15);
        avl.insert(12); // This should trigger a right-left rotation

        assertTrue(avl.isBalanced());
        assertEquals(3, avl.getSize());
        assertTrue(avl.search(10));
        assertTrue(avl.search(15));
        assertTrue(avl.search(12));
    }

    @Test
    @DisplayName("Test sequential insertion maintains balance")
    void testSequentialInsertionBalance() {
        // Insert sequential numbers (worst case for BST)
        for (int i = 1; i <= 15; i++) {
            assertTrue(avl.insert(i));
            assertTrue(avl.isBalanced(), "Tree should remain balanced after inserting " + i);
        }

        assertEquals(15, avl.getSize());

        // Verify all elements are present
        for (int i = 1; i <= 15; i++) {
            assertTrue(avl.search(i));
        }

        // Height should be logarithmic, not linear
        assertTrue(avl.getHeight() <= 5, "Height should be logarithmic for balanced tree");
    }

    @Test
    @DisplayName("Test duplicate insertion")
    void testDuplicateInsertion() {
        assertTrue(avl.insert(10));
        assertFalse(avl.insert(10)); // Should return false for duplicate
        assertEquals(1, avl.getSize());
        assertTrue(avl.isBalanced());
    }

    @Test
    @DisplayName("Test deletion maintains balance")
    void testDeletionMaintainsBalance() {
        int[] elements = {50, 25, 75, 10, 30, 60, 80, 5, 15, 27, 35};

        // Insert elements
        for (int element : elements) {
            avl.insert(element);
        }

        // Delete some elements and verify balance is maintained
        int[] toDelete = {5, 15, 27, 35, 10};

        for (int element : toDelete) {
            assertTrue(avl.delete(element));
            assertTrue(avl.isBalanced(), "Tree should remain balanced after deleting " + element);
            assertFalse(avl.search(element));
        }

        assertEquals(elements.length - toDelete.length, avl.getSize());
    }

    @Test
    @DisplayName("Test deletion of root node")
    void testDeletionOfRoot() {
        avl.insert(50);
        avl.insert(30);
        avl.insert(70);
        avl.insert(20);
        avl.insert(40);
        avl.insert(60);
        avl.insert(80);

        assertTrue(avl.delete(50)); // Delete root
        assertFalse(avl.search(50));
        assertTrue(avl.isBalanced());
        assertEquals(6, avl.getSize());

        // Verify remaining elements
        int[] remaining = {30, 70, 20, 40, 60, 80};
        for (int element : remaining) {
            assertTrue(avl.search(element));
        }
    }

    @Test
    @DisplayName("Test deletion of non-existing element")
    void testDeletionNonExisting() {
        avl.insert(50);
        avl.insert(30);
        avl.insert(70);

        assertFalse(avl.delete(100));
        assertEquals(3, avl.getSize());
        assertTrue(avl.isBalanced());
    }

    @Test
    @DisplayName("Test find minimum and maximum")
    void testFindMinMax() {
        assertNull(avl.findMin()); // Empty tree
        assertNull(avl.findMax()); // Empty tree

        int[] elements = {50, 25, 75, 10, 30, 60, 80, 5, 15, 85};

        for (int element : elements) {
            avl.insert(element);
        }

        assertEquals(Integer.valueOf(5), avl.findMin());
        assertEquals(Integer.valueOf(85), avl.findMax());
    }

    @Test
    @DisplayName("Test tree traversals")
    void testTraversals() {
        int[] elements = {50, 30, 70, 20, 40, 60, 80};

        for (int element : elements) {
            avl.insert(element);
        }

        // In-order should be sorted
        List<Integer> inOrder = avl.inOrder();
        List<Integer> expectedInOrder = Arrays.asList(20, 30, 40, 50, 60, 70, 80);
        assertEquals(expectedInOrder, inOrder);

        // Pre-order and post-order should contain all elements
        List<Integer> preOrder = avl.preOrder();
        List<Integer> postOrder = avl.postOrder();
        List<Integer> levelOrder = avl.levelOrder();

        assertEquals(elements.length, preOrder.size());
        assertEquals(elements.length, postOrder.size());
        assertEquals(elements.length, levelOrder.size());

        // All traversals should contain the same elements
        for (int element : elements) {
            assertTrue(preOrder.contains(element));
            assertTrue(postOrder.contains(element));
            assertTrue(levelOrder.contains(element));
        }
    }

    @Test
    @DisplayName("Test count leaves")
    void testCountLeaves() {
        assertEquals(0, avl.countLeaves()); // Empty tree

        avl.insert(50);
        assertEquals(1, avl.countLeaves()); // Single node

        avl.insert(30);
        avl.insert(70);
        assertEquals(2, avl.countLeaves()); // Two leaves

        avl.insert(20);
        avl.insert(40);
        avl.insert(60);
        avl.insert(80);

        // Count leaves after insertions (may vary due to rotations)
        assertTrue(avl.countLeaves() > 0);
    }

    @Test
    @DisplayName("Test tree is always balanced")
    void testAlwaysBalanced() {
        assertTrue(avl.isBalanced()); // Empty tree

        // Insert random elements and verify balance is maintained
        int[] elements = {50, 17, 76, 9, 23, 54, 14, 19, 72, 12, 67};

        for (int element : elements) {
            avl.insert(element);
            assertTrue(avl.isBalanced(), "Tree should be balanced after inserting " + element);
        }

        // Delete elements and verify balance is maintained
        for (int element : elements) {
            if (element % 2 == 0) { // Delete even numbers
                avl.delete(element);
                assertTrue(avl.isBalanced(), "Tree should be balanced after deleting " + element);
            }
        }
    }

    @Test
    @DisplayName("Test clear functionality")
    void testClear() {
        avl.insert(50);
        avl.insert(30);
        avl.insert(70);

        assertFalse(avl.isEmpty());
        assertEquals(3, avl.getSize());

        avl.clear();

        assertTrue(avl.isEmpty());
        assertEquals(0, avl.getSize());
        assertTrue(avl.isBalanced());
        assertNull(avl.findMin());
        assertNull(avl.findMax());
    }

    @Test
    @DisplayName("Test tree type")
    void testGetTreeType() {
        assertEquals("AVL", avl.getTreeType());
    }

    @Test
    @DisplayName("Test height calculation")
    void testHeightCalculation() {
        assertEquals(0, avl.getHeight());

        avl.insert(50);
        assertEquals(0, avl.getHeight());

        avl.insert(30);
        avl.insert(70);
        assertEquals(1, avl.getHeight());

        // Insert more elements and verify height grows logarithmically
        for (int i = 1; i <= 15; i++) {
            if (i != 50 && i != 30 && i != 70) {
                avl.insert(i);
            }
        }

        // Height should be logarithmic for balanced tree
        assertTrue(avl.getHeight() <= 5, "Height should be logarithmic");
    }

    @ParameterizedTest
    @ValueSource(ints = {7, 15, 31, 63})
    @DisplayName("Test with perfect binary tree sizes")
    void testPerfectBinaryTreeSizes(int size) {
        for (int i = 1; i <= size; i++) {
            avl.insert(i);
            assertTrue(avl.isBalanced());
        }

        assertEquals(size, avl.getSize());

        // Height should be ceil(log2(size+1))
        int expectedMaxHeight = (int) Math.ceil(Math.log(size + 1) / Math.log(2));
        assertTrue(avl.getHeight() <= expectedMaxHeight + 1,
                   "Height should be logarithmic for size " + size);
    }

    @Test
    @DisplayName("Test with string elements")
    void testWithStringElements() {
        AVLTree<String> stringAvl = new AVLTree<>();

        String[] words = {"dog", "cat", "elephant", "ant", "bear", "fox", "giraffe"};

        for (String word : words) {
            assertTrue(stringAvl.insert(word));
            assertTrue(stringAvl.isBalanced());
        }

        assertEquals(words.length, stringAvl.getSize());
        assertEquals("ant", stringAvl.findMin());
        assertEquals("giraffe", stringAvl.findMax());

        List<String> inOrder = stringAvl.inOrder();
        List<String> expected = Arrays.asList("ant", "bear", "cat", "dog", "elephant", "fox", "giraffe");
        assertEquals(expected, inOrder);

        assertTrue(stringAvl.isBalanced());
    }

    @Test
    @DisplayName("Test stress test with large number of elements")
    void testStressTest() {
        int numElements = 1000;

        // Insert elements
        for (int i = 1; i <= numElements; i++) {
            assertTrue(avl.insert(i));
        }

        assertEquals(numElements, avl.getSize());
        assertTrue(avl.isBalanced());

        // Verify all elements are present
        for (int i = 1; i <= numElements; i++) {
            assertTrue(avl.search(i));
        }

        // Height should be logarithmic
        int maxExpectedHeight = (int) (1.44 * Math.log(numElements) / Math.log(2)) + 2;
        assertTrue(avl.getHeight() <= maxExpectedHeight,
                   "Height should be logarithmic even for large trees");

        // Delete half the elements
        for (int i = 1; i <= numElements; i += 2) {
            assertTrue(avl.delete(i));
            assertTrue(avl.isBalanced());
        }

        assertEquals(numElements / 2, avl.getSize());
    }
}
