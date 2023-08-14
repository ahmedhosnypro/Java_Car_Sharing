package carsharing.menu;

import carsharing.Main;
import carsharing.model.Car;

import java.util.List;

public class CarMenu extends Menu {
    private final List<Car> cars;

    public CarMenu( List<Car> cars) {
        super("Car list:", true, ManagerMenu.getInstance());
        this.cars = cars;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("\n" + header);
            for (var car : cars) {
                System.out.println(car.getId() + ". " + car.getName());
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
            } else if (choice > 0 && choice <= cars.size()) {
                // todo: car menu
            } else {
                System.out.println("Wrong input");
            }
        }
    }
}
