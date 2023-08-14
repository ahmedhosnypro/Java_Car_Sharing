package carsharing.menu;

import carsharing.Main;
import carsharing.model.Company;

import java.util.List;

public class ChooseCompanyMenu extends Menu<Company> {
    public ChooseCompanyMenu(Menu backMenu) {
        super("Choose the company:", true, backMenu);
    }

    @Override
    public Company run() {
        List<Company> companies = Main.DBManager.getDbCompanyDAO().findAll();
        if (companies.isEmpty()) {
            System.out.println("The company list is empty!");
            return null;
        }

        System.out.println("\n" + header);
        for (var company : companies) {
            System.out.println(company.getId() + ". " + company.getName());
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
        } else if (choice > 0 && choice <= companies.size()) {
            return companies.get(choice - 1);
        } else {
            System.out.println("Wrong input");
        }

        return null;
    }
}