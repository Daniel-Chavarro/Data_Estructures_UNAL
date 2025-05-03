package tester.model.Abstract;

/**
 * Factory interface for creating different types of linked lists.
 */
public interface ListFactory<T>{
    /**
     * Creates a singly linked list without a tail pointer.
     *
     * @return a new linked list implementation without tail reference
     */
    LinkedList<T> createLinkedWithoutTail();
    
    /**
     * Creates a singly linked list with a tail pointer.
     *
     * @return a new linked list implementation with tail reference
     */
    LinkedList<T> createLinkedWithTail();
    
    /**
     * Creates a doubly linked list without a tail pointer.
     *
     * @return a new doubly linked list implementation without tail reference
     */
    LinkedList<T> createDoubleLinkedWithoutTail();
    
    /**
     * Creates a doubly linked list with a tail pointer.
     *
     * @return a new doubly linked list implementation with tail reference
     */
    LinkedList<T> createDoubleLinkedWithTail();
}