package carsharing.command;

import carsharing.Main;
import carsharing.domain.CompanyDAO;
import carsharing.model.Company;

import java.util.List;
import java.util.Scanner;

public class CLI {
    private final CompanyDAO companyDAO;
    Menu mainMenu;
    Menu managerMenu;

    public CLI(CompanyDAO companyDAO) {
        this.companyDAO = companyDAO;

        mainMenu = new Menu(List.of(logAsManager()), false);

        managerMenu = new Menu(List.of(companyList(), createCompany()), true);
        managerMenu.setBackMenu(mainMenu);
    }

    public void run() {
        mainMenu.run();
    }

    Command logAsManager() {
        CommandRunnable enterManagerMenu = () -> managerMenu.run();
        return new Command("Log in as a manager", enterManagerMenu);
    }

    Command companyList() {
        CommandRunnable printCompanyList = () -> {
            List<Company> companies = companyDAO.findAll();
            if (companies.isEmpty()) {
                System.out.println("The company list is empty!");
                return;
            }
            System.out.println("Company list:");
            for (Company company : companies) {
                System.out.println(company.toString());
            }
        };
        return new Command("Company list", printCompanyList);
    }

    Command createCompany() {
        CommandRunnable createCompany = () -> {
            System.out.println("Enter the company name:");
            Scanner scanner = new Scanner(System.in);
            String companyName = scanner.nextLine();
            companyDAO.add(new Company(companyName));
            System.out.println("The company was created!");
        };
        return new Command("Create a company", createCompany);
    }
}
