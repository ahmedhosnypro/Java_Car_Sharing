package carsharing.command;

import carsharing.Main;
import carsharing.menu.ChooseCompanyMenu;
import carsharing.menu.ChooseCustomerMenu;
import carsharing.menu.CustomerMenu;
import carsharing.menu.RentCarMenu;
import carsharing.model.Car;
import carsharing.model.Company;

import java.util.List;

public class RentCar extends CliCommand {
    private final int customerId;

    public RentCar(int customerId) {
        name = "Rent a car";
        this.customerId = customerId;
    }

    @Override
    public void run() {
        var optCustomer = Main.DBManager.getDbCustomerDAO().findById(customerId);
        if (optCustomer.isEmpty()) {
            System.out.println("Customer not found!");
            return;
        }
        var customer = optCustomer.get();
        if (customer.getRentedCarId() != null) {
            System.out.println("You've already rented a car!");
            return;
        }

        Company company = new ChooseCompanyMenu(new CustomerMenu(customer)).run();
        if (company == null) {
            return;
        }

        List<Car> availableCars = company.getAvailableCars();
        if (availableCars.isEmpty()) {
            System.out.println("No available cars in the '" + company.getName() + "' company");
            return;
        }

        Car car = new RentCarMenu(availableCars, new CustomerMenu(customer)).run();
        if (car == null) {
            return;
        }

        boolean rented = Main.DBManager.getDbCustomerDAO().rentCar(customer, car.getId());

        if (rented) {
            System.out.println("You rented '" + car.getName() + "'");
        }
    }
}
