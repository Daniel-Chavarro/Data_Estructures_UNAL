package org.tree_tester.model.concrete;

/**
 * Enumeration representing different types of data structures available in the system.
 * Each type has an associated human-readable description.
 */
public enum TreeType {

    /**
     * Binary Search Tree data structure.
     */
    BST("Binary Search Tree"),

    /**
     * AVL Tree (self-balancing binary search tree) data structure.
     */
    AVL("AVL Tree"),

    /**
     * Min Heap data structure.
     */
    MIN_HEAP("Min Heap"),

    /**
     * Max Heap data structure.
     */
    MAX_HEAP("Max Heap");

    private final String DESCRIPTION;

    /**
     * Constructs a TreeType with the specified description.
     *
     * @param description the human-readable description of the tree type
     */
    TreeType(String description) {
        this.DESCRIPTION = description;
    }

    /**
     * Gets the description of this tree type.
     *
     * @return the description string
     */
    public String getDescription() {
        return DESCRIPTION;
    }
}
