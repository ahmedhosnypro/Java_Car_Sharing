package carsharing.domain;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class DBManager {
    public CompanyDAO dbCompanyDAO;
    public CarDAO dbCarDAO;

    public DBManager(String... args) {
        DbClient dbClient = new DbClient(dataBaseName(args), "", "");
        dbCompanyDAO = new DBCompanyDAO(dbClient);
        dbCarDAO = new DBCarDAO(dbClient);
    }

    private static String dataBaseName(String... args) {
        String fileName;
        if (args.length >= 2 && args[0].equals("-databaseFileName")) {
            fileName = args[1];
        } else {
            fileName = "carsharing";
        }

        return fileName;
    }
}
