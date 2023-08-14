package carsharing.menu;

import carsharing.command.RentCar;
import carsharing.command.ReturnCar;
import carsharing.command.ShowRentedCar;
import carsharing.model.Customer;

public class CustomerMenu extends Menu<Customer> {
    public CustomerMenu(Customer customer) {
        super(" ", true, ChooseCustomerMenu.getInstance());
        commands.add(new RentCar(customer.getId()));
        commands.add(new ReturnCar(customer.getId()));
        commands.add(new ShowRentedCar(customer.getId()));
    }
}
