package listTesterProgram.model.concrete;

import listTesterProgram.model.abstractModels.LinkedList;
import listTesterProgram.model.exceptions.EmptyList;

import java.util.NoSuchElementException;


public class LinkedLinkedListWithTail<T> implements LinkedList<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;


    /**
     * Constructor for LinkedLinkedListWithTail
     * Initializes an empty list
     */
    public LinkedLinkedListWithTail() {
        this.head = null;
        this.tail = null;
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
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
        size++;

    }

    /**
     * Adds a new node to the back of the list
     * Complexity: O(1)
     *
     * @param value the value to be added
     */
    @Override
    public void pushBack(T value) {
        Node<T> newNode = new Node<>(value);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    /**
     * Removes the first node from the list
     * Complexity: O(1)
     *
     * @return the value of the removed node
     * @throws EmptyList if the list is empty
     */
    @Override
    public T popFront() {
        if (isEmpty()) {
            throw new EmptyList("List is empty");
        }
        T value = head.value;
        head = head.next;
        size--;
        if (isEmpty()) {
            tail = null;
        }
        return value;
    }

    /**
     * Removes the last node from the list
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
        T value = tail.value;
        if (head == tail) {
            head = null;
            tail = null;
        } else {
            Node<T> current = head;
            while (current.next != tail) {
                current = current.next;
            }
            current.next = null;
            tail = current;
        }
        size--;
        return value;
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
     * Removes the first node with the given value
     * Complexity: O(N)
     *
     * @param value the value to be removed
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
                if (current.next == null) {
                    tail = current;
                }
                return;
            }
            current = current.next;
        }

        throw new NoSuchElementException("Value not found in the list");

    }

    /**
     * Adds a new node after the given node
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
        if (node == tail) {
            tail = newNode;
        }
        size++;
    }

    /**
     * Adds a new node before the given node
     * Complexity: O(N)
     *
     * @param node  the node before which the new node will be added
     * @param value the value to be added
     * @throws IllegalArgumentException if the node is null
     * @throws NoSuchElementException   if the node is not found in the list
     */
    @Override
    public void addBefore(Node<T> node, T value) {
        if (node == null) {
            throw new IllegalArgumentException("Node cannot be null");
        }
        Node<T> newNode = new Node<>(value);
        if (node == head) {
            pushFront(value);
            return;
        }
        Node<T> current = head;
        while (current != null && current.next != node) {
            current = current.next;
        }
        if (current == null) {
            throw new NoSuchElementException("Node not found in the list");
        }
        newNode.next = node;
        current.next = newNode;
        size++;

    }

    /**
     * Transforms the list into a string representation
     * Complexity: O(N)
     * */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node<T> current = head;
        while (current != null) {
            sb.append(current.value).append(" ");
            current = current.next;
        }
        sb.append("]");
        return sb.toString().trim();
    }
}
