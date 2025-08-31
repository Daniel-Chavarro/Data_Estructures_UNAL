package org.tree_tester.model.factories;

import org.tree_tester.model.Abstract.Heap;
import org.tree_tester.model.Abstract.Tree;
import org.tree_tester.model.concrete.*;
import org.tree_tester.model.concrete.Heap.DaryMaxHeap;
import org.tree_tester.model.concrete.Heap.DaryMinHeap;
import org.tree_tester.model.concrete.Tree.AVLTree;
import org.tree_tester.model.concrete.Tree.BinarySearchTree;

/**
 * Factory class for creating instances of different tree and heap data structures.
 * Provides static methods to instantiate concrete implementations based on TreeType.
 */
public class TreeFactory {

    /**
     * Creates a tree instance based on the specified tree type.
     *
     * @param <T>      the type of elements stored in the tree, must implement Comparable
     * @param treeType the type of tree to create (AVL or BST)
     * @return a new instance of the specified tree type
     * @throws IllegalArgumentException if the tree type is not supported
     */
    public static <T extends Comparable<T>> Tree<T> createTree(TreeType treeType) {
        switch (treeType) {
            case AVL:
                return new AVLTree<T>();
            case BST:
                return new BinarySearchTree<T>();
            default:
                throw new IllegalArgumentException("Unsupported tree type: " + treeType);
        }
    }

    /**
     * Creates a heap instance based on the specified heap type and degree.
     *
     * @param <T>      the type of elements stored in the heap, must implement Comparable
     * @param treeType the type of heap to create (MIN_HEAP or MAX_HEAP)
     * @param degree   the degree of the heap (number of children per node)
     * @return a new instance of the specified heap type
     * @throws IllegalArgumentException if the heap type is not supported
     */
    public static <T extends Comparable<T>> Heap<T> createHeap(TreeType treeType, int degree) {
        switch (treeType) {
            case MIN_HEAP:
                return new DaryMinHeap<>(degree);
            case MAX_HEAP:
                return new DaryMaxHeap<>(degree);
            default:
                throw new IllegalArgumentException("Unsupported Heap type: " + treeType);
        }
    }
}
