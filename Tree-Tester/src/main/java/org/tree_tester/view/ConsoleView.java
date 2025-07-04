package org.tree_tester.view;

import java.util.Scanner;

/**
 * Console view class that provides methods for user interaction through the console.
 * Handles input validation and output formatting for the benchmark application.
 */
public class ConsoleView {
    private Scanner scanner;

    /**
     * Constructs a new ConsoleView with a Scanner for reading user input.
     */
    public ConsoleView() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Prints a message to the console followed by a newline.
     *
     * @param message the message to print
     */
    public void println(String message) {
        System.out.println(message);
    }

    /**
     * Prints a message to the console without a newline.
     *
     * @param message the message to print
     */
    public void print(String message) {
        System.out.print(message);
    }

    /**
     * Reads an integer from the console with input validation.
     * Continues prompting until a valid integer is entered.
     *
     * @param message the prompt message to display
     * @return the integer value entered by the user
     */
    public int readInt(String message) {
        print(message);
        while (!scanner.hasNextInt()) {
            print("Invalid input. " + message);
            scanner.next();
        }
        int value = scanner.nextInt();
        scanner.nextLine();
        return value;
    }

    /**
     * Reads a non-empty string from the console with input validation.
     * Continues prompting until a non-empty string is entered.
     *
     * @param message the prompt message to display
     * @return the string value entered by the user
     */
    public String readString(String message) {
        print(message);
        return scanner.nextLine();
    }
}
