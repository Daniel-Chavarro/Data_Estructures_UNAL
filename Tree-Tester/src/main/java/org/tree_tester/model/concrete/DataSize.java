package org.tree_tester.model.concrete;

/**
 * Enumeration representing different data sizes for benchmark testing.
 */
public enum DataSize {

    /**
     * Small dataset - 100 elements.
     */
    SMALL(100, "Small"),

    /**
     * Medium dataset - 1,000 elements.
     */
    MEDIUM(1_000, "Medium"),

    /**
     * Large dataset - 10,000 elements.
     */
    LARGE(10_000, "Large"),

    /**
     * Extra large dataset - 100,000 elements.
     */
    EXTRA_LARGE(100_000, "Extra Large"),

    /**
     * Huge dataset - 1,000,000 elements.
     */
    HUGE(1_000_000, "Huge"),

    /**
     * Massive dataset - 10,000,000 elements.
     */
    MASSIVE(10_000_000, "Massive");


    private final int value;
    private final String description;

    /**
     * Constructs a DataSize with the specified value and description.
     *
     * @param value the numeric size value
     * @param description the human-readable description
     */
    DataSize(int value, String description) {
        this.value = value;
        this.description = description;
    }

    /**
     * Gets the numeric value of this data size.
     *
     * @return the size value
     */
    public int getValue() {
        return value;
    }

    /**
     * Gets the description of this data size.
     *
     * @return the description string
     */
    public String getDescription() {
        return description;
    }

    /**
     * Parses a string to a DataSize enum.
     *
     * @param input the input string (can be numeric or name)
     * @return the corresponding DataSize
     * @throws IllegalArgumentException if the input is invalid
     */
    public static DataSize fromString(String input) {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException("Input cannot be null or empty");
        }

        String trimmed = input.trim().toUpperCase().replace("_", "");

        // Try to parse as number first
        try {
            int value = Integer.parseInt(input.trim());
            for (DataSize size : values()) {
                if (size.value == value) {
                    return size;
                }
            }
            throw new IllegalArgumentException("Invalid data size: " + value);
        } catch (NumberFormatException e) {
            // Try to parse as enum name
            for (DataSize size : values()) {
                if (size.name().replace("_", "").equals(trimmed)) {
                    return size;
                }
            }
            throw new IllegalArgumentException("Invalid data size: " + input + ". Valid values: 100, 1000, 10000, 100000, 1000000, 10000000, 100000000 or SMALL, MEDIUM, LARGE, etc.");
        }
    }

    /**
     * Gets all available data sizes as a formatted string.
     *
     * @return formatted string of all available sizes
     */
    public static String getAvailableSizes() {
        StringBuilder sb = new StringBuilder();
        for (DataSize size : values()) {
            if (sb.length() > 0) sb.append(", ");
            sb.append(size.value).append(" (").append(size.description).append(")");
        }
        return sb.toString();
    }
}
