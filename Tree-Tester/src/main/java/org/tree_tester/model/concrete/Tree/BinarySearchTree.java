package org.tree_tester.model.concrete.Tree;

import org.tree_tester.model.Abstract.Tree;
import org.tree_tester.model.concrete.TreeType;

/**
 * Binary Search Tree implementation where left children are smaller than parent nodes
 * and right children are greater than parent nodes. Does not perform any self-balancing.
 *
 * @param <T> the type of elements stored in the tree, must implement Comparable
 */
public class BinarySearchTree<T extends Comparable<T>> extends Tree<T> {

    /**
     * Constructs an empty binary search tree.
     */
    public BinarySearchTree() {
        super();
    }

    /**
     * Inserts a new node with the given data into the tree.
     * If the tree is empty, the new node becomes the root.
     * @param data The data to insert.
     * @return true if the insertion was successful, false if the data already exists in the tree.
     */
    @Override
    public boolean insert(T data) {
        boolean[] inserted = {false};
        root = insert(root, data, inserted);
        return inserted[0];
    }

    /**
     * Inserts a new node with the given data into the tree.
     * @param node The current node to compare against.
     * @param data The data to insert.
     * @param inserted A boolean array flag indicating whether the insertion was successful.
     * @return The updated node after insertion.
     */
    private Node<T> insert(Node<T> node, T data, boolean[] inserted) {
        if (node == null) {
            inserted[0] = true;
            size++;
            return new Node<>(data, null);
        }

        if (data.compareTo(node.data) < 0) {
            node.left = insert(node.left, data, inserted);
        } else if (data.compareTo(node.data) > 0) {
            node.right = insert(node.right, data, inserted);
        }

        adjustHeight(node);
        return node;
    }

    /**
     * Deletes a node with the specified data from the tree.
     * @param data the element to be deleted
     * @return true if the deletion was successful, false if the element was not found
     */
    @Override
    public boolean delete(T data) {
        if (root == null) {
            return false; // Tree is empty
        }

        boolean[] deleted = {false};
        root = delete(root, data, deleted);
        return deleted[0];
    }

    /**
     * Deletes a node with the specified data from the tree.
     * @param node The current node to compare against.
     * @param data The data to delete.
     * @param deleted A boolean array flag indicating whether the deletion was successful.
     * @return The updated node after deletion.
     */
    private Node<T> delete(Node<T> node, T data, boolean[] deleted) {
        if (node == null) {
            return null; // Data not found
        }

        if (data.compareTo(node.data) < 0) {
            node.left = delete(node.left, data, deleted);
        } else if (data.compareTo(node.data) > 0) {
            node.right = delete(node.right, data, deleted);
        } else {
            deleted[0] = true; // Data found
            size--;
            if (node.left == null) {
                return node.right; // Node with only right child or no child
            } else if (node.right == null) {
                return node.left; // Node with only left child
            }

            // Node with two children: get the inorder successor (smallest in the right subtree)
            Node<T> minNode = findMin(node.right);
            node.data = minNode.data; // Copy the inorder successor's value to this node
            // Delete the inorder successor - but don't count this as a separate deletion
            node.right = deleteSuccessor(node.right, minNode.data);
        }
        return node;
    }

    /**
     * Helper method to delete the successor node without affecting the size count.
     * This is used when replacing a node with two children.
     */
    private Node<T> deleteSuccessor(Node<T> node, T data) {
        if (node == null) {
            return null;
        }

        if (data.compareTo(node.data) < 0) {
            node.left = deleteSuccessor(node.left, data);
        } else if (data.compareTo(node.data) > 0) {
            node.right = deleteSuccessor(node.right, data);
        } else {

            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }

            Node<T> minNode = findMin(node.right);
            node.data = minNode.data;
        }
        return node;
    }

    /**
     * Gets the type of the tree.
     * @return the type of the tree as a string
     */
    @Override
    public String getTreeType() {
        return TreeType.BST.toString();
    }
}
