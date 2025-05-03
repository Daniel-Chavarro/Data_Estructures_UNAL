package tester.model.concrete;

public class Node<T> {
    protected T data;
    protected Node<T> next;
    protected Node<T> previous;

    public Node(T data) {
        this.data = data;
        this.next = null;
        this.previous = null;
    }
}
