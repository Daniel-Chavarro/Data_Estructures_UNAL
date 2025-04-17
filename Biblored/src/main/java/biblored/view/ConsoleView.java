package biblored.view;

import java.util.Scanner;

public class ConsoleView {
    private final Scanner scanner;

    public ConsoleView() {
        this.scanner = new Scanner(System.in);
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

    public String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public Integer getIntegerInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                printMessage("Invalid input. Please enter a valid integer.");
            }
        }
    }




}
