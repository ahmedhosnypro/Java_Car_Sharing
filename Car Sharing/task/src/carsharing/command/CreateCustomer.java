package carsharing.command;

import carsharing.Main;
import carsharing.model.Customer;

import java.util.Scanner;

public class CreateCustomer extends CliCommand{
    private static CreateCustomer instance;

    private CreateCustomer() {
        name = "Create a customer";
    }

    public static CliCommand getInstance() {
        if (instance == null) {
            instance = new CreateCustomer();
        }
        return instance;
    }

    @Override
    public void run() {
        System.out.println("Enter the customer name:");
        Scanner scanner = new Scanner(System.in);
        String customerName = scanner.nextLine();
        Main.DBManager.getDbCustomerDAO().add(new Customer(customerName));
        System.out.println("The customer was added!");
    }
}
