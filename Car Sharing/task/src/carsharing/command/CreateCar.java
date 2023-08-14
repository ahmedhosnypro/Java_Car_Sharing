package carsharing.command;

import carsharing.Main;
import carsharing.model.Car;

import java.util.Scanner;

public class CreateCar extends CliCommand {
    private final int companyId;

    public CreateCar(int companyId) {
        name = "Create a car";
        this.companyId = companyId;
    }

    @Override
    public void run() {
        System.out.println("\nEnter the car name:");
        Scanner scanner = new Scanner(System.in);
        String carName = scanner.nextLine();
        Main.DBManager.getDbCarDAO().add(new Car(carName, companyId));
        System.out.println("The car was created!");
    }
}
