package listTesterProgram.controller;

import listTesterProgram.view.ConsoleView;

/**
 * Main application class for the LinkedList Tester
 * Initializes the MVC components and starts the application
 */
public class ListTesterApp {

    /**
     * Main method to start the application
     *
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        ConsoleView view = new ConsoleView();

        view.displayTitle("Welcome to LinkedList Tester");
        view.print("This application allows you to test and benchmark different LinkedList implementations.");
        view.print("You can export benchmark results to CSV/JSON files for visualization.");
        view.print("After exporting, use the 'visualize_results.py' script to generate charts.");

        ListController controller = new ListController(view);

        try {
            controller.run();
        } catch (Exception e) {
            view.printError("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {
            view.close();
        }
    }
}
