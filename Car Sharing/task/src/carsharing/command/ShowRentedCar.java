package carsharing.command;

import carsharing.Main;
import carsharing.model.Car;

public class ShowRentedCar extends CliCommand {
    private final int customerId;

    public ShowRentedCar(int customerId) {
        name = "My rented car";
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
        if (customer.getRentedCarId() == null) {
            System.out.println("You didn't rent a car!");
        } else {
            var optRentedCar = Main.DBManager.getDbCarDAO().findById(customer.getRentedCarId());
            if (optRentedCar.isPresent()) {
                Car rentedCar = optRentedCar.get();
                var optCompany = Main.DBManager.getDbCompanyDAO().findById(rentedCar.getCompanyId());
                if (optCompany.isPresent()) {
                    System.out.println("Your rented car:");
                    System.out.println(rentedCar.getName());
                    System.out.println("Company:");
                    System.out.println(optCompany.get().getName());
                } else {
                    System.out.println("Company not found!");
                }
            } else {
                System.out.println("You didn't rent a car!");
            }
        }
    }
}