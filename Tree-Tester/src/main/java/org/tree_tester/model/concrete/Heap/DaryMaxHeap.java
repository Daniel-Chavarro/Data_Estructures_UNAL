package org.tree_tester.model.concrete.Heap;

import org.tree_tester.model.Abstract.Heap;

import java.util.List;

/**
 * D-ary Max Heap implementation where each parent node is greater than or equal to its children.
 * The degree parameter determines how many children each node can have.
 *
 * @param <T> the type of elements stored in the heap, must implement Comparable
 */
public class DaryMaxHeap<T extends Comparable<T>> extends Heap<T> {

    /**
     * Constructs a D-ary max heap with the specified degree.
     *
     * @param degree the number of children each node can have
     */
    public DaryMaxHeap(int degree) {
        super(degree);
    }

    /**
     * Heapifies down the element at the specified index to maintain the max-heap property.
     *
     * @param index The index of the element to heapify down.
     */
    @Override
    protected void heapifyDown(int index) {
        int largest = index;
        int firstChildIndex = DEGREE * index + 1;

        for (int i = 0; i < DEGREE; i++) {
            int childIndex = firstChildIndex + i;
            if (childIndex < size && heap.get(childIndex).compareTo(heap.get(largest)) > 0) {
                largest = childIndex;
            }
        }

        if (largest != index) {
            swap(index, largest);
            heapifyDown(largest);
        }
    }

    /**
     * Heapifies up the element at the specified index to maintain the max-heap property.
     *
     * @param index The index of the element to heapify up.
     */
    @Override
    protected void heapifyUp(int index) {
        while (index > 0) {
            int parentIndex = (index - 1) / DEGREE;
            if (heap.get(index).compareTo(heap.get(parentIndex)) <= 0) {
                break;
            }
            swap(index, parentIndex);
            index = parentIndex;
        }
    }

    /**
     * Checks if the heap is a valid max-heap.
     * A max-heap is valid if every parent node is greater than or equal to its children.
     *
     * @return True if the heap is a valid max-heap, false otherwise.
     */
    @Override
    public boolean isValidHeap() {
        for (int i = 0; i < size; i++) {
            int firstChildIndex = DEGREE * i + 1;
            for (int j = 0; j < DEGREE; j++) {
                int childIndex = firstChildIndex + j;
                if (childIndex < size && heap.get(i).compareTo(heap.get(childIndex)) < 0) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Builds the heap from a list of elements.
     *
     * @param data The list of elements to build the heap from.
     */
    @Override
    public void buildHeap(List<T> data) {
        heap.clear();
        heap.addAll(data);
        size = heap.size();
        for (int i = (size - 2) / DEGREE; i >= 0; i--) {
            heapifyDown(i);
        }
    }

    /**
     * Gets the type of the heap.
     *
     * @return A string representing the type of the heap.
     */
    @Override
    public String getHeapType() {
        return DEGREE + "-ary Max Heap";
    }
}
