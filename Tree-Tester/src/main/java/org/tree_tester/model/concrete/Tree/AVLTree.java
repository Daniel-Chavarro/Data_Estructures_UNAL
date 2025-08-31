package org.tree_tester.model.concrete.Tree;

import org.tree_tester.model.Abstract.Tree;
import org.tree_tester.model.concrete.TreeType;

/**
 * AVL Tree implementation - a self-balancing binary search tree.
 * In an AVL tree, the heights of the two child subtrees of any node differ by at most one.
 * If at any time they differ by more than one, rebalancing is performed to restore this property.
 *
 * @param <T> the type of elements stored in the AVL tree, must implement Comparable
 */
public class AVLTree<T extends Comparable<T>> extends Tree<T> {

    /**
     * Constructs an empty AVL tree.
     */
    public AVLTree() {
        super();
    }

    /**
     * Inserts a new element into the AVL tree.
     *
     * @param data the element to be inserted
     * @return True if the element was successfully inserted, false if it already exists
     */
    @Override
    public boolean insert(T data) {
        boolean[] inserted = {false};
        root = insert(root, data, null, inserted);
        return inserted[0];
    }

    /**
     * Recursively inserts a new element into the AVL tree.
     *
     * @param node     the current node in the tree
     * @param data     the element to be inserted
     * @param parent   the parent node of the current node
     * @param inserted a flag array indicating whether the element was inserted
     * @return the new node if inserted, or the existing node if not
     */
    private Node<T> insert(Node<T> node, T data, Node<T> parent, boolean[] inserted) {
        if (node == null) {
            inserted[0] = true;
            size++;
            return new Node<>(data, parent);
        }
        if (data.compareTo(node.data) < 0) {
            node.left = insert(node.left, data, node, inserted);
        } else if (data.compareTo(node.data) > 0) {
            node.right = insert(node.right, data, node, inserted);
        }

        adjustHeight(node);
        return balance(node);
    }

    /**
     * Deletes an element from the AVL tree.
     *
     * @param data the element to be deleted
     * @return True if the element was successfully deleted, false if it was not found
     */
    @Override
    public boolean delete(T data) {
        boolean[] deleted = {false};
        root = delete(root, data, deleted);
        return deleted[0];
    }

    /**
     * Recursively deletes an element from the AVL tree.
     *
     * @param node    the current node in the tree
     * @param data    the element to be deleted
     * @param deleted a flag array indicating whether the element was deleted
     * @return the new root of the subtree after deletion
     */
    private Node<T> delete(Node<T> node, T data, boolean[] deleted) {
        if (node == null) {
            return null;
        }

        if (data.compareTo(node.data) < 0) {
            node.left = delete(node.left, data, deleted);
        } else if (data.compareTo(node.data) > 0) {
            node.right = delete(node.right, data, deleted);
        } else {
            deleted[0] = true;
            size--;
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }
            Node<T> minNode = findMin(node.right);
            node.data = minNode.data;
            node.right = deleteSuccessor(node.right, minNode.data);
        }

        adjustHeight(node);
        return balance(node);
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
            node.right = deleteSuccessor(node.right, minNode.data);
        }

        adjustHeight(node);
        return balance(node);
    }


    /**
     * Returns the type of the tree.
     *
     * @return a string representing the type of the tree
     */
    @Override
    public String getTreeType() {
        return TreeType.AVL.toString();
    }

    /**
     * Balances the given node if it is unbalanced.
     *
     * @param node the node to be balanced
     * @return the new root of the subtree after balancing
     */
    private Node<T> balance(Node<T> node) {
        if (node == null) {
            return node;
        }

        int balance = getBalance(node);

        if (balance > 1 && getBalance(node.left) >= 0) {
            return rotateRight(node);
        }

        if (balance < -1 && getBalance(node.right) <= 0) {
            return rotateLeft(node);
        }

        if (balance > 1 && getBalance(node.left) < 0) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        if (balance < -1 && getBalance(node.right) > 0) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node;
    }

    /**
     * Rotates the subtree rooted at the given node to the right.
     *
     * @param y the root of the subtree to be rotated
     * @return the new root of the subtree after rotation
     */
    private Node<T> rotateRight(Node<T> y) {
        Node<T> x = y.left;
        Node<T> T2 = x.right;

        x.right = y;
        y.left = T2;

        x.parent = y.parent;
        y.parent = x;
        if (T2 != null) {
            T2.parent = y;
        }

        adjustHeight(y);
        adjustHeight(x);

        return x;
    }

    /**
     * Rotates the subtree rooted at the given node to the left.
     *
     * @param x the root of the subtree to be rotated
     * @return the new root of the subtree after rotation
     */
    private Node<T> rotateLeft(Node<T> x) {
        Node<T> y = x.right;
        Node<T> T2 = y.left;

        y.left = x;
        x.right = T2;

        y.parent = x.parent;
        x.parent = y;
        if (T2 != null) {
            T2.parent = x;
        }

        adjustHeight(x);
        adjustHeight(y);

        return y;
    }

}
