package carsharing.command;

import carsharing.Main;
import carsharing.model.Car;

import java.util.List;

public class CarList extends CliCommand {
    private final int companyId;

    public CarList(int companyId) {
        name = "Car list";
        this.companyId = companyId;
    }

    @Override
    public void run() {
        List<Car> cars = Main.DBManager.dbCarDAO.findAllByCompanyId(companyId);
        if (cars.isEmpty()) {
            System.out.println("The car list is empty!");
            return;
        }
        System.out.println("\nCar list:");
        for (int i = 0; i < cars.size(); i++) {
            System.out.println((i + 1) + ". " + cars.get(i).getName());
        }
    }
}
