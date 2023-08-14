package carsharing.domain;

import carsharing.model.Company;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DbClient {
    private final String url;
    private final String user;
    private final String password;

    private static final String URL_PREFIX = "jdbc:h2:./src/carsharing/db/";

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

    public Optional<Company> select(String query) {
        List<Company> companies = selectForList(query);
        if (companies.size() == 1) {
            return Optional.ofNullable(companies.get(0));
        } else if (companies.isEmpty()) {
            return Optional.empty();
        } else {
            throw new IllegalStateException("Query returned more than one object");
        }
    }

    public List<Company> selectForList(String query) {
        List<Company> developers = new ArrayList<>();

        try (Connection con = getConnection();
             Statement statement = con.createStatement();
             ResultSet resultSetItem = statement.executeQuery(query)
        ) {
            con.setAutoCommit(true);
            while (resultSetItem.next()) {
                // Retrieve column values
                int id = resultSetItem.getInt("id");
                String name = resultSetItem.getString("name");
                Company company = new Company(id, name);
                developers.add(company);
            }

            return developers;
        } catch (SQLException e) {
            System.out.println("Error while executing statement: " + query);
        }

        return developers;
    }

    Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}