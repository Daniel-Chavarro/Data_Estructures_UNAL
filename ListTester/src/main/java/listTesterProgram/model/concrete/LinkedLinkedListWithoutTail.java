package listTesterProgram.model.concrete;


import listTesterProgram.model.abstractModels.LinkedList;
import listTesterProgram.model.exceptions.EmptyList;

import java.util.NoSuchElementException;

public class LinkedLinkedListWithoutTail<T> implements LinkedList<T> {
    private Node<T> head;
    private int size;


    /**
     * Constructor for LinkedLinkedListWithoutTail
     * Initializes an empty list
     */
    public LinkedLinkedListWithoutTail() {
        this.head = null;
        this.size = 0;
    }

    /**
     * Checks if the list is empty
     * Complexity: O(1)
     *
     * @return true if the list is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return size <= 0;
    }

    /**
     * Adds a new node to the front of the list
     * Complexity: O(1)
     *
     * @param value the value to be added
     */
    @Override
    public void pushFront(T value) {
        Node<T> newNode = new Node<>(value);
        if (!isEmpty()) {
            newNode.next = head;
        }

        head = newNode;
        size++;
    }

    /**
     * Adds a new node to the back of the list
     * Complexity: O(N)
     *
     * @param value the value to be added
     */
    @Override
    public void pushBack(T value) {
        Node<T> newNode = new Node<>(value);
        if (isEmpty()) {
            pushFront(value);
        }

        Node<T> current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newNode;
        size++;
    }

    /**
     * Removes the first node from the front of the list
     * Complexity: O(N)
     *
     * @return the value of the removed node
     * @throws EmptyList if the list is empty
     */
    @Override
    public T popFront() {
        if (isEmpty()) {
            throw new EmptyList("List is empty");
        }

        Node<T> temp = head;
        head = head.next;
        size--;
        return temp.value;
    }

    /**
     * Removes the first node from the back of the list
     * Complexity: O(N)
     *
     * @return the value of the removed node
     * @throws EmptyList if the list is empty
     */
    @Override
    public T popBack() {
        if (isEmpty()) {
            throw new EmptyList("List is empty");
        }

        Node<T> current = head;
        while (current.next.next != null) {
            current = current.next;
        }

        Node<T> temp = current.next;
        current.next = null;
        size--;
        return temp.value;
    }

    /**
     * Finds a node with the given value
     * Complexity: O(N)
     *
     * @param value the value to be found
     * @return the node with the given value, or null if not found
     * @throws EmptyList if the list is empty
     */
    @Override
    public Node<T> find(T value) {
        if (isEmpty()) {
            throw new EmptyList("List is empty");
        }
        Node<T> current = head;
        while (current != null) {
            if (current.value.equals(value)) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    /**
     * Removes the first node with the given value.
     * Complexity: O(N).
     *
     * @param value the value to be removed.
     * @throws EmptyList              if the list is empty
     * @throws NoSuchElementException if the value is not found in the list
     */
    @Override
    public void erase(T value) {
        if (isEmpty()) {
            throw new EmptyList("List is empty");
        }

        if (head.value.equals(value)) {
            popFront();
            return;
        }

        Node<T> current = head;
        while (current.next != null) {
            if (current.next.value.equals(value)) {
                current.next = current.next.next;
                size--;
                return;
            }
            current = current.next;
        }

        throw new NoSuchElementException("Value not found in the list");
    }

    /**
     * Adds a new node after the given node, assuming the node is in the list.
     * Complexity: O(1)
     *
     * @param node  the node after which the new node will be added
     * @param value the value to be added
     * @throws IllegalArgumentException if the node is null
     */
    @Override
    public void addAfter(Node<T> node, T value) {
        if (node == null) {
            throw new IllegalArgumentException("Node cannot be null");
        }

        Node<T> newNode = new Node<>(value);
        newNode.next = node.next;
        node.next = newNode;
        size++;
    }

    /**
     * Adds a new node before the given node, assuming the node is in the list.
     * Complexity: O(N)
     *
     * @param node  the node before which the new node will be added
     * @param value the value to be added
     * @throws IllegalArgumentException if the node is null
     * @throws NoSuchElementException if the node is not found in the list
     */
    @Override
    public void addBefore(Node<T> node, T value) {
        if (node == null) {
            throw new IllegalArgumentException("Node cannot be null");
        }

        Node<T> newNode = new Node<>(value);
        if (node == head) {
            newNode.next = head;
            head = newNode;
        } else {
            Node<T> current = head;
            while (current != null && current.next != node) {
                current = current.next;
            }
            if (current == null) {
                throw new NoSuchElementException("Node not found in the list");
            }
            newNode.next = node;
            current.next = newNode;
        }

        size++;
    }
}
