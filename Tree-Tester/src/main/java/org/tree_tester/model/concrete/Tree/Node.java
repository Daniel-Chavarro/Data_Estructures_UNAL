package org.tree_tester.model.concrete.Tree;

/**
 * Represents a node in a binary tree structure.
 * Each node contains data and references to left child, right child, and parent nodes.
 * Also maintains height information for tree balancing operations.
 *
 * @param <T> the type of data stored in the node, must implement Comparable
 */
public class Node<T extends Comparable<T>> {
    protected T data;
    protected Node<T> left;
    protected Node<T> right;
    protected Node<T> parent;
    protected int height;

    /**
     * Constructs a new node with the specified data and parent.
     * Left and right children are initialized to null, height to 0.
     *
     * @param data the data to store in this node
     * @param parent the parent node of this node
     */
    public Node(T data, Node<T> parent) {
        this.data = data;
        this.left = null;
        this.right = null;
        this.parent = parent;
        this.height = 0;
    }

    /**
     * Gets the data stored in this node.
     *
     * @return the data stored in this node
     */
    public T getData() {
        return data;
    }

    /**
     * Sets the data stored in this node.
     *
     * @param data the data to store in this node
     */
    public void setData(T data) {
        this.data = data;
    }

    /**
     * Gets the left child of this node.
     *
     * @return the left child node, or null if none exists
     */
    public Node<T> getLeft() {
        return left;
    }

    /**
     * Sets the left child of this node.
     *
     * @param left the node to set as left child
     */
    public void setLeft(Node<T> left) {
        this.left = left;
    }

    /**
     * Gets the right child of this node.
     *
     * @return the right child node, or null if none exists
     */
    public Node<T> getRight() {
        return right;
    }

    /**
     * Sets the right child of this node.
     *
     * @param right the node to set as right child
     */
    public void setRight(Node<T> right) {
        this.right = right;
    }

    /**
     * Gets the parent of this node.
     *
     * @return the parent node, or null if this is the root
     */
    public Node<T> getParent() {
        return parent;
    }

    /**
     * Sets the parent of this node.
     *
     * @param parent the node to set as parent
     */
    public void setParent(Node<T> parent) {
        this.parent = parent;
    }

    /**
     * Gets the height of this node in the tree.
     * Height is defined as the number of edges from this node to the deepest leaf.
     *
     * @return the height of this node
     */
    public int getHeight() {
        return height;
    }

    /**
     * Sets the height of this node.
     *
     * @param height the height value to set
     */
    public void setHeight(int height) {
        this.height = height;
    }
}
