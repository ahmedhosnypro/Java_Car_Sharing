package carsharing.command;

import carsharing.Main;

import java.util.List;

public class Menu {
    List<Command> commands;
    boolean hasBack;
    Menu backMenu;

    public Menu(List<Command> commands, boolean hasBack) {
        this.commands = commands;
        this.hasBack = hasBack;
    }

    public void setBackMenu(Menu backMenu) {
        this.backMenu = backMenu;
    }

    public void run() {
        while (true) {
            System.out.println();
            for (int i = 0; i < commands.size(); i++) {
                System.out.println((i + 1) + ". " + commands.get(i).getName());
            }
            if (hasBack) {
                System.out.println("0. Back");
            } else {
                System.out.println("0. Exit");
            }
            int choice = Main.scanner.nextInt();
            if (choice == 0 && hasBack) {
                backMenu.run();
                break;
            } else if (choice == 0) {
                System.exit(0);
            } else if (choice > 0 && choice <= commands.size()) {
                commands.get(choice - 1).run();
            } else {
                System.out.println("Wrong input");
            }
        }
    }
}
