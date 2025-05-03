package tester.model;

import tester.model.Abstract.LinkedList;
import tester.model.Abstract.ListFactory;

public class ListCreator<T> implements ListFactory<T>{

    /**
     * Creates a singly linked list without a tail pointer.
     *
     * @return a new linked list implementation without tail reference
     */
    @Override
    public LinkedList<T> createLinkedWithoutTail() {
        return null;
    }

    /**
     * Creates a singly linked list with a tail pointer.
     *
     * @return a new linked list implementation with tail reference
     */
    @Override
    public LinkedList<T> createLinkedWithTail() {
        return null;
    }

    /**
     * Creates a doubly linked list without a tail pointer.
     *
     * @return a new doubly linked list implementation without tail reference
     */
    @Override
    public LinkedList<T> createDoubleLinkedWithoutTail() {
        return null;
    }

    /**
     * Creates a doubly linked list with a tail pointer.
     *
     * @return a new doubly linked list implementation with tail reference
     */
    @Override
    public LinkedList<T> createDoubleLinkedWithTail() {
        return null;
    }
}
