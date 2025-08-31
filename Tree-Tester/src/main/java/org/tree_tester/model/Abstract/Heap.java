package org.tree_tester.model.Abstract;

import java.util.ArrayList;
import java.util.List;

public abstract class Heap<T extends Comparable<T>> {
    /**
     * The DEGREE of the heap, which determines how many children each node can have.
     * For example, a binary heap has a DEGREE of 2, a ternary heap has a DEGREE of 3, etc.
     */
    protected final int DEGREE;
    /**
     * The list that holds the elements of the heap.
     * It is used to store the elements in a way that allows for efficient heap operations.
     */
    protected List<T> heap;
    /**
     * The size of the heap, representing the number of elements currently in the heap.
     */
    protected int size;

    /**
     * Constructor for the Heap class.
     *
     * @param DEGREE The DEGREE of the heap, must be at least 2.
     */
    public Heap(int DEGREE) {
        if (DEGREE < 2) {
            throw new IllegalArgumentException("Degree must be at least 2");
        }
        this.DEGREE = DEGREE;
        this.size = 0;
        this.heap = new ArrayList<>();
    }

    /**
     * Inserts a new element into the heap.
     *
     * @param data the element to be inserted
     */
    public void insert(T data) {
        heap.add(data);
        size++;
        heapifyUp(size - 1);
    }

    /**
     * Extracts the root element from the heap.
     * The root is the minimum or maximum element, depending on the type of heap.
     * After extraction, the heap is restructured to maintain the heap property.
     *
     * @return The root element of the heap.
     */
    public T extractRoot() {
        T root = heap.get(0);
        swap(0, heap.size() - 1);
        heap.remove(heap.size() - 1);
        size--;
        if (!heap.isEmpty()) {
            heapifyDown(0);
        }
        return root;
    }

    /**
     * Swaps two elements in the heap at the specified indices.
     *
     * @param index1 The index of the first element to swap.
     * @param index2 The index of the second element to swap.
     */
    protected void swap(int index1, int index2) {
        T temp = heap.get(index1);
        heap.set(index1, heap.get(index2));
        heap.set(index2, temp);
    }

    /**
     * Heapifies down the element at the specified index to maintain the heap property.
     * This method should be implemented by subclasses to define the specific heapification logic.
     *
     * @param index The index of the element to heapify down.
     */
    protected abstract void heapifyDown(int index);

    /**
     * Heapifies up the element at the specified index to maintain the heap property.
     * This method should be implemented by subclasses to define the specific heapification logic.
     *
     * @param index The index of the element to heapify up.
     */
    protected abstract void heapifyUp(int index);

    /**
     * Peeks at the root element of the heap without removing it.
     * If the heap is empty, it returns null.
     *
     * @return The root element of the heap, or null if the heap is empty.
     */
    public T peek() {
        if (heap.isEmpty()) {
            return null;
        }
        return heap.get(0);
    }

    /**
     * Gets the current size of the heap.
     *
     * @return The number of elements in the heap.
     */
    public int getSize() {
        return size;
    }

    /**
     * Gets the height of the heap.
     * The height is defined as the number of edges on the longest path from the root to a leaf node.
     *
     * @return The height of the heap.
     */
    public int getHeight() {
        if (size == 0) {
            return 0;
        }
        return (int) Math.ceil(Math.log(size + 1) / Math.log(DEGREE));
    }

    /**
     * Gets the DEGREE of the heap.
     * The DEGREE is the number of children each node can have.
     *
     * @return The DEGREE of the heap.
     */
    public int getDegree() {
        return DEGREE;
    }

    /**
     * Checks if the heap is empty.
     * An empty heap has no elements.
     *
     * @return true if the heap has no elements, false otherwise.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Checks if the heap is a valid heap.
     * This method should be implemented by subclasses to define the specific validation logic.
     *
     * @return true if the heap is valid, false otherwise.
     */
    public abstract boolean isValidHeap();

    /**
     * Builds a heap from the provided list of data.
     * This method should be implemented by subclasses to define the specific building logic.
     *
     * @param data The list of elements to build the heap from.
     */
    public abstract void buildHeap(List<T> data);

    /**
     * Clears the heap, removing all elements and resetting its size.
     * After this operation, the heap will be empty.
     */
    public void clear() {
        heap.clear();
        size = 0;
    }

    /**
     * Gets the type of the heap.
     * This method should be implemented by subclasses to return the specific type of heap.
     *
     * @return A string representing the type of the heap (e.g., "MinHeap", "MaxHeap").
     */
    public abstract String getHeapType();

    /**
     * Gets the parent index of the element at the specified index.
     * The parent index is calculated based on the DEGREE of the heap.
     *
     * @param index The index of the child element.
     * @return The index of the parent element.
     */
    protected int getParentIndex(int index) {
        return (index - 1) / DEGREE;
    }

    /**
     * Gets the index of the child element at the specified child number for the element at the given index.
     * The child number is zero-based, meaning 0 refers to the first child, 1 to the second child, and so on.
     *
     * @param index       The index of the parent element.
     * @param childNumber The zero-based index of the child.
     * @return The index of the specified child element.
     */
    protected int getChildIndex(int index, int childNumber) {
        return index * DEGREE + childNumber + 1;
    }
}
