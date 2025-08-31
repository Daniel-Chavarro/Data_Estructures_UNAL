package org.tree_tester.model.concrete.Tree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Node Tests")
class NodeTest {

    private Node<Integer> node;
    private Node<Integer> parentNode;
    private Node<Integer> leftChild;
    private Node<Integer> rightChild;

    @BeforeEach
    void setUp() {
        parentNode = new Node<>(50, null);
        node = new Node<>(30, parentNode);
        leftChild = new Node<>(20, node);
        rightChild = new Node<>(40, node);
    }

    @Test
    @DisplayName("Test node creation with parent")
    void testNodeCreationWithParent() {
        Node<String> stringNode = new Node<>("test", null);

        assertEquals("test", stringNode.getData());
        assertNull(stringNode.getParent());
        assertNull(stringNode.getLeft());
        assertNull(stringNode.getRight());
        assertEquals(0, stringNode.getHeight());
    }

    @Test
    @DisplayName("Test node creation without parent")
    void testNodeCreationWithoutParent() {
        assertEquals(Integer.valueOf(30), node.getData());
        assertEquals(parentNode, node.getParent());
        assertNull(node.getLeft());
        assertNull(node.getRight());
        assertEquals(0, node.getHeight());
    }

    @Test
    @DisplayName("Test setting and getting data")
    void testSetAndGetData() {
        node.setData(100);
        assertEquals(Integer.valueOf(100), node.getData());
    }

    @Test
    @DisplayName("Test setting and getting parent")
    void testSetAndGetParent() {
        Node<Integer> newParent = new Node<>(60, null);
        node.setParent(newParent);
        assertEquals(newParent, node.getParent());

        node.setParent(null);
        assertNull(node.getParent());
    }

    @Test
    @DisplayName("Test setting and getting left child")
    void testSetAndGetLeftChild() {
        node.setLeft(leftChild);
        assertEquals(leftChild, node.getLeft());

        node.setLeft(null);
        assertNull(node.getLeft());
    }

    @Test
    @DisplayName("Test setting and getting right child")
    void testSetAndGetRightChild() {
        node.setRight(rightChild);
        assertEquals(rightChild, node.getRight());

        node.setRight(null);
        assertNull(node.getRight());
    }

    @Test
    @DisplayName("Test setting and getting height")
    void testSetAndGetHeight() {
        assertEquals(0, node.getHeight()); // Default height

        node.setHeight(3);
        assertEquals(3, node.getHeight());

        node.setHeight(0);
        assertEquals(0, node.getHeight());
    }

    @Test
    @DisplayName("Test node relationships")
    void testNodeRelationships() {
        // Set up a small tree structure
        node.setLeft(leftChild);
        node.setRight(rightChild);

        // Test parent-child relationships
        assertEquals(node, leftChild.getParent());
        assertEquals(node, rightChild.getParent());
        assertEquals(leftChild, node.getLeft());
        assertEquals(rightChild, node.getRight());

        // Test grandparent relationship
        assertEquals(parentNode, node.getParent());
    }

    @Test
    @DisplayName("Test node is leaf")
    void testNodeIsLeaf() {
        // Node with no children should be a leaf
        assertTrue(isLeaf(node));

        // Node with left child should not be a leaf
        node.setLeft(leftChild);
        assertFalse(isLeaf(node));

        // Node with right child should not be a leaf
        node.setLeft(null);
        node.setRight(rightChild);
        assertFalse(isLeaf(node));

        // Node with both children should not be a leaf
        node.setLeft(leftChild);
        assertFalse(isLeaf(node));
    }

    @Test
    @DisplayName("Test node has one child")
    void testNodeHasOneChild() {
        // Node with no children
        assertFalse(hasOneChild(node));

        // Node with only left child
        node.setLeft(leftChild);
        assertTrue(hasOneChild(node));

        // Node with only right child
        node.setLeft(null);
        node.setRight(rightChild);
        assertTrue(hasOneChild(node));

        // Node with both children
        node.setLeft(leftChild);
        assertFalse(hasOneChild(node));
    }

    @Test
    @DisplayName("Test node has two children")
    void testNodeHasTwoChildren() {
        // Node with no children
        assertFalse(hasTwoChildren(node));

        // Node with only left child
        node.setLeft(leftChild);
        assertFalse(hasTwoChildren(node));

        // Node with only right child
        node.setLeft(null);
        node.setRight(rightChild);
        assertFalse(hasTwoChildren(node));

        // Node with both children
        node.setLeft(leftChild);
        assertTrue(hasTwoChildren(node));
    }

    @Test
    @DisplayName("Test node with different data types")
    void testNodeWithDifferentDataTypes() {
        // Test with String
        Node<String> stringNode = new Node<>("hello", null);
        assertEquals("hello", stringNode.getData());

        // Test with Double
        Node<Double> doubleNode = new Node<>(3.14, null);
        assertEquals(Double.valueOf(3.14), doubleNode.getData());

        // Test with Character
        Node<Character> charNode = new Node<>('A', null);
        assertEquals(Character.valueOf('A'), charNode.getData());
    }

    @Test
    @DisplayName("Test node height management")
    void testNodeHeightManagement() {
        // Single node should have height 1
        assertEquals(0, node.getHeight());

        // Node with children should have height calculated properly
        node.setLeft(leftChild);
        node.setRight(rightChild);

        // Manually set heights to simulate tree operations
        leftChild.setHeight(1);
        rightChild.setHeight(2);

        // Parent height should be max(child heights) + 1
        int expectedHeight = Math.max(leftChild.getHeight(), rightChild.getHeight()) + 1;
        node.setHeight(expectedHeight);
        assertEquals(3, node.getHeight());
    }

    @Test
    @DisplayName("Test node tree structure")
    void testNodeTreeStructure() {
        /*
         * Create a small tree:
         *       50 (parent)
         *      /
         *     30 (node)
         *    /  \
         *   20   40
         */
        node.setLeft(leftChild);
        node.setRight(rightChild);

        // Verify the complete structure
        assertEquals(Integer.valueOf(50), parentNode.getData());
        assertEquals(Integer.valueOf(30), node.getData());
        assertEquals(Integer.valueOf(20), leftChild.getData());
        assertEquals(Integer.valueOf(40), rightChild.getData());

        // Verify relationships
        assertEquals(parentNode, node.getParent());
        assertEquals(node, leftChild.getParent());
        assertEquals(node, rightChild.getParent());
        assertEquals(leftChild, node.getLeft());
        assertEquals(rightChild, node.getRight());
    }

    // Helper methods for testing
    private boolean isLeaf(Node<Integer> node) {
        return node.getLeft() == null && node.getRight() == null;
    }

    private boolean hasOneChild(Node<Integer> node) {
        return (node.getLeft() == null && node.getRight() != null) ||
               (node.getLeft() != null && node.getRight() == null);
    }

    private boolean hasTwoChildren(Node<Integer> node) {
        return node.getLeft() != null && node.getRight() != null;
    }
}
