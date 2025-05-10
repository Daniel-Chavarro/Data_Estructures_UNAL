package tester.model.concrete;

import tester.model.Abstract.LinkedList;

public class DoubleLinkedListWithoutTail<T> implements LinkedList<T> {

    private Node<T> head;
    private int size;

    public DoubleLinkedListWithoutTail() {
        head = null;
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
        if (size > 0) {
            newNode.next = head;
            head.previous = newNode;
        }

        head = newNode;
        size++;
    }

    /**
     * Adds a new node with the given value to the back of the list.
     * Time complexity: O(N) because it needs to traverse the list to find the last node.
     *
     * @param value the value to add to the back of the list
     */

    @Override
    public void pushBack(T value) {
        if (size == 0) {
            pushFront(value);
            return;
        }

        Node<T> newNode = new Node<>(value);
        Node<T> current = head;

        while (current.next != null) {
            current = current.next;
        }

        current.next = newNode;
        newNode.previous = current;
        size++;
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
        head.previous = null;

        size--;
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

        Node<T> prev = head;
        while (prev.next != null && prev.next.next != null) {
            prev = prev.next;
        }

        Node<T> lastNode = prev.next;
        T data = lastNode.data;
        prev.next = null;
        size--;

        return data;
    }

    /**
     * @param value
     * @return
     */
    @Override
    public Object find(T value) {
        return null;
    }

    /**
     * @param value
     */
    @Override
    public void erase(T value) {

    }

    /**
     * @param node
     * @param valueToAdd
     */
    @Override
    public void addBefore(Node<T> node, T valueToAdd) {

    }

    /**
     * @param node
     * @param valueToAdd
     */
    @Override
    public void addAfter(Node<T> node, T valueToAdd) {

    }
}
