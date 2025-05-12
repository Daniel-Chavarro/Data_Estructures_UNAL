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


    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }

    public Node<T> getPrev() {
        return prev;
    }

    public void setPrev(Node<T> prev) {
        this.prev = prev;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
