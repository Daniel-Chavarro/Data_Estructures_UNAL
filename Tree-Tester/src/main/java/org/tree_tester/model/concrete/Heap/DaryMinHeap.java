package org.tree_tester.model.concrete.Heap;

import org.tree_tester.model.Abstract.Heap;

import java.util.List;

public class DaryMinHeap<T extends Comparable<T>> extends Heap<T> {

    /**
     * Constructor for DaryMinHeap with a specified DEGREE.
     *
     * @param degree The number of children each node can have.
     */
    public DaryMinHeap(int degree) {
        super(degree);
    }

    @Override
    protected void heapifyDown(int index) {
        int smallest = index;
        int firstChildIndex = DEGREE * index + 1;

        for (int i = 0; i < DEGREE; i++) {
            int childIndex = firstChildIndex + i;
            if (childIndex < size && heap.get(childIndex).compareTo(heap.get(smallest)) < 0) {
                smallest = childIndex;
            }
        }

        if (smallest != index) {
            swap(index, smallest);
            heapifyDown(smallest);
        }

    }

    @Override
    protected void heapifyUp(int index) {
        while (index > 0) {
            int parentIndex = (index - 1) / DEGREE;
            if (heap.get(index).compareTo(heap.get(parentIndex)) >= 0) {
                break;
            }
            swap(index, parentIndex);
            index = parentIndex;
        }
    }

    @Override
    public boolean isValidHeap() {
        for (int i = 0; i < size; i++) {
            int firstChildIndex = DEGREE * i + 1;
            for (int j = 0; j < DEGREE; j++) {
                int childIndex = firstChildIndex + j;
                if (childIndex < size && heap.get(i).compareTo(heap.get(childIndex)) > 0) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void buildHeap(List<T> data) {
        heap.clear();
        heap.addAll(data);
        size = heap.size();
        for (int i = (size - 2) / DEGREE; i >= 0; i--) {
            heapifyDown(i);
        }
    }

    @Override
    public String getHeapType() {
        return DEGREE + "-ary Min Heap";
    }
}
