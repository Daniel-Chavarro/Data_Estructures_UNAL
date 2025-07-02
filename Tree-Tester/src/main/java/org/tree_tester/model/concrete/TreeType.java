package org.tree_tester.model.concrete;

public enum TreeType {

    BST("Binary Search Tree"),
    AVL("AVL Tree"),
    MIN_HEAP("Min Heap"),
    MAX_HEAP("Max Heap");


    private final String DESCRIPTION;

    TreeType(String description) {
        this.DESCRIPTION = description;
    }

    public String getDescription() {
        return DESCRIPTION;
    }
}
