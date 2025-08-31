package org.tree_tester.model.concrete;

/**
 * Enumeration representing different degrees (number of children) for D-ary heaps.
 */
public enum HeapDegree {

    /**
     * Binary heap (2 children per node).
     */
    BINARY(2, "Binary"),

    /**
     * Ternary heap (3 children per node).
     */
    TERNARY(3, "Ternary"),

    /**
     * Quaternary heap (4 children per node).
     */
    QUATERNARY(4, "Quaternary"),

    /**
     * Quinary heap (5 children per node).
     */
    QUINARY(5, "Quinary");

    private final int value;
    private final String description;

    /**
     * Constructs a HeapDegree with the specified value and description.
     *
     * @param value the numeric degree value
     * @param description the human-readable description
     */
    HeapDegree(int value, String description) {
        this.value = value;
        this.description = description;
    }

    /**
     * Gets the numeric value of this degree.
     *
     * @return the degree value
     */
    public int getValue() {
        return value;
    }

    /**
     * Gets the description of this degree.
     *
     * @return the description string
     */
    public String getDescription() {
        return description;
    }

    /**
     * Parses a string to a HeapDegree enum.
     *
     * @param input the input string (can be numeric or name)
     * @return the corresponding HeapDegree
     * @throws IllegalArgumentException if the input is invalid
     */
    public static HeapDegree fromString(String input) {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException("Input cannot be null or empty");
        }

        String trimmed = input.trim().toUpperCase();

        // Try to parse as number first
        try {
            int value = Integer.parseInt(trimmed);
            for (HeapDegree degree : values()) {
                if (degree.value == value) {
                    return degree;
                }
            }
            throw new IllegalArgumentException("Invalid degree value: " + value);
        } catch (NumberFormatException e) {
            // Try to parse as enum name
            try {
                return valueOf(trimmed);
            } catch (IllegalArgumentException ex) {
                throw new IllegalArgumentException("Invalid degree: " + input + ". Valid values: 2, 3, 4, 5 or BINARY, TERNARY, QUATERNARY, QUINARY");
            }
        }
    }
}
