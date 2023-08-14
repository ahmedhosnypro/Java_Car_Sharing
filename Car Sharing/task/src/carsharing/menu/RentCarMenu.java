package carsharing.menu;

import carsharing.Main;
import carsharing.model.Car;

import java.util.List;

public class RentCarMenu extends Menu<Car> {
    List<Car> cars;

    public RentCarMenu(List<Car> cars, Menu backMenu) {
        super("Choose a car:", true, backMenu);
        this.cars = cars;
    }

    @Override
    public Car run() {
        System.out.println("\n" + header);
        for (int i = 0; i < cars.size(); i++) {
            System.out.println((i + 1) + ". " + cars.get(i).getName());
        }
        if (hasBack) {
            System.out.println("0. Back");
        } else {
            System.out.println("0. Exit");
        }
        int choice = Main.scanner.nextInt();
        if (choice == 0 && hasBack) {
            backMenu.run();
        } else if (choice == 0) {
            System.exit(0);
        } else if (choice > 0 && choice <= cars.size()) {
            return cars.get(choice - 1);
        } else {
            System.out.println("Wrong input");
        }

        return null;
    }
}
