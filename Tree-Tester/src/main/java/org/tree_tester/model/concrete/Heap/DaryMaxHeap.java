package org.tree_tester.model.concrete.Heap;

import org.tree_tester.model.Abstract.Heap;

import java.util.List;

public class DaryMaxHeap <T extends  Comparable<T>> implements Heap<T> {


    @Override
    public void insert(T data) {

    }

    @Override
    public T extractRoot() {
        return null;
    }

    @Override
    public T peek() {
        return null;
    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }

    @Override
    public int getDegree() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean isValidHeap() {
        return false;
    }

    @Override
    public void buildHeap(List<T> data) {

    }

    @Override
    public void clear() {

    }

    @Override
    public String getHeapType() {
        return "";
    }
}
