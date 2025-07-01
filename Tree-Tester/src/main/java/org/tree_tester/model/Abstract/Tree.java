package org.tree_tester.model.Abstract;

import java.util.List;

public interface Tree <T extends Comparable<T>> {
    boolean insert(T data);

    boolean search(T data);

    boolean delete(T data);

    int getSize();

    int getHeight();

    boolean isEmpty();

    int countLeaves();

    T findMin();

    T findMax();

    boolean isBalanced();

    List<T> inOrder();

    List<T> preOrder();

    List<T> postOrder();

    List<T> levelOrder();

    void clear();

    @Override
    String toString();

    String getTreeType();
}
