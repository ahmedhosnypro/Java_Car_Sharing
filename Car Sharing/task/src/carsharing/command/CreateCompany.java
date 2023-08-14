package carsharing.command;

import carsharing.Main;
import carsharing.model.Company;

import java.util.Scanner;

public class CreateCompany extends CliCommand {
    private static CreateCompany instance;

    private CreateCompany() {
        name = "Create a company";
    }

    public static CliCommand getInstance() {
        if (instance == null) {
            instance = new CreateCompany();
        }
        return instance;
    }

    @Override
    public void run() {
        System.out.println("Enter the company name:");
        Scanner scanner = new Scanner(System.in);
        String companyName = scanner.nextLine();
        Main.DBManager.dbCompanyDAO.add(new Company(companyName));
        System.out.println("The company was created!");
    }
}
