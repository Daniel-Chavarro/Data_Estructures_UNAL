package listTesterProgram.model.abstractModels;

import listTesterProgram.model.concrete.TypeLinkedList;

import java.util.ArrayList;

public interface FactoryList {
    <T> LinkedList<T> createLinkedList(TypeLinkedList type);
}
