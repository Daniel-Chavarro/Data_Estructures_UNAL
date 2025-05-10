package tester.model.concrete;

import java.util.Objects;

public class Node<T> {
    protected T data;
    protected Node<T> next;
    protected Node<T> previous;

    public Node(T data) {
        this.data = data;
        this.next = null;
        this.previous = null;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Node<?> node = (Node<?>) o;
        return Objects.equals(data, node.data) && Objects.equals(next, node.next) && Objects.equals(previous, node.previous);
    }

}
