package listTesterProgram.controller;

import listTesterProgram.controller.benchmark.Benchmark;
import listTesterProgram.model.abstractModels.LinkedList;
import listTesterProgram.model.concrete.Node;
import listTesterProgram.model.concrete.TypeLinkedList;
import listTesterProgram.model.creators.ListCreator;
import listTesterProgram.view.ConsoleView;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Controller class for the LinkedList tester application
 * Connects the model (LinkedList implementations) with the view (ConsoleView)
 */
public class ListController {
    private final ConsoleView view;
    private final ListCreator listCreator;
    private final Benchmark benchmarker;
    private LinkedList<Integer> currentIntegerList;
    private TypeLinkedList currentListType;

    /**
     * Constructor for ListController
     *
     * @param view the view to use for user interaction
     */
    public ListController(ConsoleView view) {
        this.view = view;
        this.listCreator = new ListCreator();
        this.benchmarker = new Benchmark();
        this.currentListType = TypeLinkedList.LINKED_LIST_WITH_TAIL; // Default list type
        this.currentIntegerList = listCreator.createLinkedList(currentListType);
    }

    /**
     * Run the main application loop
     */
    public void run() {
        boolean exit = false;
        while (!exit) {
            displayMainMenu();
            int choice = getIntegerInput("Enter your choice: ");

            switch (choice) {
                case 1:
                    selectListType();
                    break;
                case 2:
                    manipulateList();
                    break;
                case 3:
                    runBenchmarks();
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    view.printError("Invalid choice. Please try again.");
            }
        }
        view.print("Thank you for using the LinkedList Tester!");
    }

    /**
     * Display the main menu
     */
    private void displayMainMenu() {
        view.displayTitle("LinkedList Tester");
        view.print("Current List Type: " + currentListType);
        view.print("1. Select List Type");
        view.print("2. Manipulate List");
        view.print("3. Run Benchmarks");
        view.print("4. Exit");
    }

    /**
     * Allow the user to select a list type
     */
    private void selectListType() {
        view.displayTitle("Select List Type");
        TypeLinkedList[] types = TypeLinkedList.values();
        for (int i = 0; i < types.length; i++) {
            view.print((i + 1) + ". " + types[i]);
        }

        int choice = getIntegerInput("Enter your choice: ");
        if (choice >= 1 && choice <= types.length) {
            currentListType = types[choice - 1];
            currentIntegerList = listCreator.createLinkedList(currentListType);
            view.print("List type changed to: " + currentListType);
        } else {
            view.printError("Invalid choice. List type not changed.");
        }
    }

    /**
     * Allow the user to manipulate the current list
     */
    private void manipulateList() {
        boolean back = false;
        while (!back) {
            view.displayTitle("Manipulate List");
            view.print("Current List Type: " + currentListType);
            view.print("Current List: " + currentIntegerList);
            view.print("1. Push Front");
            view.print("2. Push Back");
            view.print("3. Pop Front");
            view.print("4. Pop Back");
            view.print("5. Find");
            view.print("6. Erase");
            view.print("7. Add After");
            view.print("8. Add Before");
            view.print("9. Clear List");
            view.print("10. Back to Main Menu");

            int choice = getIntegerInput("Enter your choice: ");

            switch (choice) {
                case 1:
                    pushFront();
                    break;
                case 2:
                    pushBack();
                    break;
                case 3:
                    popFront();
                    break;
                case 4:
                    popBack();
                    break;
                case 5:
                    find();
                    break;
                case 6:
                    erase();
                    break;
                case 7:
                    addAfter();
                    break;
                case 8:
                    addBefore();
                    break;
                case 9:
                    clearList();
                    break;
                case 10:
                    back = true;
                    break;
                default:
                    view.printError("Invalid choice. Please try again.");
            }
        }
    }

    /**
     * Run benchmarks on the LinkedList implementations
     */
    private void runBenchmarks() {
        boolean back = false;
        while (!back) {
            view.displayTitle("Run Benchmarks");
            view.print("1. Run Benchmarks");
            view.print("2. Export Results to CSV");
            view.print("3. Find Fastest Implementations");
            view.print("4. Back to Main Menu");

            int choice = getIntegerInput("Enter your choice: ");

            switch (choice) {
                case 1:
                    runBenchmarksOperation();
                    break;
                case 2:
                    exportResults();
                    break;
                case 3:
                    findFastestImplementations();
                    break;
                case 4:
                    back = true;
                    break;
                default:
                    view.printError("Invalid choice. Please try again.");
            }
        }
    }

    /**
     * Run benchmarks on all LinkedList implementations
     */
    private void runBenchmarksOperation() {
        view.displayTitle("Benchmarks");
        view.print("Running benchmarks on all LinkedList implementations...");

        List<Benchmark.Result> results = benchmarker.runBenchmarks();

        view.print("\nBenchmark Results:");
        benchmarker.printResults(results);

        getStringInput("\nPress Enter to continue...");
    }

    /**
     * Export benchmark results to CSV file
     */
    private void exportResults() {
        view.displayTitle("Export Benchmark Results");

        try {
            File resultsDir = new File("results");
            if (!resultsDir.exists()) {
                resultsDir.mkdir();
                view.print("Created results directory");
            }

            List<Benchmark.Result> results = benchmarker.runBenchmarks();

            String csvFilePath = "results/benchmark_results.csv";
            benchmarker.exportToCSV(results, csvFilePath);

            view.print("Benchmark results exported to:");
            view.print("- " + csvFilePath);

            view.print("\nResults exported successfully!");
        } catch (IOException e) {
            view.printError("Error exporting results: " + e.getMessage());
        }

        getStringInput("\nPress Enter to continue...");
    }

    /**
     * Find the fastest implementations for each operation
     */
    private void findFastestImplementations() {
        view.displayTitle("Fastest Implementations");
        view.print("Analyzing benchmark results...");

        List<Benchmark.Result> results = benchmarker.runBenchmarks();

        String fastestImplementations = benchmarker.findFastestImplementations(results);
        view.print(fastestImplementations);

        getStringInput("\nPress Enter to continue...");
    }

    /**
     * Push a value to the front of the current list
     */
    private void pushFront() {
        int value = getIntegerInput("Enter value to push front: ");
        currentIntegerList.pushFront(value);
        view.print("Value pushed to front: " + value);
    }

    /**
     * Push a value to the back of the current list
     */
    private void pushBack() {
        int value = getIntegerInput("Enter value to push back: ");
        currentIntegerList.pushBack(value);
        view.print("Value pushed to back: " + value);
    }

    /**
     * Pop a value from the front of the current list
     */
    private void popFront() {
        try {
            int value = currentIntegerList.popFront();
            view.print("Value popped from front: " + value);
        } catch (Exception e) {
            view.printError("Error: " + e.getMessage());
        }
    }

    /**
     * Pop a value from the back of the current list
     */
    private void popBack() {
        try {
            int value = currentIntegerList.popBack();
            view.print("Value popped from back: " + value);
        } catch (Exception e) {
            view.printError("Error: " + e.getMessage());
        }
    }

    /**
     * Find a value in the current list
     */
    private void find() {
        int value = getIntegerInput("Enter value to find: ");
        try {
            Node<Integer> node = currentIntegerList.find(value);
            if (node != null) {
                view.print("Value found: " + node.getValue());
            } else {
                view.print("Value not found: " + value);
            }
        } catch (Exception e) {
            view.printError("Error: " + e.getMessage());
        }
    }

    /**
     * Erase a value from the current list
     */
    private void erase() {
        int value = getIntegerInput("Enter value to erase: ");
        try {
            currentIntegerList.erase(value);
            view.print("Value erased: " + value);
        } catch (Exception e) {
            view.printError("Error: " + e.getMessage());
        }
    }

    /**
     * Add a value after a node in the current list
     */
    private void addAfter() {
        int targetValue = getIntegerInput("Enter target value (to add after): ");
        int newValue = getIntegerInput("Enter new value to add: ");

        try {
            Node<Integer> node = currentIntegerList.find(targetValue);
            if (node != null) {
                currentIntegerList.addAfter(node, newValue);
                view.print("Value " + newValue + " added after " + targetValue);
            } else {
                view.printError("Target value not found: " + targetValue);
            }
        } catch (Exception e) {
            view.printError("Error: " + e.getMessage());
        }
    }

    /**
     * Add a value before a node in the current list
     */
    private void addBefore() {
        int targetValue = getIntegerInput("Enter target value (to add before): ");
        int newValue = getIntegerInput("Enter new value to add: ");

        try {
            Node<Integer> node = currentIntegerList.find(targetValue);
            if (node != null) {
                currentIntegerList.addBefore(node, newValue);
                view.print("Value " + newValue + " added before " + targetValue);
            } else {
                view.printError("Target value not found: " + targetValue);
            }
        } catch (Exception e) {
            view.printError("Error: " + e.getMessage());
        }
    }

    /**
     * Clear the current list
     */
    private void clearList() {
        while (!currentIntegerList.isEmpty()) {
            try {
                currentIntegerList.popFront();
            } catch (Exception e) {
                break;
            }
        }
        view.print("List cleared.");
    }

    /**
     * Get an integer input from the user
     *
     * @param prompt the prompt to display
     * @return the integer input
     */
    private int getIntegerInput(String prompt) {
        return view.getInteger(prompt);
    }

    /**
     * Get a string input from the user
     *
     * @param prompt the prompt to display
     * @return the string input
     */
    private String getStringInput(String prompt) {
        return view.getString(prompt);
    }
}
