package carsharing.domain;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class DBManager {
    private final CompanyDAO dbCompanyDAO;
    private final CarDAO dbCarDAO;
    private final CustomerDAO dbCustomerDAO;

    public DBManager(String... args) {
        DbClient dbClient = new DbClient(dataBaseName(args), "", "");
        dbCompanyDAO = new DBCompanyDAO(dbClient);
        dbCarDAO = new DBCarDAO(dbClient);
        dbCustomerDAO = new DBCustomerDAO(dbClient);
    }

    private static String dataBaseName(String... args) {
        String fileName;
        if (args.length >= 2 && args[0].equals("-databaseFileName")) {
            fileName = args[1];
        } else {
            fileName = "carsharing";
        }

        String prefix = "./src/carsharing/db/";

        // delete old h2 database files
//        List<File> files = List.of(
//                new File(prefix + fileName + ".mv.db"),
//                new File(prefix + fileName + ".trace.db")
//        );
//        for (var file : files) {
//            try {
//                Files.deleteIfExists(file.toPath());
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }

        return fileName;
    }

    public CompanyDAO getDbCompanyDAO() {
        return dbCompanyDAO;
    }

    public CarDAO getDbCarDAO() {
        return dbCarDAO;
    }

    public CustomerDAO getDbCustomerDAO() {
        return dbCustomerDAO;
    }
}
