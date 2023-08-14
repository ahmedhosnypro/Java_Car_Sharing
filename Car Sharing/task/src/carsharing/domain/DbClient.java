package carsharing.domain;

import java.sql.*;

public class DbClient {
    private final String url;
    private final String user;
    private final String password;

    protected static final String URL_PREFIX = "jdbc:h2:./src/carsharing/db/";

    public DbClient(String url, String user, String password) {
        this.url = URL_PREFIX + url;
        this.user = user;
        this.password = password;
    }


    public void run(String str) {
        try (Connection con = getConnection(); // Statement creation
             Statement statement = con.createStatement()
        ) {
            con.setAutoCommit(true);
            statement.executeUpdate(str); // Statement execution
        } catch (SQLException e) {
            System.out.println("Error while executing statement: " + str + " " + e.getMessage());
        }
    }

    Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}