package carsharing;

import carsharing.domain.Database;

public class Main {

    public static void main(String[] args) {
        new Database(dataBaseName(args), "", "").setup();
    }

    private static String dataBaseName(String... args) {
        if (args.length >= 2 && args[0].equals("-databaseFileName")) {
            return args[1];
        }
        return "carsharing";
    }
}