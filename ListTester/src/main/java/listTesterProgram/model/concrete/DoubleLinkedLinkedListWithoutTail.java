package listTesterProgram.model.concrete;

import listTesterProgram.model.abstractModels.LinkedList;
import listTesterProgram.model.exceptions.EmptyList;

import java.util.NoSuchElementException;

public class DoubleLinkedLinkedListWithoutTail<T> implements LinkedList<T> {
    private Node<T> head;
    private int size;

    public DoubleLinkedLinkedListWithoutTail() {
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
            head.prev = newNode;
        }
        size++;
        head = newNode;
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
            head = newNode;
        } else {
            Node<T> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
            newNode.prev = current;
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
        Node<T> temp = head;
        head = head.next;
        if (head != null) {
            head.prev = null;
        }
        size--;
        return temp.value;
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
        Node<T> current = head;
        while (current.next != null) {
            current = current.next;
        }
        T value = current.value;
        if (current.prev != null) {
            current.prev.next = null;
        } else {
            head = null;
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
     * @throws EmptyList              if the list is empty
     * @throws NoSuchElementException if the value is not found
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
        throw new NoSuchElementException("Value not found in the list");
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
        Node<T> toRemove = find(value);
        if (toRemove == head) {
            popFront();
        } else {
            if (toRemove.next != null) {
                toRemove.next.prev = toRemove.prev;
            }
            toRemove.prev.next = toRemove.next;
            size--;
        }
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
        newNode.prev = node;
        if (node.next != null) {
            node.next.prev = newNode;
        }
        node.next = newNode;
        size++;
    }

    /**
     * Adds a new node before the given node
     * Complexity: O(1)
     *
     * @param node  the node before which the new node will be added
     * @param value the value to be added
     * @throws IllegalArgumentException if the node is null
     */
    @Override
    public void addBefore(Node<T> node, T value) {
        if (node == null) {
            throw new IllegalArgumentException("Node cannot be null");
        }
        Node<T> newNode = new Node<>(value);
        newNode.prev = node.prev;
        newNode.next = node;
        if (node.prev != null) {
            node.prev.next = newNode;
        } else {
            head = newNode;
        }
        node.prev = newNode;
        size++;

    }
}
