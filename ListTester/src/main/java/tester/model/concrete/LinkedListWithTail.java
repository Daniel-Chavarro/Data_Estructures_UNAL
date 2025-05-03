package tester.model.concrete;

import tester.model.Abstract.LinkedList;

/**
 * A simple linked list implementation that maintains references to both the head and the tail.
 * This allows for efficient O(1) operations for adding to the back (pushBack).
 *
 * @param <T> the type of data stored in the list
 */
public class LinkedListWithTail<T> implements LinkedList<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;


    /**
     * Constructs an empty linked list with a tail pointer.
     */
    public LinkedListWithTail() {
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * Adds a new node with the given value to the front of the list.
     * Time complexity: O(1)
     *
     * @param value the value to add to the front of the list
     */
    @Override
    public void pushFront(T value) {
        Node<T> newNode = new Node<>(value);
        if (size == 0) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
        size++;
    }

    /**
     * Adds a new node with the given value to the back of the list.
     * Time complexity: O(1) thanks to the tail pointer.
     *
     * @param value the value to add to the back of the list
     */
    @Override
    public void pushBack(T value) {
        Node<T> newNode = new Node<>(value);
        if (size == 0) {
            pushFront(value);
        } else {
            tail.next = newNode;
            tail = newNode;
            size++;
        }
    }

    /**
     * Removes the node from the front of the list and returns its value.
     * Time complexity: O(1)
     *
     * @return the value of the node removed from the front, or null if the list is empty
     */
    @Override
    public T popFront() {
        if (size == 0) {
            return null;
        }
        T data = head.data;
        head = head.next;
        size--;
        if (size == 0) {
            tail = null;
        }
        return data;
    }

    /**
     * Removes the node from the back of the list and returns its value.
     * Time complexity: O(N) because it needs to traverse the list to find the second-to-last node.
     *
     * @return the value of the node removed from the back, or null if the list is empty
     */
    @Override
    public T popBack() {
        if (size == 0) {
            return null;
        }

        if (size == 1) {
            return popFront();
        }


        Node<T> current = head;
        while (current.next != tail) {
            current = current.next;
        }

        T data = tail.data;
        tail = current;
        tail.next = null;
        size--;
        return data;
    }

    /**
     * Finds the first node containing the specified value.
     * Time complexity: O(N) in the worst case (value is at the end or not present).
     *
     * @param value the value to search for
     * @return the Node containing the value, or null if the value is not found in the list
     */
    @Override
    public Node<T> find(T value) {
        Node<T> current = head;
        while (current != null) {
            if (current.data.equals(value)) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    /**
     * Removes the first occurrence of the node with the specified value from the list.
     * Time complexity: O(N) in the worst case.
     *
     * @param value the value to remove from the list
     */
    @Override
    public void erase(T value) {
        if (size == 0) {
            return;
        }

        if (head.data.equals(value)) {
            popFront();
            return;
        }

        Node<T> current = head;
        while (current.next != null && !current.next.data.equals(value)) {
            current = current.next;
        }

        if (current.next != null) {
            if (current.next == tail) {
                tail = current;
            }
            current.next = current.next.next;
            size--;
        }

    }

    /**
     * Adds a new node with `valueToAdd` immediately before the first node containing `valueToFind`.
     * If `valueToFind` is not found, the list remains unchanged.
     * Time complexity: O(N) in the worst case.
     *
     * @param valueToFind the value of the node before which the new node should be added
     * @param valueToAdd  the value to add to the list
     */
    @Override
    public void addBefore(T valueToFind, T valueToAdd) {
        if (size == 0) {
            return;
        }


        if (head.data.equals(valueToFind)) {
            pushFront(valueToAdd);
            return;
        }

        Node<T> current = head;
        while (current.next != null && !current.next.data.equals(valueToFind)) {
            current = current.next;
        }

        if (current.next != null) {
            Node<T> newNode = new Node<>(valueToAdd);
            newNode.next = current.next;
            size++;
        }
    }

    /**
     * Adds a new node with `valueToAdd` immediately after the first node containing `valueToFind`.
     * If `valueToFind` is not found, the list remains unchanged.
     * Time complexity: O(N) due to the find operation.
     *
     * @param valueToFind the value of the node after which the new node should be added
     * @param valueToAdd  the value to add to the list
     */
    @Override
    public void addAfter(T valueToFind, T valueToAdd) {
        Node<T> findNode = find(valueToFind);
        if (findNode == null) {
            return;
        }

        Node<T> newNode = new Node<>(valueToAdd);
        newNode.next = findNode.next;
        findNode.next = newNode;


        if (findNode == tail) {
            tail = newNode;
        }
        size++;
    }
}