package tester.model.Abstract;

import tester.model.concrete.Node;

public interface LinkedList <T>{
    void pushFront(T value);
    void pushBack(T value);
    T popFront();
    T popBack();
    Object find(T value);
    void erase(T value);
    void addBefore(Node<T> node, T valueToAdd);
    void addAfter(Node<T> node, T valueToAdd);
}
