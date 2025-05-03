package tester.model.Abstract;

public interface LinkedList <T>{
    void pushFront(T value);
    void pushBack(T value);
    T popFront();
    T popBack();
    Object find(T value);
    void erase(T value);
    void addBefore(T valueToFind, T valueToAdd);
    void addAfter(T valueToFind, T valueToAdd);
}
