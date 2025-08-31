package org.tree_tester.model.Abstract;

import org.tree_tester.model.concrete.Tree.Node;

import java.util.ArrayList;
import java.util.List;

public abstract class Tree <T extends Comparable<T>> {
    /**
     * The root node of the tree.
     */
    protected Node<T> root;
    /**
     * The size of the tree, representing the number of elements in it.
     */
    protected int size;

    /**
     * Constructor to create an empty tree.
     */
    public Tree() {
        this.root = null;
        this.size = 0;
    }

    /**
     * Inserts a new element into the tree.
     * This method should be implemented by subclasses to define the specific insertion logic.
     *
     * @param data the element to be inserted
     * @return True if the insertion was successful, false if the element already exists in the tree
     */
    public abstract boolean insert(T data);

    /**
     * Searches for an element in the tree.
     *
     * @param data the element to be searched
     * @return True if the element is found, false otherwise
     */
    public boolean search(T data) {
        Node<T> current = root;
        while (current != null) {
            int cmp = data.compareTo(current.getData());
            if (cmp < 0) {
                current = current.getLeft();
            } else if (cmp > 0) {
                current = current.getRight();
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * Deletes an element from the tree.
     * This method should be implemented by subclasses to define the specific deletion logic.
     *
     * @param data the element to be deleted
     * @return True if the deletion was successful, false if the element was not found
     */
    public abstract boolean delete(T data);

    /**
     * Gets the root node of the tree.
     *
     * @return the root node
     */
    public int getSize(){
        return size;
    };

    /**
     * Gets the height of the tree.
     * The height is defined as the number of edges on the longest path from the root to a leaf node.
     *
     * @return the height of the tree
     */
    public int getHeight(){
        return getHeight(root);
    }

    /**
     * Returns the root node of the AVL tree.
     *
     * @return the root node
     */
    protected int getHeight(Node<T> node) {
        return node == null ? 0 : node.getHeight();
    }

    /**
     * Checks if the tree is empty.
     * @return true if the tree has no elements, false otherwise.
     */
    public boolean isEmpty(){
        return root == null;
    }

    /**
     * Counts the number of leaf nodes in the AVL tree.
     *
     * @return the number of leaf nodes
     */
    public int countLeaves() {
        return countLeaves(root);
    }

    /**
     * Recursively counts the number of leaf nodes in the tree.
     *
     * @param node the current node in the tree
     * @return the number of leaf nodes in the subtree rooted at the given node
     */
    private int countLeaves(Node<T> node) {
        if (node == null) {
            return 0;
        }
        if (node.getLeft() == null && node.getRight() == null) {
            return 1;
        }
        return countLeaves(node.getLeft()) + countLeaves(node.getRight());
    }

    /**
     * Finds the minimum element in the tree.
     *
     * @return the minimum element, or null if the tree is empty
     */
    public T findMin() {
        if (root == null) {
            return null;
        }
        Node<T> minNode = findMin(root);
        return minNode != null ? minNode.getData() : null;
    }

    /**
     * Recursively finds the minimum element in the subtree rooted at the given node.
     *
     * @param node the current node in the tree
     * @return the node containing the minimum element
     */
    protected Node<T> findMin(Node<T> node) {
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node;
    }

    /**
     * Finds the maximum element in the tree.
     *
     * @return the maximum element, or null if the tree is empty
     */
    public T findMax() {
        if (root == null) {
            return null;
        }
        Node<T> maxNode = findMax(root);
        return maxNode != null ? maxNode.getData() : null;
    }

    /**
     * Recursively finds the maximum element in the subtree rooted at the given node.
     *
     * @param node the current node in the tree
     * @return the node containing the maximum element
     */
    private Node<T> findMax(Node<T> node) {
        while (node.getRight() != null) {
            node = node.getRight();
        }
        return node;
    }

    /**
     * Checks if the tree is balanced.
     *
     * @return True if the tree is balanced, false otherwise
     */
    public boolean isBalanced() {
        return isBalanced(root);
    }

    /**
     * Checks if the tree is balanced.
     * A tree is balanced if the height of the left and right subtrees of any node differ by at most 1.
     *
     * @return True if the tree is balanced, false otherwise
     */
    private boolean isBalanced(Node<T> node) {
        if (node == null) {
            return true;
        }
        int balance = getBalance(node);
        return Math.abs(balance) <= 1 && isBalanced(node.getLeft()) && isBalanced(node.getRight());
    }

    /**
     * Returns the balance factor of a node.
     * The balance factor is defined as the height of the left subtree minus the height of the right subtree.
     *
     * @param node the node for which to calculate the balance factor
     * @return the balance factor of the node
     */
    protected int getBalance(Node<T> node) {
        if (node == null) {
            return 0;
        }
        return getHeight(node.getLeft()) - getHeight(node.getRight());
    }

    /**
     * Returns the in-order traversal of the tree.
     *
     * @return a list containing the elements in in-order traversal
     */
    public List<T> inOrder() {
        ArrayList<T> inOrder = new ArrayList<>();
        inOrderTraversal(root, inOrder);
        return inOrder;
    }

    /**
     * Recursively performs in-order traversal of the tree.
     *
     * @param node the current node in the tree
     * @param list the list to store the elements in in-order
     */
    private void inOrderTraversal(Node<T> node, List<T> list) {
        if (node != null) {
            inOrderTraversal(node.getLeft(), list);
            list.add(node.getData());
            inOrderTraversal(node.getRight(), list);
        }
    }
    /**
     * Returns the pre-order traversal of the tree.
     *
     * @return a list containing the elements in pre-order traversal
     */
    public List<T> preOrder() {
        ArrayList<T> preOrder = new ArrayList<>();
        preOrderTraversal(root, preOrder);
        return preOrder;
    }
    /**
     * Recursively performs pre-order traversal of the tree.
     *
     * @param node the current node in the tree
     * @param list the list to store the elements in pre-order
     */
    private void preOrderTraversal(Node<T> node, List<T> list) {
        if (node != null) {
            list.add(node.getData());
            preOrderTraversal(node.getLeft(), list);
            preOrderTraversal(node.getRight(), list);
        }
    }

    /**
     * Returns the post-order traversal of the tree.
     *
     * @return a list containing the elements in post-order traversal
     */
    public List<T> postOrder() {
        ArrayList<T> postOrder = new ArrayList<>();
        postOrderTraversal(root, postOrder);
        return postOrder;
    }

    /**
     * Recursively performs post-order traversal of the tree.
     *
     * @param node the current node in the tree
     * @param list the list to store the elements in post-order
     */
    private void postOrderTraversal(Node<T> node, List<T> list) {
        if (node != null) {
            postOrderTraversal(node.getLeft(), list);
            postOrderTraversal(node.getRight(), list);
            list.add(node.getData());
        }
    }

    /**
     * Returns the level-order traversal of the tree.
     *
     * @return a list containing the elements in level-order traversal
     */
    public List<T> levelOrder() {
        ArrayList<T> levelOrder = new ArrayList<>();
        if (root == null) {
            return levelOrder;
        }
        List<Node<T>> queue = new ArrayList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node<T> current = queue.remove(0);
            levelOrder.add(current.getData());
            if (current.getLeft() != null) {
                queue.add(current.getLeft());
            }
            if (current.getRight() != null) {
                queue.add(current.getRight());
            }
        }
        return levelOrder;
    }

    /**
     * Clears the tree, removing all elements.
     * This method resets the root to null and size to 0.
     */
    public void clear(){
        root = null;
        size = 0;
    }

    /**
     * Adjusts the height of the given node based on the heights of its children.
     *
     * @param node the node whose height needs to be adjusted
     */
    protected void adjustHeight(Node<T> node) {
        if (node != null) {
            node.setHeight(1 + Math.max(getHeight(node.getLeft()), getHeight(node.getRight())));
        }
    }

    /**
     * Returns the type of the tree.
     * This method should be implemented by subclasses to return the specific type of tree.
     *
     * @return a string representing the type of the tree
     */
    public abstract String getTreeType();
}
