package carsharing.domain;

import java.sql.Connection;
import java.sql.SQLException;

public class Database {
    private final H2Database h2Database;

    public Database(String path, String user, String password) {
        h2Database = new H2Database(path, user, password);
    }

    public void setup() {
        try (Connection con = h2Database.getConnection()) {
            try (var stmt = con.createStatement()) {
                con.setAutoCommit(true);
                stmt.executeUpdate("""
                        CREATE TABLE IF NOT EXISTS COMPANY (
                            ID INT PRIMARY KEY AUTO_INCREMENT,
                            NAME VARCHAR(255) NOT NULL UNIQUE
                        );
                        """);
            }
        } catch (SQLException e) {
            System.out.println("Error while creating table COMPANY");
            System.out.println(h2Database.getUrl());
        }
    }
}
