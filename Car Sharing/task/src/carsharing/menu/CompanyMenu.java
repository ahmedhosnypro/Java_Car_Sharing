package carsharing.menu;

import carsharing.command.CarList;
import carsharing.command.CreateCar;
import carsharing.model.Company;

public class CompanyMenu extends Menu<Company> {

    public CompanyMenu(Company company) {
        super("'" + company.getName() + "' company", true, ManagerMenu.getInstance());
        commands.add(new CarList(company.getId()));
        commands.add(new CreateCar(company.getId()));
    }
}