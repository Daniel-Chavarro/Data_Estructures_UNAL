package listTesterProgram.model.concrete;

public class Node<T> {
    protected Node<T> next;
    protected Node<T> prev;
    protected T value;

    public Node(T value) {
        this.value = value;
        this.next = null;
        this.prev = null;
    }

}
