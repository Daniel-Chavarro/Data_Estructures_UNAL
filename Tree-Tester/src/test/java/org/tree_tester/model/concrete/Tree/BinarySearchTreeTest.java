package org.tree_tester.model.concrete.Tree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Binary Search Tree Tests")
class BinarySearchTreeTest {

    private BinarySearchTree<Integer> bst;

    @BeforeEach
    void setUp() {
        bst = new BinarySearchTree<>();
    }

    @Test
    @DisplayName("Test empty tree creation")
    void testEmptyTreeCreation() {
        assertTrue(bst.isEmpty());
        assertEquals(0, bst.getSize());
        assertEquals(0, bst.getHeight());
        assertNull(bst.findMin());
        assertNull(bst.findMax());
    }

    @Test
    @DisplayName("Test single element insertion")
    void testSingleElementInsertion() {
        assertTrue(bst.insert(10));
        assertFalse(bst.isEmpty());
        assertEquals(1, bst.getSize());
        assertTrue(bst.search(10));
        assertEquals(Integer.valueOf(10), bst.findMin());
        assertEquals(Integer.valueOf(10), bst.findMax());
    }

    @Test
    @DisplayName("Test multiple elements insertion")
    void testMultipleElementsInsertion() {
        int[] elements = {50, 30, 70, 20, 40, 60, 80};

        for (int element : elements) {
            assertTrue(bst.insert(element));
        }

        assertEquals(elements.length, bst.getSize());

        for (int element : elements) {
            assertTrue(bst.search(element));
        }
    }

    @Test
    @DisplayName("Test duplicate insertion")
    void testDuplicateInsertion() {
        assertTrue(bst.insert(10));
        assertFalse(bst.insert(10)); // Should return false for duplicate
        assertEquals(1, bst.getSize());
    }

    @Test
    @DisplayName("Test search functionality")
    void testSearch() {
        int[] elements = {50, 30, 70, 20, 40, 60, 80};

        for (int element : elements) {
            bst.insert(element);
        }

        // Test existing elements
        for (int element : elements) {
            assertTrue(bst.search(element));
        }

        // Test non-existing elements
        assertFalse(bst.search(100));
        assertFalse(bst.search(15));
        assertFalse(bst.search(65));
    }

    @Test
    @DisplayName("Test deletion of leaf node")
    void testDeletionLeafNode() {
        bst.insert(50);
        bst.insert(30);
        bst.insert(70);
        bst.insert(20);

        assertTrue(bst.delete(20));
        assertFalse(bst.search(20));
        assertEquals(3, bst.getSize());
    }

    @Test
    @DisplayName("Test deletion of node with one child")
    void testDeletionNodeWithOneChild() {
        bst.insert(50);
        bst.insert(30);
        bst.insert(20);

        assertTrue(bst.delete(30));
        assertFalse(bst.search(30));
        assertTrue(bst.search(50));
        assertTrue(bst.search(20));
        assertEquals(2, bst.getSize());
    }

    @Test
    @DisplayName("Test deletion of node with two children")
    void testDeletionNodeWithTwoChildren() {
        bst.insert(50);
        bst.insert(30);
        bst.insert(70);
        bst.insert(20);
        bst.insert(40);
        bst.insert(60);
        bst.insert(80);

        assertTrue(bst.delete(50)); // Root node with two children
        assertFalse(bst.search(50));
        assertEquals(6, bst.getSize());


        assertTrue(bst.search(30));
        assertTrue(bst.search(70));
        assertTrue(bst.search(20));
        assertTrue(bst.search(40));
        assertTrue(bst.search(60));
        assertTrue(bst.search(80));
    }

    @Test
    @DisplayName("Test deletion of non-existing element")
    void testDeletionNonExisting() {
        bst.insert(50);
        bst.insert(30);
        bst.insert(70);

        assertFalse(bst.delete(100));
        assertEquals(3, bst.getSize());
    }

    @Test
    @DisplayName("Test find minimum")
    void testFindMin() {
        assertNull(bst.findMin()); // Empty tree

        bst.insert(50);
        assertEquals(Integer.valueOf(50), bst.findMin());

        bst.insert(30);
        bst.insert(70);
        bst.insert(20);
        bst.insert(40);

        assertEquals(Integer.valueOf(20), bst.findMin());
    }

    @Test
    @DisplayName("Test find maximum")
    void testFindMax() {
        assertNull(bst.findMax()); // Empty tree

        bst.insert(50);
        assertEquals(Integer.valueOf(50), bst.findMax());

        bst.insert(30);
        bst.insert(70);
        bst.insert(60);
        bst.insert(80);

        assertEquals(Integer.valueOf(80), bst.findMax());
    }

    @Test
    @DisplayName("Test in-order traversal")
    void testInOrderTraversal() {
        int[] elements = {50, 30, 70, 20, 40, 60, 80};

        for (int element : elements) {
            bst.insert(element);
        }

        List<Integer> inOrder = bst.inOrder();
        List<Integer> expected = Arrays.asList(20, 30, 40, 50, 60, 70, 80);

        assertEquals(expected, inOrder);
    }

    @Test
    @DisplayName("Test pre-order traversal")
    void testPreOrderTraversal() {
        bst.insert(50);
        bst.insert(30);
        bst.insert(70);
        bst.insert(20);
        bst.insert(40);

        List<Integer> preOrder = bst.preOrder();
        List<Integer> expected = Arrays.asList(50, 30, 20, 40, 70);

        assertEquals(expected, preOrder);
    }

    @Test
    @DisplayName("Test post-order traversal")
    void testPostOrderTraversal() {
        bst.insert(50);
        bst.insert(30);
        bst.insert(70);
        bst.insert(20);
        bst.insert(40);

        List<Integer> postOrder = bst.postOrder();
        List<Integer> expected = Arrays.asList(20, 40, 30, 70, 50);

        assertEquals(expected, postOrder);
    }

    @Test
    @DisplayName("Test level-order traversal")
    void testLevelOrderTraversal() {
        bst.insert(50);
        bst.insert(30);
        bst.insert(70);
        bst.insert(20);
        bst.insert(40);

        List<Integer> levelOrder = bst.levelOrder();
        List<Integer> expected = Arrays.asList(50, 30, 70, 20, 40);

        assertEquals(expected, levelOrder);
    }

    @Test
    @DisplayName("Test count leaves")
    void testCountLeaves() {
        assertEquals(0, bst.countLeaves()); // Empty tree

        bst.insert(50);
        assertEquals(1, bst.countLeaves()); // Single node

        bst.insert(30);
        bst.insert(70);
        assertEquals(2, bst.countLeaves()); // Two leaves

        bst.insert(20);
        bst.insert(40);
        bst.insert(60);
        bst.insert(80);
        assertEquals(4, bst.countLeaves()); // Four leaves
    }

    @Test
    @DisplayName("Test tree balance")
    void testIsBalanced() {
        assertTrue(bst.isBalanced()); // Empty tree is balanced

        bst.insert(50);
        assertTrue(bst.isBalanced()); // Single node is balanced

        bst.insert(30);
        bst.insert(70);
        assertTrue(bst.isBalanced()); // Balanced tree

        // Create unbalanced tree
        BinarySearchTree<Integer> unbalanced = new BinarySearchTree<>();
        unbalanced.insert(1);
        unbalanced.insert(2);
        unbalanced.insert(3);
        unbalanced.insert(4);
        unbalanced.insert(5);

        assertFalse(unbalanced.isBalanced());
    }

    @Test
    @DisplayName("Test clear functionality")
    void testClear() {
        bst.insert(50);
        bst.insert(30);
        bst.insert(70);

        assertFalse(bst.isEmpty());
        assertEquals(3, bst.getSize());

        bst.clear();

        assertTrue(bst.isEmpty());
        assertEquals(0, bst.getSize());
        assertNull(bst.findMin());
        assertNull(bst.findMax());
    }

    @Test
    @DisplayName("Test tree type")
    void testGetTreeType() {
        assertEquals("BST", bst.getTreeType());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 5, 10, 100, 1000})
    @DisplayName("Test with different tree sizes")
    void testDifferentTreeSizes(int size) {
        for (int i = 1; i <= size; i++) {
            assertTrue(bst.insert(i));
        }

        assertEquals(size, bst.getSize());
        assertEquals(Integer.valueOf(1), bst.findMin());
        assertEquals(Integer.valueOf(size), bst.findMax());

        for (int i = 1; i <= size; i++) {
            assertTrue(bst.search(i));
        }
    }

    @Test
    @DisplayName("Test with string elements")
    void testWithStringElements() {
        BinarySearchTree<String> stringBst = new BinarySearchTree<>();

        String[] words = {"dog", "cat", "elephant", "ant", "bear"};

        for (String word : words) {
            assertTrue(stringBst.insert(word));
        }

        assertEquals(words.length, stringBst.getSize());
        assertEquals("ant", stringBst.findMin());
        assertEquals("elephant", stringBst.findMax());

        List<String> inOrder = stringBst.inOrder();
        List<String> expected = Arrays.asList("ant", "bear", "cat", "dog", "elephant");
        assertEquals(expected, inOrder);
    }
}
