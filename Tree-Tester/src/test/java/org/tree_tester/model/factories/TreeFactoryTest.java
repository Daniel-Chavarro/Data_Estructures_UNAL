package org.tree_tester.model.factories;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.tree_tester.model.Abstract.Tree;
import org.tree_tester.model.Abstract.Heap;
import org.tree_tester.model.concrete.Tree.AVLTree;
import org.tree_tester.model.concrete.Tree.BinarySearchTree;
import org.tree_tester.model.concrete.TreeType;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tree Factory Tests")
class TreeFactoryTest {

    @Test
    @DisplayName("Test BST creation")
    void testBSTCreation() {
        Tree<Integer> tree = TreeFactory.createTree(TreeType.BST);

        assertNotNull(tree);
        assertInstanceOf(BinarySearchTree.class, tree);
        assertTrue(tree.isEmpty());
        assertEquals(0, tree.getSize());
        assertEquals("BST", tree.getTreeType());
    }

    @Test
    @DisplayName("Test AVL creation")
    void testAVLCreation() {
        Tree<Integer> tree = TreeFactory.createTree(TreeType.AVL);

        assertNotNull(tree);
        assertInstanceOf(AVLTree.class, tree);
        assertTrue(tree.isEmpty());
        assertEquals(0, tree.getSize());
        assertEquals("AVL", tree.getTreeType());
    }

    @Test
    @DisplayName("Test BST functionality after creation")
    void testBSTFunctionalityAfterCreation() {
        Tree<Integer> bst = TreeFactory.createTree(TreeType.BST);

        // Test basic operations
        assertTrue(bst.insert(50));
        assertTrue(bst.insert(30));
        assertTrue(bst.insert(70));

        assertEquals(3, bst.getSize());
        assertTrue(bst.search(50));
        assertTrue(bst.search(30));
        assertTrue(bst.search(70));

        assertEquals(Integer.valueOf(30), bst.findMin());
        assertEquals(Integer.valueOf(70), bst.findMax());
    }

    @Test
    @DisplayName("Test AVL functionality after creation")
    void testAVLFunctionalityAfterCreation() {
        Tree<Integer> avl = TreeFactory.createTree(TreeType.AVL);

        // Test basic operations
        assertTrue(avl.insert(50));
        assertTrue(avl.insert(30));
        assertTrue(avl.insert(70));

        assertEquals(3, avl.getSize());
        assertTrue(avl.search(50));
        assertTrue(avl.search(30));
        assertTrue(avl.search(70));
        assertTrue(avl.isBalanced());

        assertEquals(Integer.valueOf(30), avl.findMin());
        assertEquals(Integer.valueOf(70), avl.findMax());
    }

    @Test
    @DisplayName("Test Min Heap creation")
    void testMinHeapCreation() {
        assertThrows(IllegalArgumentException.class, () -> {
            TreeFactory.createHeap(TreeType.MIN_HEAP, 1); // Invalid degree
        });

        // Note: This will fail until DaryMinHeap is properly implemented
        assertDoesNotThrow(() -> {
            Heap<Integer> heap = TreeFactory.createHeap(TreeType.MIN_HEAP, 2);
            assertNotNull(heap);
        });
    }

    @Test
    @DisplayName("Test Max Heap creation")
    void testMaxHeapCreation() {
        assertThrows(IllegalArgumentException.class, () -> {
            TreeFactory.createHeap(TreeType.MAX_HEAP, 1); // Invalid degree
        });

        // Note: This will fail until DaryMaxHeap is properly implemented
        assertDoesNotThrow(() -> {
            Heap<Integer> heap = TreeFactory.createHeap(TreeType.MAX_HEAP, 3);
            assertNotNull(heap);
        });
    }

    @Test
    @DisplayName("Test unsupported tree type")
    void testUnsupportedTreeType() {
        // This test would require adding an unsupported enum value
        // For now, we test with null (though this might not be the best practice)
        assertThrows(NullPointerException.class, () -> {
            TreeFactory.createTree(null);
        });
    }

    @Test
    @DisplayName("Test different tree types with same data")
    void testDifferentTreeTypesWithSameData() {
        Tree<Integer> bst = TreeFactory.createTree(TreeType.BST);
        Tree<Integer> avl = TreeFactory.createTree(TreeType.AVL);

        int[] data = {50, 30, 70, 20, 40, 60, 80};

        // Insert same data in both trees
        for (int value : data) {
            bst.insert(value);
            avl.insert(value);
        }

        assertEquals(data.length, bst.getSize());
        assertEquals(data.length, avl.getSize());

        // Both should contain all elements
        for (int value : data) {
            assertTrue(bst.search(value));
            assertTrue(avl.search(value));
        }

        // Both should have same min/max
        assertEquals(bst.findMin(), avl.findMin());
        assertEquals(bst.findMax(), avl.findMax());

        // Both should have same in-order traversal (sorted)
        assertEquals(bst.inOrder(), avl.inOrder());

        // AVL should be balanced, BST might not be
        assertTrue(avl.isBalanced());
    }

    @Test
    @DisplayName("Test generic type compatibility")
    void testGenericTypeCompatibility() {
        // Test with String
        Tree<String> stringBst = TreeFactory.createTree(TreeType.BST);
        Tree<String> stringAvl = TreeFactory.createTree(TreeType.AVL);

        stringBst.insert("banana");
        stringBst.insert("apple");
        stringBst.insert("cherry");

        stringAvl.insert("banana");
        stringAvl.insert("apple");
        stringAvl.insert("cherry");

        assertEquals("apple", stringBst.findMin());
        assertEquals("cherry", stringBst.findMax());
        assertEquals("apple", stringAvl.findMin());
        assertEquals("cherry", stringAvl.findMax());

        // Test with Double
        Tree<Double> doubleBst = TreeFactory.createTree(TreeType.BST);
        doubleBst.insert(3.14);
        doubleBst.insert(2.71);
        doubleBst.insert(1.41);

        assertEquals(Double.valueOf(1.41), doubleBst.findMin());
        assertEquals(Double.valueOf(3.14), doubleBst.findMax());
    }

    @Test
    @DisplayName("Test factory creates independent instances")
    void testFactoryCreatesIndependentInstances() {
        Tree<Integer> bst1 = TreeFactory.createTree(TreeType.BST);
        Tree<Integer> bst2 = TreeFactory.createTree(TreeType.BST);
        Tree<Integer> avl1 = TreeFactory.createTree(TreeType.AVL);
        Tree<Integer> avl2 = TreeFactory.createTree(TreeType.AVL);

        // Instances should be different objects
        assertNotSame(bst1, bst2);
        assertNotSame(avl1, avl2);
        assertNotSame(bst1, avl1);

        // Operations on one shouldn't affect the other
        bst1.insert(10);
        bst2.insert(20);

        assertTrue(bst1.search(10));
        assertFalse(bst1.search(20));
        assertTrue(bst2.search(20));
        assertFalse(bst2.search(10));

        assertEquals(1, bst1.getSize());
        assertEquals(1, bst2.getSize());
    }
}
