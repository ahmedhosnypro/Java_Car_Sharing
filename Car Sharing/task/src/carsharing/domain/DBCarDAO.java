package carsharing.domain;

import carsharing.model.Car;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DBCarDAO implements CarDAO {
    private final DbClient dbClient;

    private static final String CREATE_TABLE_CAR = """
            CREATE TABLE IF NOT EXISTS car (
                id INT PRIMARY KEY AUTO_INCREMENT,
                name VARCHAR UNIQUE NOT NULL,
                rented BOOLEAN DEFAULT FALSE,
                company_id INT NOT NULL,
                CONSTRAINT fk_company FOREIGN KEY (company_id)
                REFERENCES COMPANY(id)
            );
            """;

    private static final String FIND_ALL_CARS = "SELECT * FROM car;";

    // car
    private static final String SELECT = "SELECT * FROM car WHERE id = %d";
    private static final String INSERT_DATA = "INSERT INTO car (id, name, company_id) VALUES (%d, '%s', %d)";
    private static final String UPDATE_RENTED = "UPDATE car SET rented = %b WHERE id = %d";

    public DBCarDAO(DbClient dbClient) {
        this.dbClient = dbClient;
        setup();
    }

    public void add(Car car) {
        // determine if the company already exists
        Optional<Car> carOptional = findById(car.getId());
        if (carOptional.isPresent()) {
            return;
        }
        // determine the next id
        List<Car> cars = findAll();
        int nextId = cars.stream().mapToInt(Car::getId).max().orElse(0) + 1;
        car.setId(nextId);
        dbClient.run(String.format(INSERT_DATA, car.getId(), car.getName(), car.getCompanyId()));
    }

    public void updateRented(Car car) {
        dbClient.run(String.format(UPDATE_RENTED, car.isRented(), car.getId()));
    }

    public Optional<Car> findById(int id) {
        List<Car> items = selectForList(String.format(SELECT, id));
        if (items.size() == 1) {
            return Optional.ofNullable(items.get(0));
        } else if (items.isEmpty()) {
            return Optional.empty();
        } else {
            throw new IllegalStateException("Query returned more than one object");
        }
    }

    @Override
    public List<Car> findAll() {
        return selectForList(FIND_ALL_CARS);
    }

    @Override
    public List<Car> findAllByCompanyId(int id) {
        return selectForList(String.format("SELECT * FROM car WHERE company_id = %d", id));
    }

    public void setup() {
        dbClient.run(CREATE_TABLE_CAR);
    }

    private List<Car> selectForList(String query) {
        List<Car> cars = new ArrayList<>();

        try (Connection con = dbClient.getConnection();
             Statement statement = con.createStatement();
             ResultSet resultSetItem = statement.executeQuery(query)
        ) {
            con.setAutoCommit(true);
            while (resultSetItem.next()) {
                int id = resultSetItem.getInt("id");
                String name = resultSetItem.getString("name");
                boolean rented = resultSetItem.getBoolean("rented");
                int companyId = resultSetItem.getInt("company_id");
                cars.add(new Car(id, name, companyId, rented));
            }
            return cars;
        } catch (Exception e) {
            System.out.println("Error while executing statement: " + query + " " + e.getMessage());
        }
        return cars;
    }
}
