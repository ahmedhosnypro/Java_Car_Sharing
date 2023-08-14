package carsharing.menu;

import carsharing.Main;
import carsharing.command.CliCommand;

import java.util.ArrayList;
import java.util.List;

public abstract class Menu {
    String header;
    List<CliCommand> commands = new ArrayList<>();
    boolean hasBack;
    Menu backMenu;

    Menu(String header, boolean hasBack, Menu backMenu) {
        this.header = header;
        this.hasBack = hasBack;
        this.backMenu = backMenu;
    }

    public void run() {
        while (true) {
            System.out.println("\n"+ header);
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
