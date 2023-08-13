package carsharing.domain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class H2Database {
    private static final String URL_PREFIX = "jdbc:h2:./src/carsharing/db/";
    private final String url;
    private final String user;
    private final String password;

    public H2Database(String path, String user, String password) {
        this.url = URL_PREFIX + path;
        this.user = user;
        this.password = password;
    }

    Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public String getUrl() {
        return url;
    }
}