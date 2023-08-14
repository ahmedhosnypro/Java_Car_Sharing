package carsharing;

import carsharing.command.CLI;
import carsharing.domain.CompanyDAO;
import carsharing.domain.DBCompanyDAO;

import java.util.Scanner;

public class Main {

    public static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        CompanyDAO dbCompanyDAO = new DBCompanyDAO(dataBaseName(args), "", "");
        new CLI(dbCompanyDAO).run();
    }

    private static String dataBaseName(String... args) {
        if (args.length >= 2 && args[0].equals("-databaseFileName")) {
            return args[1];
        }
        return "carsharing";
    }
}