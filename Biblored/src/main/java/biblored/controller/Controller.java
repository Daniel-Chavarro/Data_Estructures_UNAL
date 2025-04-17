package biblored.controller;

import biblored.view.ConsoleView;

public class Controller {
    private ConsoleView console;

    public Controller() {
        this.console = new ConsoleView();
    }

    public ConsoleView getConsole() {
        return console;
    }

    public void setConsole(ConsoleView console) {
        this.console = console;
    }
}
