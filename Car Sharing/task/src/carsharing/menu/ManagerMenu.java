package carsharing.menu;

import carsharing.command.CreateCompany;
import carsharing.command.ChooseCompany;

public class ManagerMenu extends Menu {
    private static ManagerMenu instance;

    public ManagerMenu() {
        super("", true, MainMenu.getInstance());
        commands.add(ChooseCompany.getInstance());
        commands.add(CreateCompany.getInstance());
    }

    public static ManagerMenu getInstance() {
        if (instance == null) {
            instance = new ManagerMenu();
        }
        return instance;
    }
}
