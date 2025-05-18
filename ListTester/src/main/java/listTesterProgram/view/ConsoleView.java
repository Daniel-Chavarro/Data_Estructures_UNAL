package listTesterProgram.view;

import java.util.Scanner;

/**
 * View class for the LinkedList tester application
 * Handles user interaction through the console
 */
public class ConsoleView {
    private Scanner sc;

    /**
     * Constructor for ConsoleView
     * Initializes a new Scanner for user input
     */
    public ConsoleView() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Prints a message to the console
     *
     * @param message the message to be printed
     */
    public void print(String message) {
        System.out.println(message);
    }

    /**
     * Prints an error message to the console
     *
     * @param message the error message to be printed
     */
    public void printError(String message) {
        System.err.println(message);
    }

    /**
     * Gets an integer input from the user
     *
     * @param message the prompt message to display
     * @return the integer input by the user
     */
    public int getInteger(String message) {
        System.out.print(message);
        while (!sc.hasNextInt()) {
            sc.next();
            System.out.print(message);
        }
        int value = sc.nextInt();
        sc.nextLine();
        return value;
    }

    /**
     * Gets a string input from the user
     *
     * @param message the prompt message to display
     * @return the string input by the user
     */
    public String getString(String message) {
        System.out.print(message);
        return sc.nextLine();
    }

    /**
     * Gets the next line of input from the user
     *
     * @return the next line of input
     */
    public String getNextLine() {
        return sc.nextLine();
    }

    /**
     * Displays a horizontal line for formatting
     */
    public void displayHorizontalLine() {
        System.out.println("----------------------------------------");
    }

    /**
     * Displays a title with formatting
     *
     * @param title the title to display
     */
    public void displayTitle(String title) {
        displayHorizontalLine();
        System.out.println(title);
        displayHorizontalLine();
    }

    /**
     * Closes the scanner when the application is done
     *
     */
    public void close() {
        sc.close();
    }
}
