package org.tree_tester.model.factories;

import org.tree_tester.model.Abstract.Heap;
import org.tree_tester.model.Abstract.Tree;
import org.tree_tester.model.concrete.*;
import org.tree_tester.model.concrete.Heap.DaryMaxHeap;
import org.tree_tester.model.concrete.Heap.DaryMinHeap;
import org.tree_tester.model.concrete.Tree.AVLTree;
import org.tree_tester.model.concrete.Tree.BinarySearchTree;

public class TreeFactory {

    public static <T extends Comparable<T>> Tree<T>createTree(TreeType treeType) {
        switch (treeType) {
            case AVL:
                return new AVLTree<T>();
            case BST:
                return new BinarySearchTree<T>();
            default:
                throw new IllegalArgumentException("Unsupported tree type: " + treeType);
        }
    }

    public static <T extends Comparable<T>>Heap<T> createHeap(TreeType treeType, int degree) {
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
