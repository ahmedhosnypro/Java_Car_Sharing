package carsharing.menu;

import carsharing.Main;
import carsharing.model.Customer;

import java.util.List;

public class ChooseCustomerMenu extends Menu<Customer> {
    private static ChooseCustomerMenu instance;

    public ChooseCustomerMenu() {
        super("Choose a customer:", true, MainMenu.getInstance());
    }

    public static ChooseCustomerMenu getInstance() {
        if (instance == null) {
            instance = new ChooseCustomerMenu();
        }
        return instance;
    }

    @Override
    public Customer run() {
        List<Customer> customers = Main.DBManager.getDbCustomerDAO().findAll();
        if (customers.isEmpty()) {
            System.out.println("The customer list is empty!");
            return null;
        }
        System.out.println("\n" + header);
        for (var customer : customers) {
            System.out.println(customer.getId() + ". " + customer.getName());
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
        } else if (choice > 0 && choice <= customers.size()) {
            return customers.get(choice - 1);
        } else {
            System.out.println("Wrong input");
        }
        return null;
    }
}
