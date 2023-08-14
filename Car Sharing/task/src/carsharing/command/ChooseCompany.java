package carsharing.command;

import carsharing.Main;
import carsharing.menu.ChooseCompanyMenu;
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
        List<Company> companies = Main.DBManager.dbCompanyDAO.findAll();
        if (companies.isEmpty()) {
            System.out.println("The company list is empty!");
            return;
        }
        ChooseCompanyMenu menu = new ChooseCompanyMenu(companies);
        menu.run();
    }
}
