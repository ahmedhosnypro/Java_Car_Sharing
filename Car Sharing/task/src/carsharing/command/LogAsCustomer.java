package carsharing.command;

import carsharing.menu.ChooseCustomerMenu;
import carsharing.menu.CustomerMenu;
import carsharing.model.Customer;

public class LogAsCustomer extends CliCommand {

    private static LogAsCustomer instance;

    private LogAsCustomer() {
        name = "Log in as a customer";
    }

    public static CliCommand getInstance() {
        if (instance == null) {
            instance = new LogAsCustomer();
        }
        return instance;
    }

    @Override
    public void run() {
        Customer customer = ChooseCustomerMenu.getInstance().run();
        if (customer != null) {
            new CustomerMenu(customer).run();
        }
    }
}
