package carsharing;

import carsharing.domain.DBManager;
import carsharing.menu.MainMenu;

import java.util.Scanner;

public class Main {
    public static final Scanner scanner = new Scanner(System.in);
    public static DBManager DBManager;

    public static void main(String[] args) {
        DBManager = new DBManager(args);
        MainMenu.getInstance().run();
    }
}