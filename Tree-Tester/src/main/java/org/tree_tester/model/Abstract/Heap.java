package org.tree_tester.model.Abstract;

import java.util.List;

public interface Heap<T extends Comparable<T>> {
    void insert(T data);

    T extractRoot();

    T peek();

    int getSize();

    int getHeight();

    int getDegree();

    boolean isEmpty();

    boolean isValidHeap();

    void buildHeap(List<T> data);

    void clear();

    String getHeapType();
}
