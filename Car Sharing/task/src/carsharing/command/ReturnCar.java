package carsharing.command;

import carsharing.Main;

public class ReturnCar extends CliCommand {
    private final int customerId;

    public ReturnCar(int customerId) {
        name = "Return a rented car";
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
            var optCar = Main.DBManager.getDbCarDAO().findById(customer.getRentedCarId());
            if (optCar.isPresent()) {
                var car = optCar.get();
                car.setRented(false);
                Main.DBManager.getDbCarDAO().updateRented(car);
                customer.setRentedCarId(null);
                Main.DBManager.getDbCustomerDAO().returnCar(customer);
                System.out.println("You've returned a rented car!");
            } else {
                System.out.println("You didn't rent a car!");
            }
        }
    }
}
