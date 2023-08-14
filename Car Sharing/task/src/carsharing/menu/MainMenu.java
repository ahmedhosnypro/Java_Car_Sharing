package carsharing.menu;

import carsharing.command.CreateCustomer;
import carsharing.command.LogAsCustomer;
import carsharing.command.LogAsManager;

public class MainMenu extends Menu {
    private static MainMenu instance;

    private MainMenu(String header, boolean hasBack, Menu backMenu) {
        super(header, hasBack, backMenu);
        commands.add(LogAsManager.getInstance());
        commands.add(LogAsCustomer.getInstance());
        commands.add(CreateCustomer.getInstance());
    }

    public static MainMenu getInstance() {
        if (instance == null) {
            instance = new MainMenu("", false, null);
        }
        return instance;
    }
}
