package listTesterProgram.model.abstractModels;

import listTesterProgram.model.concrete.Node;

public interface LinkedList<T> {
    boolean isEmpty();
    void pushFront(T value);
    void pushBack(T value);
    T popFront();
    T popBack();
    Node<T> find(T value);
    void erase(T value);
    void addAfter(Node<T> node, T value);
    void addBefore(Node<T> node, T value);

}
