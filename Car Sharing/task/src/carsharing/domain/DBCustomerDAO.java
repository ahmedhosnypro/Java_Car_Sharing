package carsharing.domain;

import carsharing.Main;
import carsharing.model.Customer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DBCustomerDAO implements CustomerDAO {
    private final DbClient dbClient;

    private static final String CREATE_TABLE = """
            CREATE TABLE IF NOT EXISTS customer (
                id INTEGER PRIMARY KEY AUTO_INCREMENT,
                name VARCHAR UNIQUE NOT NULL,
                rented_car_id INTEGER,
                CONSTRAINT fk_rented_car FOREIGN KEY (rented_car_id)
                REFERENCES car(id)
            );
            """;

    private static final String FIND_ALL_CUSTOMERS = "SELECT * FROM customer;";

    private static final String SELECT = "SELECT * FROM customer WHERE id = %d";

    private static final String INSERT_DATA = "INSERT INTO customer(id, name) VALUES (%d, '%s');";

    private static final String RENT_CAR = "UPDATE customer SET rented_car_id = %d WHERE id = %d";

    private static final String RETURN_CAR = "UPDATE customer SET rented_car_id = NULL WHERE id = %d";

    private void setup() {
        dbClient.run(CREATE_TABLE);
    }

    public DBCustomerDAO(DbClient dbClient) {
        this.dbClient = dbClient;
        setup();
    }

    @Override
    public void add(Customer customer) {
        // determine if the customer already exists
        Optional<Customer> customerOptional = findById(customer.getId());
        if (customerOptional.isPresent()) {
            return;
        }
        // determine the next id
        List<Customer> customers = findAll();
        int nextId = customers.stream().mapToInt(Customer::getId).max().orElse(0) + 1;
        customer.setId(nextId);
        dbClient.run(String.format(INSERT_DATA, customer.getId(), customer.getName()));
    }

    @Override
    public Optional<Customer> findById(int id) {
        List<Customer> items = selectForList(String.format(SELECT, id));
        if (items.size() == 1) {
            return Optional.ofNullable(items.get(0));
        } else if (items.isEmpty()) {
            return Optional.empty();
        } else {
            throw new IllegalStateException("Query returned more than one object");
        }
    }

    @Override
    public List<Customer> findAll() {
        return selectForList(FIND_ALL_CUSTOMERS);
    }

    @Override
    public boolean rentCar(Customer customer, int carId) {
        var optCar = Main.DBManager.getDbCarDAO().findById(carId);
        if (optCar.isPresent()) {
            var car = optCar.get();
            if (!car.isRented()) {
                dbClient.run(String.format(RENT_CAR, carId, customer.getId()));
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean returnCar(Customer customer) {
        dbClient.run(String.format(RETURN_CAR, customer.getId()));
        return true;
    }

    private List<Customer> selectForList(String query) {
        List<Customer> customers = new ArrayList<>();

        try (Connection con = dbClient.getConnection();
             Statement statement = con.createStatement();
             ResultSet resultSetItem = statement.executeQuery(query)
        ) {
            con.setAutoCommit(true);
            while (resultSetItem.next()) {
                int id = resultSetItem.getInt("id");
                String name = resultSetItem.getString("name");
                Integer rentedCarId = resultSetItem.getInt("rented_car_id");
                if (resultSetItem.wasNull()) {
                    rentedCarId = null;
                }
                customers.add(new Customer(id, name, rentedCarId));
            }
            return customers;
        } catch (Exception e) {
            System.out.println("Error while executing statement: " + query + " " + e.getMessage());
        }
        return customers;
    }
}
