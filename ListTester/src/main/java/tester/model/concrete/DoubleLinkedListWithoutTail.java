package tester.model.concrete;

import tester.model.Abstract.LinkedList;

public class DoubleLinkedListWithoutTail<T> implements LinkedList<T> {

    /**
     * @param value
     */
    @Override
    public void pushFront(T value) {

    }

    /**
     * @param value
     */
    @Override
    public void pushBack(T value) {

    }

    /**
     * @return
     */
    @Override
    public T popFront() {
        return null;
    }

    /**
     * @return
     */
    @Override
    public T popBack() {
        return null;
    }

    /**
     * @param value
     * @return
     */
    @Override
    public Object find(T value) {
        return null;
    }

    /**
     * @param value
     */
    @Override
    public void erase(T value) {

    }

    /**
     * @param valueToFind
     * @param valueToAdd
     */
    @Override
    public void addBefore(T valueToFind, T valueToAdd) {

    }

    /**
     * @param valueToFind
     * @param valueToAdd
     */
    @Override
    public void addAfter(T valueToFind, T valueToAdd) {

    }
}
