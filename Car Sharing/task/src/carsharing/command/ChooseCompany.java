package carsharing.command;

import carsharing.Main;
import carsharing.menu.ChooseCompanyMenu;
import carsharing.menu.CompanyMenu;
import carsharing.menu.ManagerMenu;
import carsharing.model.Company;

import java.util.List;

public class ChooseCompany extends CliCommand {
    private static ChooseCompany instance;

    private ChooseCompany() {
        name = "Company list";
    }

    public static CliCommand getInstance() {
        if (instance == null) {
            instance = new ChooseCompany();
        }
        return instance;
    }

    @Override
    public void run() {
        ChooseCompanyMenu menu = new ChooseCompanyMenu(ManagerMenu.getInstance());
        Company company = menu.run();
        if (company != null) {
            new CompanyMenu(company).run();
        }
    }
}
