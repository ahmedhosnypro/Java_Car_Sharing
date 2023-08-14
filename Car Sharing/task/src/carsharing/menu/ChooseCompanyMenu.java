package carsharing.menu;

import carsharing.Main;
import carsharing.model.Company;

import java.util.List;

public class ChooseCompanyMenu extends Menu {
    private final List<Company> companies;

    public ChooseCompanyMenu(List<Company> companies) {
        super("Choose the company:", true, ManagerMenu.getInstance());
        this.companies = companies;
    }

    @Override
    public void run() {
        while (true) {
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
                break;
            } else if (choice == 0) {
                System.exit(0);
            } else if (choice > 0 && choice <= companies.size()) {
                new CompanyMenu(companies.get(choice - 1)).run();
                break;
            } else {
                System.out.println("Wrong input");
            }
        }
    }
}