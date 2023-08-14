package carsharing.command;

import carsharing.menu.ManagerMenu;

public class LogAsManager extends CliCommand {
    private static LogAsManager instance;

    private LogAsManager() {
        name = "Log in as a manager";
    }

    public static CliCommand getInstance() {
        if (instance == null) {
            instance = new LogAsManager();
        }
        return instance;
    }

    @Override
    public void run() {
        ManagerMenu.getInstance().run();
    }
}
