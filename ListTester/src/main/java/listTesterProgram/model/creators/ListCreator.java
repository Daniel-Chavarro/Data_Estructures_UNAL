package listTesterProgram.model.creators;

import listTesterProgram.model.abstractModels.FactoryList;
import listTesterProgram.model.abstractModels.LinkedList;
import listTesterProgram.model.concrete.*;

import java.util.ArrayList;

public class ListCreator implements FactoryList {
    /**
     * Creates a linked list of the specified type.
     * @param type the type of linked list to create
     * @param <T> the type of elements in the linked list
     * @return a new linked list of the specified type
     */
    @Override
    public <T> LinkedList<T> createLinkedList(TypeLinkedList type) {
        switch (type) {
            case LINKED_LIST_WITH_TAIL:
                return new LinkedLinkedListWithTail<>();
            case LINKED_LIST_WITHOUT_TAIL:
                return new LinkedLinkedListWithoutTail<>();
            case DOUBLE_LINKED_LIST_WITH_TAIL:
                return new DoubleLinkedLinkedListWithTail<>();
            case DOUBLE_LINKED_LIST_WITHOUT_TAIL:
                return new DoubleLinkedLinkedListWithoutTail<>();
            default:
                throw new IllegalArgumentException("Invalid linked list type");
        }
    }
}
