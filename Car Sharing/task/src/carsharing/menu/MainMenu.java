package carsharing.menu;

import carsharing.command.LogAsManager;

public class MainMenu extends Menu {
    public static MainMenu instance;

    private MainMenu(String header, boolean hasBack, Menu backMenu) {
        super(header, hasBack, backMenu);
        commands.add(LogAsManager.getInstance());
    }

    public static MainMenu getInstance() {
        if (instance == null) {
            instance = new MainMenu("", false, null);
        }
        return instance;
    }
}
