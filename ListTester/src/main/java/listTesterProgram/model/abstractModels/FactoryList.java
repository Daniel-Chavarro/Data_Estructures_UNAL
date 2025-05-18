package listTesterProgram.model.abstractModels;

import listTesterProgram.model.concrete.TypeLinkedList;


public interface FactoryList {
    <T> LinkedList<T> createLinkedList(TypeLinkedList type);
}
